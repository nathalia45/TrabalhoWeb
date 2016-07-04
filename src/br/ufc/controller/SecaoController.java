package br.ufc.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.SecaoDAO;
import br.ufc.model.Noticia;
import br.ufc.model.Secao;

@Transactional
@Controller
public class SecaoController {
	
	@Autowired
	private SecaoDAO secaoDAO;
	
	@RequestMapping("formularioSecao")
	public String formularioSecao(){
		return "secao/cadastrar_secao";
	}
	
	@RequestMapping("cadastrarSecao")
	public String adicionarSecao(Secao secao){
		System.out.println("titulo secao" + secao.getTitulo());
		System.out.println("descricao" + secao.getDescricao());
		
		Secao secao1 = new Secao();
		secao.setTitulo(secao.getTitulo());
		secao.setDescricao(secao.getDescricao());
		System.out.println("Leia já" + secaoDAO);
		this.secaoDAO.add(secao1);
		System.out.println("nulo ou n");
		return "secao/secao_adicionado";
	}
	@RequestMapping("descricaoSecao")
	public String desSecao(Secao secao,Model model){
		secao = secaoDAO.buscar(secao);
		List<Noticia> noticias= secao.getNoticias();
		model.addAttribute("noticias", noticias);
		return "noticia/noticia";
	}
	
	@RequestMapping("listarSecao")
	public String listarSecao(Model model){
		
		List<Secao> secoes = secaoDAO.listar();
		model.addAttribute("secoes", secoes);
		model.addAttribute("tamanho", secoes.size());
		System.out.println("o tamanho das seções é:" + secoes.size());
		return "secao/listar_secao";
	}
	@RequestMapping("deletarSecao")
	public String removerSecao(Secao s){
		secaoDAO.deletar(s);
		return "redirect:listar_secao";
	}
}
