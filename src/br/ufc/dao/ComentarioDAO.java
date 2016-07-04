package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.model.Comentario;

@Repository
@Transactional
public class ComentarioDAO  {
	
	public ComentarioDAO(){
		
	}

	@PersistenceContext
	private EntityManager manager;
	
	public void add(Comentario comentario) {
		// TODO Auto-generated method stub
		System.out.println("comentário adicionado");
		this.manager.persist(comentario);
		
	}


	public void alterar(Comentario comentario) {
		// TODO Auto-generated method stub
		this.manager.merge(comentario);
	}

	
	public List<Comentario> listar() {
		// TODO Auto-generated method stub
		String hql = "select c from Comentario c";
		return this.manager.createQuery(hql, Comentario.class).getResultList();
	}

	public Comentario buscar(Comentario comentario){
		Comentario comentario2 = this.manager.find(Comentario.class, comentario.getId());
		return  comentario2;
	}
	public void deletar(Comentario comentario) {
		// TODO Auto-generated method stub
		Comentario comentario2 = buscar(comentario);
		this.manager.remove(comentario2);
	}


	public List<Comentario> comentarios(int id_noticia) {
		// TODO Auto-generated method stub
		String hql = "select c from Comentario c where c.noticia.id_noticia =:id_noticia";
		TypedQuery<Comentario> query = this.manager.createQuery(hql, Comentario.class);
		query.setParameter("id_noticia", id_noticia);
		List<Comentario> comentarios = query.getResultList();
		
		return comentarios;
	}

}
