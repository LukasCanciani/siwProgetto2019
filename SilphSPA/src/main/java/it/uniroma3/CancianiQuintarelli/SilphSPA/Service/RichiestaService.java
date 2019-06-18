package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Richiesta;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Utente;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Repository.RichiestaRepository;

@Service
public class RichiestaService {
	
	@Autowired
	private RichiestaRepository rr;
	
	
	
	
	@Transactional
	public Richiesta salvaRichiesta(Richiesta richiesta) {
		return this.rr.save(richiesta);
	}
	
	@Transactional
	public Richiesta trovaRichestaPerId(Long id) {
		try {
			return this.rr.findById(id).get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public List<Richiesta> tutti(){
		return (List<Richiesta>) this.rr.findAll();
	}
	@Transactional
	public List<Richiesta> trovaRichiestaPerUtente(Utente u){
		return this.rr.findByUtente(u);
	}
	
}
