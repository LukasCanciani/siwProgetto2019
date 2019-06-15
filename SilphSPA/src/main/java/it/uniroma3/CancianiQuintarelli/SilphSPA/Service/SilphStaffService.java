package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;
import it.uniroma3.CancianiQuintarelli.SilphSPA.Repository.SilphStaffRepository;

@Service
public class SilphStaffService {
	@Autowired
	SilphStaffRepository silphStaffRepository;
	
	@Transactional
	public SilphStaff login(String username, String password) {
		return silphStaffRepository.findByUsernameAndPassword(username, password);
	}
	
	@Transactional
	public void salvaSilphStaff(SilphStaff silphStaff) {
		this.silphStaffRepository.save(silphStaff);
	}
	
}
