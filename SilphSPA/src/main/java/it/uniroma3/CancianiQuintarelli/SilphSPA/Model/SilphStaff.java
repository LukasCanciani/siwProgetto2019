package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Entity
public class SilphStaff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	private String username;
	
	private String role;
	
	private String password;
	
	public SilphStaff() {}
	
	public SilphStaff(String userName, String role) {
		this.username = userName;
		this.role = role;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
