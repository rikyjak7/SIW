package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Ambiente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String image;
	private Integer grandezzaInMQ;
	private String descrizione;
	
	@OneToOne
	private Personale responsabile;
	
	@OneToMany
	private List<Personale> dipendente;
	
	@OneToMany(mappedBy="ambienteOspitante")
	private List<Specie> specieOspitate;
	
	@Override
	public int hashCode() {
		return Objects.hash(grandezzaInMQ);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ambiente other = (Ambiente) obj;
		return Objects.equals(grandezzaInMQ, other.grandezzaInMQ);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public Integer getGrandezzaInMQ() {
		return grandezzaInMQ;
	}
	
	public void setGrandezzaInMQ(Integer grandezzaInMQ) {
		this.grandezzaInMQ = grandezzaInMQ;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Personale getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(Personale responsabile) {
		this.responsabile = responsabile;
	}

	public List<Personale> getDipendente() {
		return dipendente;
	}

	public void setDipendente(List<Personale> dipendente) {
		this.dipendente = dipendente;
	}

	public List<Specie> getSpecieOspitate() {
		return specieOspitate;
	}

	public void setSpecieOspitate(List<Specie> specieOspitate) {
		this.specieOspitate = specieOspitate;
	}
}