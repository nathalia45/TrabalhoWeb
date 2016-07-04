package br.ufc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name ="id", nullable = false)
	private int id;
	@NotNull
	@Size(min = 15)
	private String nome;
	@NotNull
	@Column(name = "login", unique = true)
	private String login;
	@NotNull
	@Size(min = 6)
	private String senha;
	@NotNull
	private String email;
	private String  url_imagem;
	
	
	@OneToMany(mappedBy ="autor",targetEntity = Classificado.class,fetch = FetchType.LAZY)
	List<Classificado> classificados;
	
	@OneToMany(mappedBy= "autor",targetEntity = Comentario.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "autor",targetEntity = Noticia.class,fetch = FetchType.LAZY)
	List<Noticia> noticias;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao",
				joinColumns = @JoinColumn(name = "id_usuario"),
				inverseJoinColumns = @JoinColumn(name = "id_permissao" , referencedColumnName = "id_permissao"))
	private List<Permissao> permissoes;
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getUrl_imagem() {
		return url_imagem;
	}

	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}
	public List<Permissao> getPermissoes(){
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes){
		this.permissoes = permissoes;
	}
	public boolean getPermissoes(String permissao){
		for(Permissao p : permissoes){
			if(p.getPapel().equals("leitor")){
				return true;
			}
		}
		return false;
	}
	public String toString(){
		StringBuffer paper = new StringBuffer();
		paper.append("id_usuario :" + id);
		paper.append( "  login : "+login);
		paper.append( "  senha :"+ senha);
		paper.append( "  nome  :"+ nome);
		paper.append("  email :" + email);
		paper.append("Papeis :" + permissoes);
		return paper.toString();
		
	}
	}


