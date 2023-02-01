

package org.agenda.tsi.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.agenda.exception.EmailNotFilledException;
import org.agenda.tsi.model.ListaDeCompras;
import org.agenda.tsi.persistencia.IConnectionDB;
import org.agenda.tsi.persistencia.ListaDeComprasDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Lista extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField MaterialTextField;
	private JTextField QuantidadeTextField;
	private JTextField TipoTextField;
	private IConnectionDB databaseMySQL;

	
	public Lista() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeCompras = new JLabel("Lista de Compras");
		lblListaDeCompras.setFont(new Font("Dialog", Font.BOLD, 24));
		lblListaDeCompras.setBounds(176, 0, 201, 37);
		contentPane.add(lblListaDeCompras);
		
		JLabel lblMateriasNecessrios = new JLabel("Materias Necessários");
		lblMateriasNecessrios.setBounds(197, 216, 161, 14);
		contentPane.add(lblMateriasNecessrios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 242, 571, 241);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(table.getSelectedRow() != -1) {
					MaterialTextField.setText(table.getValueAt(table.getSelectedRow(),1).toString());
					QuantidadeTextField.setText(table.getValueAt(table.getSelectedRow(),2).toString());
					TipoTextField.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "material", "quantidade", "tipo"
			}
		));
		scrollPane.setViewportView(table);
		readJTable();
		
	
		
		JLabel lblMateriais = new JLabel("Materiais");
		lblMateriais.setBounds(30, 51, 55, 14);
		contentPane.add(lblMateriais);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(224, 46, 138, 25);
		contentPane.add(lblQuantidade);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(441, 51, 55, 14);
		contentPane.add(lblTipo);
		
		MaterialTextField = new JTextField();
		MaterialTextField.setBounds(12, 84, 138, 18);
		contentPane.add(MaterialTextField);
		MaterialTextField.setColumns(10);
		
		QuantidadeTextField = new JTextField();
		QuantidadeTextField.setBounds(211, 84, 138, 18);
		contentPane.add(QuantidadeTextField);
		QuantidadeTextField.setColumns(10);
		
		TipoTextField = new JTextField();
		TipoTextField.setBounds(407, 84, 138, 18);
		contentPane.add(TipoTextField);
		TipoTextField.setColumns(10);
		
		JButton btnAdicionarMaterial = new JButton("Adicionar");
		btnAdicionarMaterial.setBounds(211, 138, 147, 24);
		contentPane.add(btnAdicionarMaterial);
		
		JButton btnRemoverMaterial = new JButton("Remover");
		btnRemoverMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					ListaDeCompras l = new ListaDeCompras();
					ListaDeComprasDAO dao = new ListaDeComprasDAO();
					l.setId((int)(table.getValueAt(table.getSelectedRow(), 0)));
					dao.removerItem(l);
					readJTable();
					
					
				}
			}
		});
		btnRemoverMaterial.setBounds(407, 138, 138, 24);
		contentPane.add(btnRemoverMaterial);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					ListaDeCompras l = new ListaDeCompras();
					ListaDeComprasDAO dao = new ListaDeComprasDAO();
					l.setMaterial(MaterialTextField.getText());
					l.setQuantidade(QuantidadeTextField.getText());
					l.setTipo(TipoTextField.getText());
					l.setId((int)(table.getValueAt(table.getSelectedRow(), 0)));
					dao.atualizarItem(l);					
					apagarCampos();
					readJTable();
				}
			}
		});
		btnNewButton.setBounds(16, 138, 134, 24);
		contentPane.add(btnNewButton);
		btnAdicionarMaterial.addActionListener(new SalvarAction());
	}
	


	public void apagarCampos() {
		MaterialTextField.setText("");
		QuantidadeTextField.setText("");
		TipoTextField.setText("");
	}
	
	private class SalvarAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				ListaDeComprasDAO.getInstance().adicionarItem(new ListaDeCompras(0, MaterialTextField.getText(), QuantidadeTextField.getText(), TipoTextField.getText()));
				JOptionPane.showMessageDialog(null, "item inserido com sucesso");
				apagarCampos();
				
			} catch (EmailNotFilledException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			readJTable();
		}
		
	}


	private class FecharAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}

	public JTable getTable() {
		return table;
	}



	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void readJTable() {
		DefaultTableModel modelo = (DefaultTableModel)table.getModel();
		modelo.setNumRows(0);
		ListaDeComprasDAO ldao = new ListaDeComprasDAO();
		for(ListaDeCompras l:ldao.read()) {
			modelo.addRow(new Object[] {
					l.getId(),
					l.getMaterial(),
					l.getQuantidade(),
					l.getTipo()
			});
		}
	}
}


