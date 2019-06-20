package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fotografo implements Comparable<Fotografo> {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String cognome;
	private String fotoProfilo;
	
	public Fotografo() {}
	
	public Fotografo(String nome, String cognome, String fotoProfilo) {
		this.nome = nome;
		this.cognome = cognome;
		this.fotoProfilo = fotoProfilo;
		this.setAlbum(Collections.emptyList());
		this.setFoto(Collections.emptyList());
	}
	
	@OneToMany(mappedBy = "fotografo")
	List<Album> album;
	
	@OneToMany(mappedBy = "fotografo")
	List<Foto> foto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}

	public Long getId() {
		return id;
	}

	public List<Foto> getFoto() {
		return foto;
	}

	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}

	public String getFotoProfilo() {
		return fotoProfilo;
	}

	public void setFotoProfilo(String fotoProfilo) {
		this.fotoProfilo = fotoProfilo;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public int compareTo(Fotografo o) {
		return this.getId().compareTo(o.getId());
	}
}
