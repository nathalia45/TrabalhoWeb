package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.model.Noticia;

@Repository
@Transactional
public class NoticiaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public NoticiaDao(){
		
	}
	
	public Noticia getNoticiaId(int id){
		return this.manager.find(Noticia.class, id);
	}
	public void add(Noticia noticia){
		System.out.println("noticia adicionada" + noticia.getTitulo());
		this.manager.persist(noticia);
	}
	public Noticia buscar(Noticia noticia){
		Noticia noticia2 = this.manager.find(Noticia.class,noticia.getId_categoria());
		return noticia2;
	}
	public List<Noticia> listar(){
		String hql = "select n from Noticia n";
		return this.manager.createQuery(hql, Noticia.class).getResultList();
	}
	
	public void deletar(Noticia noticia){
		Noticia noticia2 = buscar(noticia);
	}
	public void alterar(Noticia noticia){
		this.manager.merge(noticia);
	}
}
