package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "secao")
public class Secao {
	
	@Id
	@GeneratedValue
	@Column(name ="id_secao",nullable = false)
	private int id_secao;
	
	@NotNull
	@Size(min = 2)
	private String titulo;
	@NotNull
	@Size(min = 10)
	private int descricao;
	
	@OneToMany(mappedBy = "secao",targetEntity = Noticia.class, fetch = FetchType.EAGER)
	private List<Noticia> noticias;

	public int getId_secao() {
		return id_secao;
	}

	public void setId_secao(int id_secao) {
		this.id_secao = id_secao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDescricao() {
		return descricao;
	}

	public void setDescricao(int descricao) {
		this.descricao = descricao;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
}
