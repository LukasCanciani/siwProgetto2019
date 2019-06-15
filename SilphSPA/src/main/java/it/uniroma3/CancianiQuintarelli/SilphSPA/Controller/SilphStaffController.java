package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.AlbumForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.FotoForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffValidator;

@Controller
public class SilphStaffController {
	
	@Autowired
	private SilphStaffValidator silphStaffValidator;
	
	@Autowired
	private SilphStaffService silphStaffService;
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("silphStaff") SilphStaff silphStaff, Model model, BindingResult bindingResult) {
		
		this.silphStaffValidator.validate(silphStaff,bindingResult);
		if(!bindingResult.hasErrors()) {
			SilphStaff control =silphStaffService.login(silphStaff.getUsername(), silphStaff.getPassword());
			//SilphStaff control = (SilphStaff)silphStaffService.findByUsername(silphStaff.getUsername());
			//SilphStaff control = silphStaffService.findByUser(silphStaff.getUsername());
			if (control == null) {
					//utente non trovato o pass sbagliata
					bindingResult.rejectValue("username", "wrong");
					return "login.html";
			}
			else {
				//successo
				return "admin.html";
			}
			//Se non ha errori, controllare se è uguale a controll la password!! poi decidi se dare eerrrore o procedere
			
		}
		else {
			//Return con campo vuoto
			return "login.html";
		}
	}
	
	@RequestMapping(value ="/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("silphStaff",new SilphStaff());
		return "login.html";
	}
	
	@RequestMapping(value ="/inserimento", method = RequestMethod.GET)
	public String inserimento(Model model) {
		return "inserimento.html";
	}
	
	@RequestMapping(value = "/inserimentoFoto")
	public String inserimentoFoto(Model model) {
		model.addAttribute("fotoForm",new FotoForm());
		return "inserimentoFoto.html";
	}
	
	@RequestMapping(value = "/inserimentoFotografo")
	public String inserimentoFotografo(Model model) {
		model.addAttribute("fotografo",new Fotografo());
		return "inserimentoFotografo.html";
	}
	
	@RequestMapping(value = "/inserimentoAlbum")
	public String inserimentoAlbum(Model model) {
		model.addAttribute("albumForm",new AlbumForm());
		return "inserimentoAlbum.html";
	}
	
}
