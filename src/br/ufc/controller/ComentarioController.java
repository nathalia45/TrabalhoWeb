package br.ufc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.ComentarioDAO;
import br.ufc.dao.NoticiaDao;
import br.ufc.dao.UsuarioDAO;
import br.ufc.model.Comentario;
import br.ufc.model.Noticia;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class ComentarioController {
	
	@Autowired
	private ComentarioDAO cDAO;
	@Autowired
	private NoticiaDao nDAO;
	@Autowired
	private UsuarioDAO uDAO;
	
	@RequestMapping("comentarios")
	public String Comentarios(String texto,int id_noticia,HttpSession session,Model model){
		Usuario autor = (Usuario) session.getAttribute("usuario");
		Noticia noticia = nDAO.getNoticiaId(id_noticia);
		
		if(autor != null && autor.getPermissoes("leitor")){
			Comentario comentario = new Comentario();
			comentario.setAutor(uDAO.buscarLogin(autor));
			comentario.setTexto(texto);
			this.cDAO.add(comentario);
			
			List<Comentario> c = noticia.getComentarios();
			c.add(comentario);
			noticia.setComentarios(c);
			model.addAttribute("noticia",noticia);
			model.addAttribute("comentarios", cDAO.comentarios(id_noticia));
			return "noticia/noticia_comentada";
		}
		return "redirect:Error";
		}
}
