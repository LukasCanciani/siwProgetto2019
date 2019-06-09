package it.uniroma3.CancianiQuintarelli.SilphSPA.Model;

import javax.persistence.Entity;

import com.vaadin.flow.component.polymertemplate.Id;

@Entity
public class SilphStaff {
	
	@Id
	String userName;
	
	String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
