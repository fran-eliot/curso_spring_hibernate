package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idItem;
	@Column(name="url")
	private String url;
	private String tematica;
	private String descripcion;
	
	public Item(int idItem, String url, String tematica, String descripcion) {
		this.idItem = idItem;
		this.url = url;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}

	public Item() {
		
	}
	
	public Item(String url, String tematica, String descripcion) {
		this.url = url;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
