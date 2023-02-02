package org.agenda.tsi.app;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import org.agenda.tsi.gui.Lista;
import org.agenda.tsi.gui.Register;
import org.agenda.tsi.persistencia.ListaDeComprasDAO;


public class Aplicacao {

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

}
