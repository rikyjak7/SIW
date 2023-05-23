package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Animale;

public interface AnimaleRepository extends CrudRepository<Animale, Long>  {

}
