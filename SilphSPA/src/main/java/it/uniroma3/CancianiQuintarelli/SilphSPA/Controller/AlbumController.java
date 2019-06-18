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

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Album;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.AlbumForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.StringaRicerca;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.AlbumService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.AlbumFormValidator;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotografoService;

@Controller
public class AlbumController {

	@Autowired
	AlbumService albumService;

	@Autowired
	FotografoService fotografoService;

	@Autowired
	AlbumFormValidator albumFormValidator;

	@RequestMapping(value="/inserisciAlbum", method = RequestMethod.POST )
	public String inserisciAlbum(@Valid @ModelAttribute("albumForm") AlbumForm albumForm, 
			BindingResult bindingResult, Model model) {
		albumFormValidator.validate(albumForm, bindingResult);
		if ( !bindingResult.hasErrors()) {
			Long id;
			try {id = Long.parseLong(albumForm.getIdFotografo());}
			catch (NumberFormatException e) {
				bindingResult.rejectValue("idFotografo", "wrong");
				return "inserimentoAlbum.html";
			}
			Fotografo fotografo = fotografoService.trovaFotografoPerId(id);
			if ( fotografo == null) {
				bindingResult.rejectValue("idFotografo", "wrong");
				return "inserimentoAlbum.html";
			}
			Album album = new Album();
			album.setNome(albumForm.getNome());
			album.setFotografo(fotografo);
			List<Album> albums = fotografo.getAlbum();
			albums.add(album);
			fotografo.setAlbum(albums);
			albumService.salvaAlbum(album);
			UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("username", details.getUsername());
			return "/admin.html";
		}else {
			return "inserimentoAlbum.html";
		} 
	}
	
	@RequestMapping(value="/album")
	public String album(Model model) {
		model.addAttribute("risultati", this.albumService.trovaTutti());
		return "listaAlbum.html";
	}
	@RequestMapping(value="/album/{id}", method=RequestMethod.GET)
	public String album(@PathVariable("id") Long id, Model model) {
		Album album = this.albumService.trovaAlbumId(id);
		if ( album == null) {
			return "/album";
		} else {
			model.addAttribute("album", album);
			return "album.html";
		}
	}
	
	@RequestMapping(value = "/ricercaAlbum")
	public String ricercaAlbum() {
		return "ricercaAlbum.html";
	}
	
	@RequestMapping(value = "/cercaAlbumPerNome")
	public String ricercaAlbumPerNome(Model model) {
		model.addAttribute("stringaRicerca", new StringaRicerca());
		return "ricercaAlbumNome";
	}
	
	@RequestMapping(value = "/cercaAlbumPerId")
	public String ricercaAlbumPerId(Model model) {
		model.addAttribute("stringaRicerca", new StringaRicerca());
		return "ricercaAlbumId";
	}
	
	@RequestMapping(value = "/risultatiAlbumId", method = RequestMethod.POST)
	public String risultatoRicercaPerId(@Valid @ModelAttribute("stringaRicerca") StringaRicerca stringaRicerca,Model model, BindingResult bd) {
		
		Long id = 0L;
		
		try {
			id = Long.parseLong(stringaRicerca.getStringa1());
		}catch (NumberFormatException e) {
			bd.rejectValue("stringa1", "wrong");
			return "ricercaAlbumId.html";
		}
		
		Album album = this.albumService.trovaAlbumId(id);
		
		if(album!=null) {
			model.addAttribute("album", album);
			return "album.html";
		}
		else {
			bd.rejectValue("stringa1", "wrong");
			return "ricercaAlbumId.html";
		}
	}
	
	@RequestMapping(value = "/risultatiAlbumNome", method = RequestMethod.POST)
	public String risultatoRicercaNome(@Valid @ModelAttribute("stringaRicerca") StringaRicerca stringaRicerca, Model model, BindingResult bd) {
		
		List<Album> albums = this.albumService.trovaAlbumNome(stringaRicerca.getStringa1());
		if(!albums.isEmpty()) {
			model.addAttribute("risultati", albums);
			return "listaAlbum.html"; 
		}
		else {
			bd.rejectValue("stringa1", "wrong");
			return "ricercaAlbumNome.html";
		}
	}
	
}
