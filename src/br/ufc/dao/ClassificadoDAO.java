package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.model.Classificado;

@Repository
@Transactional
public class ClassificadoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	public ClassificadoDAO(){
		
	}
	
	public Classificado buscar(Classificado classificado){
		Classificado classificado2 = this.manager.find(Classificado.class, classificado.getId_classificado());
		return classificado2;
	}
	
	public Classificado classificadoId(int  id) {
		// TODO Auto-generated method stub
		String hql = "select c from classificado c where c.id_classificado =:";
		TypedQuery<Classificado> query = this.manager.createQuery(hql, Classificado.class);
		query.setParameter("id", id).getResultList();
		List<Classificado> classificados = query.getResultList();
		Classificado c = classificados.get(0);
		return null;
	}
     public void deletar(Classificado classificado) {
		
		Classificado classificado2 = buscar(classificado);
		this.manager.remove(classificado2);
	}
     public List<Classificado> listar(){
    		
 		String hql = "select c from Classificado c";
 		
 		return this.manager.createQuery(hql, Classificado.class).getResultList();
 		
 	}
     public void add(Classificado classificado) {
 		
 		System.out.println("já foi classficado!!");
 		this.manager.persist(classificado);
 	}

 	
 	public void alterar(Classificado classificado) {
 		// TODO Auto-generated method stub
 		this.manager.merge(classificado);
 	}


}
