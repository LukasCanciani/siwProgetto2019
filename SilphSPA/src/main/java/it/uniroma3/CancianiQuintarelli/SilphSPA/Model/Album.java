package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	public Album() {}
	
	public Album(String nome, Fotografo fotografo) {
		this.nome = nome;
		this.fotografo = fotografo;
		this.setFoto(Collections.emptyList());
	}
	
	@ManyToOne
	private Fotografo fotografo;
	
	@OneToMany(mappedBy="album")
	private List<Foto> foto;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

	public List<Foto> getFoto() {
		return foto;
	}

	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}
	
	
	

}
