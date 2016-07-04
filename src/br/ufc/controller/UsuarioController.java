package br.ufc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.criptografia.Criptografia;
import br.ufc.dao.PermissaoDAO;
import br.ufc.dao.UsuarioDAO;
import br.ufc.model.Permissao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private PermissaoDAO permissaoDAO;
	
	@RequestMapping("formluarioUsuario")
	public String formularioUsuario(){
		return "usuario/inserir_usuario";
	}
	
	@RequestMapping("formularioJornalista")
	public String formularioEditor(){
		return "usuario/inserir_editor";
	}
	
	@RequestMapping(value = "formularioEditor",method = RequestMethod.POST)
	public String cadastrarJornalista(@Valid Usuario usuario,Permissao permissao,
		BindingResult result,@RequestParam("file") MultipartFile file){
		
		Criptografia criptografia = new Criptografia();
		usuario.setSenha(criptografia.criptografar(usuario.getSenha()));
		Permissao papel = permissaoDAO.buscar(permissao);
		List<Permissao> papeis = new ArrayList<Permissao>();
		papeis.add(papel);
		usuario.setPermissoes(papeis);
		if(!file.isEmpty()){
			try {
				String nomeImg = new Date(0).getTime() + "-"
						+ file.getOriginalFilename();
				String imagem = ""
						+ nomeImg;

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(imagem)));
				stream.write(bytes);
				stream.close();
				usuario.setUrl_imagem(nomeImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.usuarioDAO.add(usuario);
		return "usuario/usuario_/adicionado";
	}
	
	@RequestMapping("adicionarUsuario")
	public String addUsuario(@Valid Usuario usuario,BindingResult result,Permissao permissao){
		Criptografia criptografia = new Criptografia();
		usuario.setSenha(criptografia.criptografar(usuario.getSenha()));
		if(result.hasErrors()){
			return "usuario/inserir_usuario";
		}
		System.out.println(usuario.getNome());
		System.out.println(usuario.getEmail());
		System.out.println(permissao.getId_permissao());
		
		Permissao papel = permissaoDAO.buscar(permissao);
		List<Permissao> papeis = new ArrayList<Permissao>();
		papeis.add(papel);
		usuario.setPermissoes(papeis);
		System.out.println("Papel user:" + usuario.getPermissoes());
		this.usuarioDAO.add(usuario);
		return"usuario/usuario_adicionado";
	}
	@RequestMapping("listarUsuario")
	public String listarUsuario(Model model){
		List<Usuario> usuarios = usuarioDAO.listar();
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("tamanho", usuarios);
		System.out.println("teste vazia" + usuarios.size());
		return "usuario/listar_usuarios";
	}
	
	@RequestMapping("deletarUsuario")
	public String removerUsuario(Usuario u){
		usuarioDAO.deletar(u);
		return "redirect:listarUsuario";
	}
	
	}

