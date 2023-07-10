package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Personale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
    private String name;



	@NotBlank
    private String surname;
	
	@NotNull
    private Integer age;
	
	@Column(length = 100000000)//max 10Mb
    private String Image;
    
    @NotNull
    @Min(100)
    private Float stipendio;
    
	@OneToOne
	Ambiente ambienteControllato;

	public Ambiente getAmbienteControllato() {
		return ambienteControllato;
	}
	public void setAmbienteControllato(Ambiente ambienteControllato) {
		this.ambienteControllato = ambienteControllato;
	}
	@Override
	public int hashCode() {
		return Objects.hash(stipendio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personale other = (Personale) obj;
		return Objects.equals(stipendio, other.stipendio);
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
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getImage() {
		return Image;
	}
	
	public void setImage(String Image) {
		this.Image = Image;
	}
	
	public Float getStipendio() {
		return stipendio;
	}
	
	public void setStipendio(Float stipendio) {
		this.stipendio = stipendio;
	}
	private Boolean isResponsabile;
	
	public Boolean getIsResponsabile() {
		return isResponsabile;
	}
	public void setIsResponsabile(Boolean isResponsabile) {
		this.isResponsabile = isResponsabile;
	}
}