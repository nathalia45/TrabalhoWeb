package br.ufc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comentario")
public class Comentario {
	@Id
	@GeneratedValue
	@Column(name = "id",nullable = false)
	private int id;
	
	@NotNull
	@Size(min = 100)
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_noticia",referencedColumnName = "id_noticia")
	private Noticia noticia;
	
	@ManyToOne(optional = false, cascade= CascadeType.ALL)
	@JoinColumn(name = "id_autor")
	
	private Usuario autor;
	@NotNull
	private String texto;
	public Noticia getNoticia() {
		return noticia;
	}
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}