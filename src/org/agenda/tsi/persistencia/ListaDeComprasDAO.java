package org.agenda.tsi.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.agenda.exception.EmailNotFilledException;
import org.agenda.tsi.gui.Lista;
import org.agenda.tsi.model.ListaDeCompras;

public class ListaDeComprasDAO implements IPersistencia{
	private IConnectionDB databaseMySQL;
	private static ListaDeComprasDAO instance;
	public ListaDeComprasDAO() {
		databaseMySQL = new DatabaseMySQL();
	}
	private Lista view;
	

	public ListaDeComprasDAO(Lista view) {
		super();
		this.view = view;
	}
	


	public static ListaDeComprasDAO getInstance() {
		if(instance == null)
			return new ListaDeComprasDAO();
		else
			return instance;
	}
	
	public void adicionarItem(ListaDeCompras l) throws EmailNotFilledException{
	
		System.out.println(l.getMaterial());
		if(l.getMaterial() == null || l.getMaterial().equalsIgnoreCase("") || l.getMaterial().equalsIgnoreCase("\n")) {
			throw new EmailNotFilledException("O campo de material é obrigatorio");
		}else {
			try {
				PreparedStatement pstm = databaseMySQL.
						getConnection().prepareStatement("INSERT INTO LISTA VALUES (?, ?, ?, ?);");
				pstm.setInt(1, l.getId());
				pstm.setString(2, l.getMaterial());
				pstm.setString(3, l.getQuantidade());
				pstm.setString(4, l.getTipo());
				pstm.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
	
	public List<ListaDeCompras> read() {
		Connection con = databaseMySQL.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ListaDeCompras> listas = new ArrayList<>();
		try {
			pstm = con .prepareStatement("SELECT * FROM LISTA;");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				ListaDeCompras lista = new ListaDeCompras();
				lista.setId(rs.getInt("id"));
				lista.setMaterial(rs.getString("material"));
				lista.setQuantidade(rs.getString("quantidade"));
				lista.setTipo(rs.getString("tipo"));
				listas.add(lista);
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
		
		return listas;
	}

	
	public List<ListaDeCompras> readMaterial(String mat) {
		Connection con = databaseMySQL.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ListaDeCompras> listas = new ArrayList<>();
		try {
			pstm = con .prepareStatement("SELECT * FROM LISTA WHERE material LIKE ?;");
			pstm.setString(1, "%"+mat+"%");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				ListaDeCompras lista = new ListaDeCompras();
				lista.setId(rs.getInt("id"));
				lista.setMaterial(rs.getString("material"));
				lista.setQuantidade(rs.getString("quantidade"));
				lista.setTipo(rs.getString("tipo"));
				listas.add(lista);
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
		
		return listas;
	}

	@Override
	public void removerItem(ListaDeCompras l) {
		try {
			PreparedStatement pstm = databaseMySQL.
					getConnection().prepareStatement("DELETE FROM LISTA WHERE id = ?;");
			pstm.setInt(1, l.getId());
			pstm.execute();
			JOptionPane.showMessageDialog(null,"excluido com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao excluir");
			e.printStackTrace();
		}
		
	}

	@Override
	public void atualizarItem(ListaDeCompras l) {
		try {
			PreparedStatement pstm = databaseMySQL.
					getConnection().prepareStatement("UPDATE LISTA SET material = ? ,quantidade = ?,tipo = ? WHERE id = ?;");
			pstm.setString(1,l.getMaterial());
			pstm.setString(2, l.getQuantidade());
			pstm.setString(3, l.getTipo());
			pstm.setInt(4, l.getId());
			pstm.execute();
			JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao atualizar");
			e.printStackTrace();
		}
		
	}



	@Override
	public ListaDeCompras localizarMaterial(String material) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}



