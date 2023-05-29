package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Ambiente;

public interface AmbienteRepository extends CrudRepository<Ambiente,Long>{
	
	public boolean existsByNome(String nome);

}
