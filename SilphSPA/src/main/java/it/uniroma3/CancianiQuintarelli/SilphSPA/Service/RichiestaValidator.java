package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.CancianiQuintarelli.SilphSPA.Model.SilphStaff;

@Component
public class RichiestaValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClazz) {
		return SilphStaff.class.equals(aClazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"descrizione","required");
	}

}
