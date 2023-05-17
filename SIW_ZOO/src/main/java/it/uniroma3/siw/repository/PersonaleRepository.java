package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Personale;

public interface PersonaleRepository extends CrudRepository<Personale,Long>{

	public boolean existsByNameAndSurname(String name, String surname);	
}
