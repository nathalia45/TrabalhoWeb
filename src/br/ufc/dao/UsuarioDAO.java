package br.ufc.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.model.Usuario;

@Repository
@Transactional
public class UsuarioDAO {

	
	@PersistenceContext
	private EntityManager manager;
	
	public UsuarioDAO(){
		
	}
	public Usuario getUserId(int id){
		return this.manager.find(Usuario.class, id);
	}
	public void add(Usuario usuario) {
		// TODO Auto-generated method stub
		System.out.println("o nome  novo usuario:"+ usuario.getNome());
		this.manager.persist(usuario);
		
	}
	public void deletar(Usuario usuario){
		
		System.out.println("buscar usuario para remoção " + usuario.getNome());
		Usuario usuarioRef = this.manager.find(Usuario.class,usuario.getId());
		this.manager.persist(usuario);
	}
	
	public void alterar(Usuario usuario) {
		// TODO Auto-generated method stub
		this.manager.merge(usuario);
	}


	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		String hql = "select u from usuario u";
		
		return this.manager.createQuery(hql, Usuario.class).getResultList();
	}
	public Usuario buscar(Usuario usuario){
		Usuario usuario2 = this.manager.find(Usuario.class, usuario.getId());
		return usuario2;
	}

	
	public Usuario buscarLogin(Usuario usuario) {
		// TODO Auto-generated method stub
		String hql = "select u from Usuario u where u.login " + usuario.getLogin() + "'";
		
		return (Usuario) this.manager.createQuery(hql, Usuario.class).getResultList().get(0);	
	}
	public Usuario VerificarLogin(Usuario usuario){
		String hql = "select u from Usuario u where u.login " + usuario.getLogin() + "'";
		List<?> resp = this.manager.createQuery(hql).getResultList();
		if(!resp.isEmpty())
			return (Usuario) this.manager.createQuery(hql).getResultList().get(0);
		return null;
		
	}
}
