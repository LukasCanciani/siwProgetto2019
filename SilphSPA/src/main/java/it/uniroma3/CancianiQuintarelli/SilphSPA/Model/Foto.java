package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String uri;
	
	@ManyToMany(mappedBy="foto")
	private List<Album> listaAlbum;
	
	@ManyToOne
	private Fotografo fotografo;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<Album> getListaAlbum() {
		return listaAlbum;
	}

	public void setListaAlbum(List<Album> album) {
		this.listaAlbum = album;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
}
