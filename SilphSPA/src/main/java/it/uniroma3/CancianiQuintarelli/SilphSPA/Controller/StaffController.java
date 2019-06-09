package it.uniroma3.CancianiQuintarelli.SilphSPA.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Repository.SilphStaffRepository;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Service.SilphStaffValidator;

@Controller
public class StaffController {
	
	@Autowired
	private SilphStaffValidator silphStaffValidator;
	
	@Autowired
	private SilphStaffRepository silphStaffRepository;
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("silphStaff") SilphStaff silphStaff, Model model, BindingResult bindingResult) {
		
		SilphStaff controll = silphStaffRepository.findById(silphStaff.getUserName()).get();
		this.silphStaffValidator.validate(silphStaff,bindingResult);
		if(!bindingResult.hasErrors()) {
			//Se non ha errori, controllare se è uguale a controll la password!! poi decidi se dare eerrrore o procedere
			return "";
		}
		else {
			return "";
		}
	}
}
