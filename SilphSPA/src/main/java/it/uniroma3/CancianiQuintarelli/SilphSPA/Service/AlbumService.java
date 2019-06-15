package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Repository.AlbumRepository;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Album;

@Service
public class AlbumService {
	@Autowired
	AlbumRepository albumRepository;
	
	@Transactional
	public List<Album> trovaAlbumNome(String nome){
		return albumRepository.findByNome(nome);
	}
	@Transactional
	public Album trovaAlbumId(Long id) {
		try {
			return albumRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	@Transactional
	public Album salvaAlbum(Album a) {
		return albumRepository.save(a);
	}
}
