package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.ParameterParser;
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
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Foto;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.FotoForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.StringaRicerca;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.AlbumService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotoFormValidator;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotoService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotografoService;

@Controller
public class FotoController {

	@Autowired
	FotoService fotoService;

	@Autowired
	AlbumService albumService;

	@Autowired
	FotografoService fotografoService;

	@Autowired
	FotoFormValidator fotoFormValidator;

	@RequestMapping(value="/inserisciFoto", method = RequestMethod.POST )
	public String inserisciFoto(@Valid @ModelAttribute("fotoForm") FotoForm fotoForm, 
			BindingResult bindingResult, Model model) {
		fotoFormValidator.validate(fotoForm, bindingResult);
		if ( !bindingResult.hasErrors()) {
			Long id;
			try {id = Long.parseLong(fotoForm.getIdFotografo());}
			catch (NumberFormatException e) {
				bindingResult.rejectValue("idFotografo", "wrong");
				return "inserimentoFoto.html";
			}
			Fotografo fotografo = fotografoService.trovaFotografoPerId(id);
			if ( fotografo == null) {
				bindingResult.rejectValue("idFotografo", "wrong");
				return "inserimentoFoto.html";
			}

			Long id2;
			try {id2 = Long.parseLong(fotoForm.getIdAlbum());}
			catch (NumberFormatException e) {
				bindingResult.rejectValue("idAlbum", "wrong");
				return "inserimentoFoto.html";
			}
			Album album = albumService.trovaAlbumId(id2);
			if ( album == null) {
				bindingResult.rejectValue("idAlbum", "wrong");
				return "inserimentoFoto.html";
			}

			for(Album alb : this.albumService.trovaAlbumsAssociatiAUnFotografo(fotografo)) {
				if(alb.getId()==album.getId()) {
					Foto foto = new Foto();
					foto.setNome(fotoForm.getNome());
					foto.setUri(fotoForm.getUri());
					foto.setFotografo(fotografo);
					foto.setAlbum(album);
					List<Foto> listaFoto = album.getFoto();
					listaFoto.add(foto);
					album.setFoto(listaFoto);
					listaFoto.clear();
					listaFoto = fotografo.getFoto();
					listaFoto.add(foto);
					fotografo.setFoto(listaFoto);
					this.fotoService.salvaFoto(foto);
					UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					model.addAttribute("username", details.getUsername());
					return "/admin.html";
				}
			}

			bindingResult.rejectValue("idAlbum", "wrong");
			return "inserimentoFoto.html";
		}else {
			return "inserimentoFoto.html";
		} 
	}
	@RequestMapping(value="/foto",method=RequestMethod.GET)
	public String listaFoto(Model model) {
		model.addAttribute("risultati", this.fotoService.tutti());
		return "listaFoto.html";
	}
	@RequestMapping(value="/foto/{id}", method=RequestMethod.GET)
	public String album(@PathVariable("id") Long id, Model model) {
		Foto foto = this.fotoService.trovaFotoPerId(id);
		if ( foto == null) {
			return "redirect:/foto";
		} else {
			model.addAttribute("foto", foto);
			return "foto.html";
		}
	}
	
	@RequestMapping(value = "/ricercaFoto")
	public String cercaFoto() {
		return "ricercaFoto.html";
	}
	
	@RequestMapping(value = "/cercaFotoPerId")
	public String cercaFotoPerId(Model model) {
		model.addAttribute("stringaRicerca", new StringaRicerca());
		return "ricercaFotoId.html";
	}
	
	@RequestMapping(value = "/cercaFotoPerNome")
	public String cercaFotoPerNome(Model model) {
		model.addAttribute("stringaRicerca", new StringaRicerca());
		return "ricercaFotoNome";
	}
	
	@RequestMapping(value = "/risultatiFotoNome", method = RequestMethod.POST)
	public String risultatoRicercaNome(@Valid @ModelAttribute("stringaRicerca") StringaRicerca stringaRicerca, Model model , BindingResult bindingResult) {
		List<Foto> lf = this.fotoService.trovaFotoPerNome(stringaRicerca.getStringa1());
		if(!lf.isEmpty()) {
			model.addAttribute("risultati", lf);
			return "listaFoto";
		}
		else {
			bindingResult.rejectValue("stringa1", "wrong");
			return "ricercaFotoNome.html";
		}
	}
	
	@RequestMapping(value = "/risultatiFotoId", method = RequestMethod.POST)
	public String risultatoRicercaId(@Valid @ModelAttribute("stringaRicerca") StringaRicerca stringaRicerca, Model model , BindingResult bindingResult) {
		Long id =0L;
		try {
			id = Long.parseLong(stringaRicerca.getStringa1());
		}catch (NumberFormatException e) {
			bindingResult.rejectValue("stringa1", "wrong");
			return "ricercaFotoId.html";
		}
		Foto foto = this.fotoService.trovaFotoPerId(id);
		if(foto!=null) {
			model.addAttribute("foto", foto);
			return "foto";
		}
		else {
			bindingResult.rejectValue("stringa1", "wrong");
			return "ricercaFotoId.html";
		}
	}
}
