package br.ufc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "noticia")
public class Noticia {
	@Id
	@GeneratedValue
	@Column(name = "id_noticia",nullable = false)

	private Date data_da_noticia;
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public Comentario getComentario() {
		return comentario;
	}
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	@NotNull
	@Size(min = 5)
	private String titulo;
	@NotNull
	@Size(min = 10)
	private String subtitulo;
	@NotNull
	@Size(min = 15)
	private String texto_noticia;
	@OneToOne
	private Comentario comentario;
	private Date data_noticia;
	private  int id_categoria;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_autor")
	private Usuario autor;
	
	@OneToOne(mappedBy= "noticia",targetEntity = Comentario.class,cascade = CascadeType.ALL)
	private List<Comentario> comentarios;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_secao",referencedColumnName = "id_secao")
	private Secao secao;
	
	public Date getData_noticia() {
		return data_noticia;
	}
	public void setData_noticia(Date data_noticia) {
		this.data_noticia = data_noticia;
	}
	public String getUrl_imagem() {
		return url_imagem;
	}
	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}
	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	@Column(name = "url", nullable = false)
	private String url_imagem;

	public Secao getSecao() {
		return secao;
	}
	public Date getData_da_noticia() {
		return data_da_noticia;
	}
	public void setData_da_noticia(Date data_da_noticia) {
		this.data_da_noticia = data_da_noticia;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto_noticia() {
		return texto_noticia;
	}
	public void setTexto_noticia(String texto_noticia) {
		this.texto_noticia = texto_noticia;
	}
	
	
}
