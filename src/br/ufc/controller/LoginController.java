package br.ufc.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.criptografia.Criptografia;
import br.ufc.dao.UsuarioDAO;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class LoginController {
	
	@Autowired
	private UsuarioDAO uDAO;
	
	@RequestMapping("formularioLogin")
	public String formularioLogin(){
		return "login/login";
	}
	
	@RequestMapping("fazerLogin")
	public String efetuarLogin(Usuario usuario,HttpSession session){
		Criptografia criptografia = new Criptografia();
		usuario.setSenha(criptografia.criptografar(usuario.getSenha()));
		Usuario usuario2 = uDAO.buscarLogin(usuario);
		if(usuario.getSenha().equals(usuario2.getSenha())
			&& usuario.getLogin().equals(usuario2.getLogin())){
			session.setAttribute("usuario", usuario2);
			return"redirect:formularioLogin";
		}
		return "redirect:formularioLogin";
	}
	@RequestMapping("Logout")
	public String sair(HttpSession session){
		session.invalidate();
		return "index";
	}
}
