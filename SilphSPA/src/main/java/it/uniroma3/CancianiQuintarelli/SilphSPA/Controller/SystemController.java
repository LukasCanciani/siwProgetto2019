package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {
	

	
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
