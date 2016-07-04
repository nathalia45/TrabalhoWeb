package br.ufc.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.ClassificadoDAO;
import br.ufc.dao.UsuarioDAO;
import br.ufc.model.Classificado;

@Transactional
@Controller
public class ClassificadoController {
	
	@Autowired
	private ClassificadoDAO classicadoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping("formularioClassificado")
	public String formularioClassicao(){
		return "classificado/cadastrar_classificado";
	}
	
	@RequestMapping("cadastrarClassificado")
	public String adicionarClassificado(HttpSession session,Classificado classificado){
		classicadoDAO.add(classificado);
		System.out.println("classificado adicionado com sucesso");
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("listarClassificado")
	public String listarClassificados(Model model){
		List<Classificado> classificados = classicadoDAO.listar();
		model.addAttribute("classificados", classificados);
		System.out.println("listar classificados:");
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("inserirOfertaFormulario")
	public String inserirOfertaFormulario(Classificado classificado,Model model){
		Classificado classifique = this.classicadoDAO.buscar(classificado);
		model.addAttribute("classificado", classifique);
		return "classificado/inserir_oferta";
	}
	
	@RequestMapping("alocandoMelhorOferta")
	public String inserirOferta(float oferta,int id,int idUser,Model model){
		Classificado classifique = this.classicadoDAO.classificadoId(id);
		if(oferta > classifique.getPreco() && (oferta > classifique.getMelhor_oferta())){
			classifique.setData_oferta(new Timestamp(System.currentTimeMillis()));
			classifique.setAutor(usuarioDAO.getUserId(idUser));
			classifique.setMelhor_oferta(oferta);
			return "redirect:listarClassificados";
		}
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("deletarClassificado")
	public String deletarClassificado(Classificado u){
		classicadoDAO.deletar(u);
		return "redirect:listarClassificados";
	}
}
