package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.AlbumForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.FotoForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;

@Controller
public class SilphStaffController {


	
	@RequestMapping(value="/admin")
	public String login(Model model){
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", details.getUsername());
		return "admin.html";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "login";
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "/logout";
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

	@RequestMapping(value="/consultaDati")
	public String ricerca() {
		return "ricerca.html";
	}
	@RequestMapping(value="/ricercaFotografo")
	public String ricercaFotografo() {
		return "ricercaFotografo.html";
	}

}
