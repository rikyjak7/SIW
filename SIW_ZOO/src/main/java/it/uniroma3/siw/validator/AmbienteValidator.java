package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.repository.AmbienteRepository;

@Component
public class AmbienteValidator implements Validator{
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@Override
	  public void validate(Object o, Errors errors) {
		Ambiente ambiente = (Ambiente)o;
		if(ambiente.getNome()!=null && ambiente.getSuperficie()!=null && 
				ambiente.getDescrizione()!=null 
				&& ambienteRepository.existsByNome(ambiente.getNome())) {
			errors.reject("ambiente.duplicate");
		}
	}
	
	@Override
	  public boolean supports(Class<?> aClass) {
		return Ambiente.class.equals(aClass);
	}

}
