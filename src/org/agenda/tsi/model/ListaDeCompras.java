package org.agenda.tsi.model;

import java.util.Objects;

public class ListaDeCompras {
	private Integer id;
	private String material;
	private String quantidade;
	private String Tipo;
	
	public ListaDeCompras(Integer id, String material, String quantidade, String tipo) {
		super();
		this.id = id;
		this.material = material;
		this.quantidade = quantidade;
		Tipo = tipo;
	}
	public ListaDeCompras() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(Tipo, id, material, quantidade);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaDeCompras other = (ListaDeCompras) obj;
		return Objects.equals(Tipo, other.Tipo) && Objects.equals(id, other.id)
				&& Objects.equals(material, other.material) && Objects.equals(quantidade, other.quantidade);
	}
	@Override
	public String toString() {
		return "ListaDeCompras [id=" + id + ", material=" + material + ", quantidade=" + quantidade + ", Tipo=" + Tipo
				+ "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
}
