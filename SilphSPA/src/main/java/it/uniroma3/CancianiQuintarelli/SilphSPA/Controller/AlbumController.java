package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Album;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.AlbumForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
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
			 return "admin.html";
		 }else {
			 return "inserimentoAlbum.html";
		 } 
	}
}
