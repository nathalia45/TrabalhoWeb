package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.model.Secao;

@Repository
@Transactional
public class SecaoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void add(Secao secao){
		System.out.println("Está SECAO");
		this.manager.persist(secao);
	}
	public Secao buscar(Secao secao){
		Secao secao2 = this.manager.find(Secao.class, secao.getId_secao());
		return secao2;
	}
	
	public void deletar(Secao secao){
		Secao secao2 = buscar(secao);
		this.manager.remove(secao2);
	}
	
	public void alterar(Secao secao){
		this.manager.merge(secao);
	}
	
	public List<Secao> listar(){
		
		String hql = "select s from Secao c";
		return this.manager.createNamedQuery(hql, Secao.class).getResultList();
	}
	public Secao getSecao(int id){
		return this.manager.find(Secao.class, id);
		
	}
}
