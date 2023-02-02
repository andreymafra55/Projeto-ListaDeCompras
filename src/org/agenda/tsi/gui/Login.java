package org.agenda.tsi.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.agenda.tsi.persistencia.UsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UsertextField;
	private JPasswordField pwdSenha;

	/**
	 * Launch the application.
	 */
	


	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 28));
		lblLogin.setBounds(171, 0, 85, 33);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(171, 45, 90, 14);
		contentPane.add(lblUsername);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(170, 114, 55, 14);
		contentPane.add(lblSenha);
		
		UsertextField = new JTextField();
		UsertextField.setBounds(145, 64, 114, 18);
		contentPane.add(UsertextField);
		UsertextField.setColumns(10);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setText("Senha");
		pwdSenha.setBounds(145, 140, 116, 18);
		contentPane.add(pwdSenha);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDAO dao = new UsuarioDAO();
				if(dao.checkLogin(UsertextField.getText(),pwdSenha.getText())) {
				new Lista().setVisible(true);
				dispose();
				}else {
					JOptionPane.showMessageDialog(null, "login ou senha incorreto");
				}
			}
		});
		btnLogar.setBounds(158, 186, 98, 24);
		contentPane.add(btnLogar);
	}
}
