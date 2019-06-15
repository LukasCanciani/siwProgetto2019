package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotografoService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotografoValidator;

@Controller
public class FotografoController {
	
	@Autowired
	private FotografoService fs;
	
	@Autowired
	private FotografoValidator fv;
	
	
	@RequestMapping(value ="/inserisciFotografo", method = RequestMethod.POST)
	public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model, BindingResult bindingResult) {
		this.fv.validate(fotografo, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.fs.salvaFotografo(fotografo);
			return "successo.html";
		}
		else {
			return "inserimentoFotografo.html";
		}
	}
	
}
