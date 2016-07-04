package br.ufc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.NoticiaDao;
import br.ufc.dao.SecaoDAO;
import br.ufc.dao.UsuarioDAO;
import br.ufc.model.Noticia;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class NoticiaController {
	
	@Autowired
	private UsuarioDAO uDAO;
	@Autowired
	private NoticiaDao nDAO;
	@Autowired
	private SecaoDAO sDAO;
	
	@RequestMapping("ler_Noticia")
	public String lerNoticia(Noticia noticia,Model model){
		Noticia n = nDAO.buscar(noticia);
		System.out.println("noticia" + n.getTitulo());
		model.addAttribute("noticia", n.getTitulo());
		return "noticia/noticia_expandida";
	}
	@RequestMapping("ver_Noticia")
	public String Noticia(Model model){
		List<Noticia> noticias = nDAO.listar();
		model.addAttribute("noticias", noticias);
		model.addAttribute("tamanho", noticias.size());
		return "noticia/inserir_noticia";
	}
	@RequestMapping("formulario_Noticia")
	public String adicionarNoticia(Noticia noticia,HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		System.err.println(usuario);
		
		if(usuario == null){
			return "redirect:formularioNoticia";
		}
		System.err.println("idsec" + noticia.getId_categoria());
		noticia.setAutor(uDAO.getUserId(usuario.getId()));
		noticia.setSecao(sDAO.getSecao(noticia.getId_categoria()));
		this.nDAO.add(noticia);
		return "noticia_adicionada";
		
	}
	
	@RequestMapping(value = "add_noticia",method = RequestMethod.POST)
	public String adicionarNoticia(Noticia noticia,HttpSession session,Secao secao,
			@RequestParam("file") MultipartFile file){
			
		if(!file.isEmpty()){
			try{
				String nomeImagem = new Date().getTime() + "-"
						+ file.getOriginalFilename();
				String imagem = "/home/ufc/jornalCaravana";
			}catch(Exception e){
			 e.printStackTrace();
			}
		}
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		System.err.println(usuario);
		
		if(usuario == null){
			return "redirect:formularioNoticia";
		}
		System.err.println("idsecao" + noticia.getId_categoria());
		noticia.setAutor(uDAO.getUserId(usuario.getId()));
		noticia.setSecao(sDAO.getSecao(noticia.getId_categoria()));
		
		this.nDAO.add(noticia);
		return "noticia/noticia_adicionado";
	}
	
	@RequestMapping("listarNoticia")
	public String listaNoticias(Model model){
		
		List<Noticia> noticias = nDAO.listar();
		model.addAttribute("noticias", noticias);
		model.addAttribute("tamanho", noticias.size());
		return "noticia/listar_noticia";
		}
	@RequestMapping("deletarNoticiaOnly")
	public String deletarNoticiaOnly(Noticia noticia){
		nDAO.deletar(noticia);
		return "noticia/listar_noticia";
	}
	
	@RequestMapping("deletarNoticiasAll")
	public String deletarNoticiaAll(Noticia noticia){
		nDAO.deletar(noticia);
		return "noticia/noticia";
	}
		

}
