package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Animale;
import it.uniroma3.siw.repository.AnimaleRepository;
@Service
public class AnimaleService {
@Autowired AnimaleRepository animaleRepository;
	public Animale getAnimale(Long id) {
		return this.animaleRepository.findById(id).get();
	}
	public List<Animale> getRangeAnimali(int min, int max) {
		return this.animaleRepository.findByPesoInKgGreaterThanAndPesoInKgLessThan(min,max);
	}
}
