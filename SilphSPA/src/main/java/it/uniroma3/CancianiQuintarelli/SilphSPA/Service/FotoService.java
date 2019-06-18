package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Foto;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fr;
	
	
	@Transactional
	public List<Foto> trovaFotoPerNome(String nomeFoto){
		return this.fr.findByNome(nomeFoto);
	}
	
	@Transactional
	public Foto salvaFoto(Foto foto) {
		return this.fr.save(foto);
	}
	
	@Transactional
	public Foto trovaFotoPerId(Long id) {
		try {
			return this.fr.findById(id).get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public List<Foto> tutti(){
		return (List<Foto>) this.fr.findAll();
	}
	
}
