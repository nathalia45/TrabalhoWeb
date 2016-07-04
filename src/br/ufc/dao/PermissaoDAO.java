package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.model.Permissao;

@Repository
@Transactional
public class PermissaoDAO {
	@PersistenceContext
	private EntityManager manager;
	
	public PermissaoDAO(){
		
	}
	
	public void add(Permissao permissao){
		this.manager.persist(permissao);
	}
	public Permissao buscar(Permissao permissao){
		 permissao= this.manager.find(Permissao.class,permissao.getId_permissao());
		return permissao;
	}
	
	public void deletar(Permissao permissao){
		Permissao permissao2 = buscar(permissao);
		this.manager.remove(permissao2);
	}
	
	public void alterar(Permissao permissao){
		this.manager.merge(permissao);
	}
	
	public List<Permissao> listar(){
		
		String hql = "select p from permissao p";
		return this.manager.createQuery(hql, Permissao.class).getResultList();
	}
	
	

}
