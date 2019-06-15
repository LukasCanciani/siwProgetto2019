package it.uniroma3.CancianiQuintarelli.SilphSPA.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Album;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>{
	public List<Album> findByNome(String Nome);
	public List<Album> findByFotografo(Fotografo fotografo);
}
