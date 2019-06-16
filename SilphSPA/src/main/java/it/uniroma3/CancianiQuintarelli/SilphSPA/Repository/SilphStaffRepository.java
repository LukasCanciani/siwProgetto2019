package it.uniroma3.CancianiQuintarelli.SilphSPA.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;

@Repository
public interface SilphStaffRepository extends CrudRepository<SilphStaff, Long> {
	public SilphStaff findByUsernameAndPassword(String username, String password);
}
