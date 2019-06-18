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

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Carrello;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Richiesta;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.RichiestaService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.RichiestaValidator;

@Controller
public class RichiestaController {
	@Autowired
	RichiestaValidator rv;

	@Autowired
	RichiestaService rs;

	@RequestMapping(value="/richiesta")
	private String newRichiesta(@ModelAttribute("carrello")Carrello carrello,Model model,BindingResult br) {
		
		if(Carrello.getCarrello().getFoto().isEmpty()) {
			br.rejectValue("foto", "empty");
			return "carrello.html";
		}
		else {
			Richiesta richiesta = new Richiesta();
			model.addAttribute("richiesta", richiesta);
			model.addAttribute("lista", Carrello.getCarrello().getFoto());
			return "inserimentoRichiesta.html";
		}
	}
	@RequestMapping(value ="/inserisciRichiesta", method = RequestMethod.POST)
	public String newFotografo(@Valid @ModelAttribute("richiesta") Richiesta richiesta, Model model, BindingResult bindingResult) {
		this.rv.validate(richiesta, bindingResult);

		if(!bindingResult.hasErrors()) {
			richiesta.setFoto(Carrello.getCarrello().getFoto());
			this.rs.salvaRichiesta(richiesta);
			Carrello.getCarrello().cleanCarrello();
			return "redirect:/";
		}
		else {
			model.addAttribute("lista", Carrello.getCarrello().getFoto());
			return "inserimentoRichiesta.html";
		}
	}
	@RequestMapping(value="/richieste")
	public String mostraRichieste(Model model) {
		
		List<Richiesta> richieste = this.rs.tutti();
		if(richieste.size()==0) {
			UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("username", details.getUsername());
			String message = "Nessuna richiesta da mostrare";
			model.addAttribute("error", message);
			return "admin.html";
		}
		else {
			model.addAttribute("richieste", richieste);
			return "listaRichieste.html";
		}
	}
	@RequestMapping(value="/rimuoviRichiesta/{id}", method=RequestMethod.GET)
	public String rimuoviRichiesta(@PathVariable("id") Long id, Model model) {
		Richiesta richiesta = this.rs.trovaRichestaPerId(id);
		if ( richiesta == null) {
			return "redirect:/richieste";
		} else {
			this.rs.rimuoviRichiesta(richiesta);
			return "redirect:/richieste";
		}
	}
}