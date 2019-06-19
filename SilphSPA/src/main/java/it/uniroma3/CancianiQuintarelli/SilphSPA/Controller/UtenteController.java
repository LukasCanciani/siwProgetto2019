package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UtenteController {
	
	@GetMapping
	public Principal getUser(Principal user) {
		return user;
	}
}
