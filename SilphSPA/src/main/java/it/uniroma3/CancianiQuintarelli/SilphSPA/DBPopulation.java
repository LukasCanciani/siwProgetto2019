package it.uniroma3.CancianiQuintarelli.SilphSPA;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Album;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Foto;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.Fotografo;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.AlbumService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotoService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.FotografoService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffService;

@Component
public class DBPopulation implements ApplicationRunner{

	@Autowired
	private SilphStaffService silphStaffService; 

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private AlbumService albumService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.populateDB();
	}

	private void populateDB() throws IOException, InterruptedException{

		if(this.silphStaffService.tutti().isEmpty() && this.fotografoService.tutti().isEmpty() && this.albumService.trovaTutti().isEmpty() && this.fotoService.tutti().isEmpty()) {
			SilphStaff silphstaff = new SilphStaff("admin", "admin");
			String adminPassword = new BCryptPasswordEncoder().encode("admin");
			silphstaff.setPassword(adminPassword);
			Fotografo fotografo1 = new Fotografo("Mario", "Rossi", "https://bit.ly/2Rouwnu");
			Fotografo fotografo2 = new Fotografo("Paolo", "Bianchi", "https://bit.ly/2L4RfUv");
			Album album1 = new Album("natura", fotografo1);
			Album album2 = new Album("pianeti", fotografo1);
			Album album3 = new Album("macchine", fotografo2);
			Foto foto1 = new Foto("foto1", "https://bit.ly/2XnxgqS", album1, fotografo1);
			Foto foto2 = new Foto("foto2", "https://bit.ly/2WUVYzb", album2, fotografo1);
			Foto foto3 = new Foto("foto3", "https://bit.ly/2IU5k4E", album3, fotografo2);
			Foto foto4 = new Foto("foto4","https://bit.ly/2XtUf3g", album3, fotografo2);

			this.silphStaffService.salvaSilphStaff(silphstaff);
			this.fotografoService.salvaFotografo(fotografo1);
			this.fotografoService.salvaFotografo(fotografo2);
			this.albumService.salvaAlbum(album1);
			this.albumService.salvaAlbum(album2);
			this.albumService.salvaAlbum(album3);
			this.fotoService.salvaFoto(foto1);
			this.fotoService.salvaFoto(foto2);
			this.fotoService.salvaFoto(foto3);
			this.fotoService.salvaFoto(foto4);	
		}
	}	
}
