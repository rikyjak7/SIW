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
	private String name;
	private String Image;
	private Integer age;
	private String descrizione;
	private String pesoInKg;
	
	@ManyToOne
	private Specie animal_specie;
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return Image;
	}
	
	public void setImage(String image) {
		Image = image;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getPesoInKg() {
		return pesoInKg;
	}

	public void setPesoInKg(String pesoInKg) {
		this.pesoInKg = pesoInKg;
	}

	public Specie getAnimal_specie() {
		return animal_specie;
	}

	public void setAnimal_specie(Specie animal_specie) {
		this.animal_specie = animal_specie;
	}
}
