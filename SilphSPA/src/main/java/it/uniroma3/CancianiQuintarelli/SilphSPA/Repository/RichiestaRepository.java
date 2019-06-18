package it.uniroma3.CancianiQuintarelli.SilphSPA.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Richiesta;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Utente;

@Repository
public interface RichiestaRepository extends CrudRepository<Richiesta, Long>{
	public List<Richiesta> findByUtente(Utente utente);
}
