package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String uri;
	
	public Foto() {}
	
	public Foto(String nome, String uri, Album album, Fotografo fotografo) {
		this.nome = nome;
		this.uri = uri;
		this.album = album;
		this.fotografo = fotografo;
	}
	
	@ManyToOne
	private Album album;
	
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

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Foto foto = (Foto) obj;
		return this.id.equals(foto.getId());
	}
}
