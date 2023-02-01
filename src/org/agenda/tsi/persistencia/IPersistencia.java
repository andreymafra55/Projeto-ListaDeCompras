package org.agenda.tsi.persistencia;

import org.agenda.exception.EmailNotFilledException;
import org.agenda.tsi.model.ListaDeCompras;

public interface IPersistencia {


	
	public void adicionarItem(ListaDeCompras L) throws EmailNotFilledException;

	public ListaDeCompras localizarMaterial(String material);
	
	void removerItem(ListaDeCompras l);

	void atualizarItem(ListaDeCompras l);
	
	
	
}
