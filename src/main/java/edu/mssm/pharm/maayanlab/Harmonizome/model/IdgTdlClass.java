package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "idg_tdl_class")
public class IdgTdlClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@OneToMany(mappedBy = "idgTdlClass")
	private Set<Gene> genes;
	
	public IdgTdlClass() {
	}

	public IdgTdlClass(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Gene> getGenes() {
		return genes;
	}

	public void setGenes(Set<Gene> genes) {
		this.genes = genes;
	}
}
