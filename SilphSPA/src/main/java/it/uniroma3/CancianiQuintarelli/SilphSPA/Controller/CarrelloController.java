package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Carrello;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Foto;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotoService;

@Controller
public class CarrelloController {
		@Autowired
		FotoService fs;
	
		@RequestMapping(value = "/carrello/{id}", method = RequestMethod.GET)
		public String aggiungiAlCarrello(@PathVariable("id") Long id, HttpServletRequest request) {
			Carrello carrello = Carrello.getCarrello();
			Foto foto = this.fs.trovaFotoPerId(id);
			if (foto == null) {
				return "redirect:/foto";
			}
			else {
				carrello.addFoto(foto);
				return "redirect:/foto/"+foto.getId();
			}
		}
		
		@RequestMapping(value = "/rimuoviCarrello/{id}", method = RequestMethod.GET)
		public String rimuoviDalCarrello(@PathVariable("id") Long id, HttpServletRequest request) {
			Carrello carrello = Carrello.getCarrello();
			Foto foto = this.fs.trovaFotoPerId(id);
			if (foto == null) {
				return "redirect:/carrello";
			}
			else {
				carrello.removeFoto(foto);
				return "redirect:/carrello"+foto.getId();
			}
		}
		
		@RequestMapping(value="/carrello")
		public String mostraCarrello(Model model) {
			model.addAttribute("carrello",Carrello.getCarrello());
			return "carrello.html";
		}
}
