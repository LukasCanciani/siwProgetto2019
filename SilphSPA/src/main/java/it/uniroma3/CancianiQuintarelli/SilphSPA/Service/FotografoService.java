package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Repository.FotografoRepository;

@Service
public class FotografoService {
	
	@Autowired
	private FotografoRepository fr;
	
	@Transactional
	public Fotografo salvaFotografo(Fotografo fotografo) {
		return this.fr.save(fotografo);
	}
	
	@Transactional
	public Fotografo trovaFotografoPerId(Long id) {
		
		try {
			return this.fr.findById(id).get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public List<Fotografo> trovaFotografoPerNome(String nomeFotografo){
		return this.fr.findByNome(nomeFotografo);
	}
	
	@Transactional
	public List<Fotografo> trovaFotografoPerCognome(String cognomeFotografo){
		return this.fr.findByCognome(cognomeFotografo);
	}
	
	@Transactional
	public List<Fotografo> trovaFotografoPerNomeCognome(String nomeFotografo, String cognomeFotografo){
		return this.fr.findByNomeAndCognome(nomeFotografo, cognomeFotografo);
	}
	
	@Transactional
	public List<Fotografo> trovaFotografoPerIdNomeCognoe(Long id, String nome, String cognome){
		return this.fr.findByIdAndNomeAndCognome(id, nome, cognome);
	}
}
