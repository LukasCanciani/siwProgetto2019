package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class SilphStaff {
	
	@Id
	String username;
	
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
