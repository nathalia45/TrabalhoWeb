package br.ufc.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
public class ErrorController {
	
	@RequestMapping("Error")
	public String Error(){
		return "erro/pagina_erro";
	}
}
