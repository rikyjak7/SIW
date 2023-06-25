package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Animale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String foto;
	private Integer eta;
	private String descrizione;
	private Integer pesoInKg;
	
	@ManyToOne
	private Specie animal_specie;
	
	public Animale(String nome, String foto, Integer eta, String descrizione, Integer pesoInKg) {
		this.nome=nome;
		this.foto=foto;
		this.eta=eta;
		this.descrizione=descrizione;
		this.pesoInKg=pesoInKg;
	}

	public Animale() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(animal_specie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animale other = (Animale) obj;
		return Objects.equals(animal_specie, other.animal_specie);
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
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Integer getEta() {
		return eta;
	}
	
	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getPesoInKg() {
		return pesoInKg;
	}

	public void setPesoInKg(Integer pesoInKg) {
		this.pesoInKg = pesoInKg;
	}

	public Specie getAnimal_specie() {
		return animal_specie;
	}

	public void setAnimal_specie(Specie animal_specie) {
		this.animal_specie = animal_specie;
	}
}
