package it.uniroma3.CancianiQuintarelli.SilphSPA.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>{
	List<Album> findByNome(String Nome);
}
