package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Specie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@NotBlank
	private String provenienza;
	
	@ManyToOne
	private Ambiente ambienteOspitante;
	
	@OneToMany(mappedBy="animal_specie")
	private List<Animale> animali;

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getProvenienza() {
		return provenienza;
	}

	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}

	public Ambiente getAmbienteOspitante() {
		return ambienteOspitante;
	}

	public void setAmbienteOspitante(Ambiente ambienteOspitante) {
		this.ambienteOspitante = ambienteOspitante;
	}

	public List<Animale> getAnimali() {
		return animali;
	}

	public void setAnimali(List<Animale> animali) {
		this.animali = animali;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ambienteOspitante, animali, descrizione, id, nome, provenienza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specie other = (Specie) obj;
		return Objects.equals(ambienteOspitante, other.ambienteOspitante) && Objects.equals(animali, other.animali)
				&& Objects.equals(descrizione, other.descrizione) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(provenienza, other.provenienza);
	}
	
	
}