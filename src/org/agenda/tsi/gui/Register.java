package org.agenda.tsi.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.agenda.exception.EmailNotFilledException;
import org.agenda.tsi.model.ListaDeCompras;
import org.agenda.tsi.model.Usuario;
import org.agenda.tsi.persistencia.ListaDeComprasDAO;
import org.agenda.tsi.persistencia.UsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField UsertextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrese = new JLabel("Registre-se");
		lblRegistrese.setBounds(12, 3, 165, 36);
		lblRegistrese.setFont(new Font("Dialog", Font.BOLD, 30));
		contentPane.add(lblRegistrese);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(49, 51, 78, 14);
		contentPane.add(lblUsername);
		
		UsertextField = new JTextField();
		UsertextField.setBounds(49, 66, 233, 18);
		contentPane.add(UsertextField);
		UsertextField.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(49, 111, 55, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(49, 137, 233, 18);
		contentPane.add(passwordField);
		
		JButton btnRegistrese = new JButton("Registre-se");
		btnRegistrese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UsuarioDAO.getInstance().cadastraUsuario(new Usuario(0, UsertextField.getText(),passwordField.getText()));
					JOptionPane.showMessageDialog(null, "item inserido com sucesso");
					apagarCampos();
					
				} catch (EmailNotFilledException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnRegistrese.setBounds(174, 167, 108, 24);
		contentPane.add(btnRegistrese);
		
		JButton btnIrParaLogin = new JButton("Ir para login");
		btnIrParaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnIrParaLogin.setBounds(174, 206, 108, 24);
		contentPane.add(btnIrParaLogin);
	}
	
	public void apagarCampos() {
		UsertextField.setText("");
		passwordField.setText("");
		
	}
	private class SalvarAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}
		
	}

}
