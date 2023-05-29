package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.SpecieRepository;

@Component
public class SpecieValidator implements Validator{
	
	@Autowired SpecieRepository specieRepository;
	
	@Override
	  public void validate(Object o, Errors errors) {
		Specie specie = (Specie)o;
		if(specie.getNome()!=null && specie.getDescrizione()!=null && 
				specie.getProvenienza()!=null &&
				specieRepository.existsByNomeAndProvenienza
				(specie.getNome(), specie.getProvenienza())) {
			errors.reject("specie.duplicate");
		}
	}
	
	@Override
	  public boolean supports(Class<?> aClass) {
		return Specie.class.equals(aClass);
	}

}
