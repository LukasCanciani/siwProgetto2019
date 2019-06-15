package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffValidator;

@Controller
public class HomeController {
	
	@Autowired
	private SilphStaffValidator silphStaffValidator;
	
	@Autowired
	private SilphStaffService silphStaffService;
	
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		return "loginForm";
	}
	
	@RequestMapping(value ="/index")
	public String loginForm(Model model) {
		return "index.html";
	}
}
