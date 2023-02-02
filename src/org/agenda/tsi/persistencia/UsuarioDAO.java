package org.agenda.tsi.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.agenda.exception.EmailNotFilledException;
import org.agenda.tsi.model.ListaDeCompras;
import org.agenda.tsi.model.Usuario;


public class UsuarioDAO {
	private IConnectionDB databaseMySQL;
	private static UsuarioDAO instance;
	boolean check = false;
	public UsuarioDAO() {
		databaseMySQL = new DatabaseMySQL();
	}
	
	public void cadastraUsuario(Usuario u) throws EmailNotFilledException{
		
		System.out.println(u.getLogin());
		if(u.getLogin() == null || u.getLogin().equalsIgnoreCase("") || u.getLogin().equalsIgnoreCase("\n")) {
			throw new EmailNotFilledException("O campo de login é obrigatorio");
		}else {
			try {
				PreparedStatement pstm = databaseMySQL.
						getConnection().prepareStatement("INSERT INTO USERS VALUES (?, ?, ?);");
				pstm.setInt(1, u.getId());
				pstm.setString(2, u.getLogin());
				pstm.setString(3, u.getSenha());
				pstm.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	public boolean checkLogin(String login,String senha) {
		Connection con = databaseMySQL.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = con .prepareStatement("SELECT * FROM USERS WHERE login = ? and senha = ?;");
			pstm.setString(1,login);
			pstm.setString(2, senha);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
				pstm.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return check;
	}

	public static UsuarioDAO getInstance() {
		if(instance == null)
			return new UsuarioDAO();
		else
			return instance;
	}
}
