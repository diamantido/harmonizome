package edu.mssm.pharm.maayanlab.Harmonizome.model;

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

@Entity
@Table(name = "hgnc_terminal_family")
public class HgncTerminalFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "family_id", unique = true)
	private int familyId;

	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "url", length = 2083)
	private String url;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "hgnc_terminal_families_to_genes", joinColumns = { @JoinColumn(name = "hgnc_terminal_family_fk") }, inverseJoinColumns = { @JoinColumn(name = "gene_fk") })
	private Set<Gene> genes;

	public HgncTerminalFamily() {
	}

	public HgncTerminalFamily(int familyId, String name, String url, Set<Gene> genes) {
		this.familyId = familyId;
		this.name = name;
		this.url = url;
		this.genes = genes;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Gene> getGenes() {
		return genes;
	}

	public void setGenes(Set<Gene> genes) {
		this.genes = genes;
	}
}