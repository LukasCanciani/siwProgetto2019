package it.uniroma3.CancianiQuintarelli.SilphSPA.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Foto;

@Repository
public interface FotoRepository extends CrudRepository<Foto, Long> {
	public List<Foto> findByNome(String nome);
}
