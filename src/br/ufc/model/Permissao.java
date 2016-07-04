package br.ufc.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "permissao")
public class Permissao {
	@Id
	@GeneratedValue
	@Column(name = "id_permissao")
	private int id_permissao;
	@NotNull
	@Column(name ="papel")
	private String papel;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Usuario> usuarios;
	
	public int getId_permissao() {
		return id_permissao;
	}

	public void setId_permissao(int id_permissao) {
		this.id_permissao = id_permissao;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	

	
	
}
