package it.uniroma3.CancianiQuintarelli.SilphSPA.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;

@Repository
public interface FotografoRepository extends CrudRepository<Fotografo, Long> {
	List<Fotografo> findByNome(String nome);
	List<Fotografo> findByNomeAndCognome(String nome, String Cognome);
	List<Fotografo> findByCognome(String cognome);
	List<Fotografo> findByIdAndNomeAndCognome(Long id, String nome, String cognome);
}
