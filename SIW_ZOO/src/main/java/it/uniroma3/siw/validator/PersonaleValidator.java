package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.repository.PersonaleRepository;

@Component
public class PersonaleValidator implements Validator{
	
	@Autowired PersonaleRepository personaleRepository;
	
	@Override
	  public void validate(Object o, Errors errors) {
		Personale personale = (Personale)o;
		if(personale.getName()!=null && personale.getSurname()!=null && 
				personale.getAge()!=null && personale.getStipendio()!=null &&
				personaleRepository.existsByNameAndSurname
				(personale.getName(), personale.getSurname())) {
			errors.reject("personale.duplicate");
		}
	}
	
	@Override
	  public boolean supports(Class<?> aClass) {
		return Personale.class.equals(aClass);
	}

}
