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
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Foto;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.FotoForm;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
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
					 return "admin.html";
				 }
			 }
			 
			 return "inserimentoFoto.html";
		 }else {
			 return "inserimentoFoto.html";
		 } 
	}
}