package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec;

@Entity
@Table(name = "hgnc_root_family")
public class HgncRootFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "family_id", unique = true)
	private int familyId;

	@Column(name = "name", unique = true)
	private String name;

	/* Foreign key relationships
	 * ------------------------- */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "hgnc_root_families_to_genes", joinColumns = { @JoinColumn(name = "hgnc_root_family_fk") }, inverseJoinColumns = { @JoinColumn(name = "gene_fk") })
	private Set<Gene> genes;

	public HgncRootFamily() {
	}

	/* Getters & Setters 
	 * ----------------- */
	public int getId() {
		return id;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
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
	
	/* Utility functions
	 * ----------------- */
	public String getUrlEncodedName() throws UnsupportedEncodingException {
		return URLCodec.encode(name);
	}

	public String getEndpoint() {
		return "hgnc_root_family";
	}
}