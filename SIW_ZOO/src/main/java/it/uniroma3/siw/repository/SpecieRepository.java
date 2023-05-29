package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;

public interface SpecieRepository extends CrudRepository<Specie,Long>{
	
	public boolean existsByNomeAndProvenienza(String nome, String provenienza);

}
