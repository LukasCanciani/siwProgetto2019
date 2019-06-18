package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.StringaRicerca;
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
			UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("username", details.getUsername());
			return "/admin.html";
		}
		else {
			return "inserimentoFotografo.html";
		}
	}
	@RequestMapping(value="/cercaFotografoNomeCognome")
	public String cercaFotografoNomeCognome(Model model) {
		model.addAttribute("stringaRicerca", new StringaRicerca());
		return "ricercaFotografoNomeCognome.html";
	}

	@RequestMapping(value="/cercaFotografoId")
	public String cercaFotografoId(Model model) {
		model.addAttribute("stringaRicerca", new StringaRicerca());
		return "ricercaFotografoId.html";
	}



	@RequestMapping(value="/risultatiFotografoNomeCognome", method = RequestMethod.POST)
	public String risultatiFotografoNomeCognome(@ModelAttribute("stringaRicerca") StringaRicerca sr, Model model, BindingResult br) {
		List<Fotografo> risultati;
		if(sr.getStringa1() == "" && sr.getStringa2()=="") {
			br.rejectValue("stringa1", "wrong");
			return "ricercaFotografoNomeCognome.html";
		}
		else if (sr.getStringa1()=="") {
			risultati=this.fs.trovaFotografoPerCognome(sr.getStringa2());
		}else if (sr.getStringa2()=="") {
			risultati=this.fs.trovaFotografoPerNome(sr.getStringa1());
		}else {
			risultati=this.fs.trovaFotografoPerNomeCognome(sr.getStringa1(), sr.getStringa2());
		}
		if (risultati.size()==0) {
			br.rejectValue("stringa1", "wrong");
			return "ricercaFotografoNomeCognome.html";
		}else {
			model.addAttribute("risultati", risultati);
			return "listaFotografi.html";
		}
	}
	@RequestMapping(value="/risultatiFotografoId", method = RequestMethod.POST)
	public String risultatiFotografoId(@ModelAttribute("stringaRicerca") StringaRicerca sr, Model model, BindingResult br) {
		Fotografo risultati;
		Long id;
		try {
			id = Long.parseLong(sr.getStringa1());
		} catch (NumberFormatException e) {
			br.rejectValue("stringa1", "wrong");
			return "ricercaFotografoId.html";
		}
		risultati = this.fs.trovaFotografoPerId(id);
		if (risultati == null) {
			br.rejectValue("stringa1", "wrong");
			return "ricercaFotografoId.html";
		}else {
			model.addAttribute("fotografo", risultati);
			return "fotografo.html";
		}
	}

	@RequestMapping(value="/fotografi")
	public String fotografi(Model model) {
		model.addAttribute("risultati", this.fs.tutti());
		return "listaFotografi.html";

	}

	@RequestMapping(value="/fotografo/{id}", method=RequestMethod.GET)
	public String fotografo(@PathVariable("id") Long id, Model model) {
		Fotografo fotografo = this.fs.trovaFotografoPerId(id);
		if ( fotografo == null) {
			return "/fotografi";
		} else {
			model.addAttribute("fotografo", fotografo);
			return "fotografo.html";
		}
	}
}
