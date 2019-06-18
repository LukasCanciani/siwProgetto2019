package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffService;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffValidator;

@Controller
public class SystemController {
	
	@Autowired
	private SilphStaffValidator silphStaffValidator;
	
	@Autowired
	private SilphStaffService silphStaffService;
	
/*	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		return "loginForm";
	} */
	
	@RequestMapping(value ={ "/", "/index","/home" })
	public String loginForm(Model model) {
		/*SilphStaff ss = new SilphStaff();
		ss.setUsername("admin");
		ss.setRole("admin");
		String adminPassword = new BCryptPasswordEncoder().encode("admin");
		ss.setPassword(adminPassword);
		silphStaffService.salvaSilphStaff(ss);*/
		return "index.html";
	}
}
