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
@Table(name = "gene")
public class Gene {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "sybmol")
	private String symbol;

	@Column(name = "ncbi_entrez_gene_id")
	private String ncbiEntrezGeneId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "ncbi_entrez_gene_url")
	private String ncbiEntrezGeneUrl;

	@OneToMany(mappedBy = "gene")
	private Set<Synonym> synonyms;
	
	@OneToMany(mappedBy = "features")
	private Set<Feature> features;

	public Gene() {
	}

	public Gene(String symbol, String ncbiEntrezGeneId, String name, String description, String ncbiEntrezGeneUrl) {
		this.symbol = symbol;
		this.ncbiEntrezGeneId = ncbiEntrezGeneId;
		this.name = name;
		this.description = description;
		this.ncbiEntrezGeneUrl = ncbiEntrezGeneUrl;
	}

	public long getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getNcbiEntrezGeneId() {
		return ncbiEntrezGeneId;
	}

	public void setNcbiEntrezGeneId(String ncbiEntrezGeneId) {
		this.ncbiEntrezGeneId = ncbiEntrezGeneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNcbiEntrezGeneUrl() {
		return ncbiEntrezGeneUrl;
	}

	public void setNcbiEntrezGeneUrl(String ncbiEntrezGeneUrl) {
		this.ncbiEntrezGeneUrl = ncbiEntrezGeneUrl;
	}

	public Set<Synonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<Synonym> synonyms) {
		this.synonyms = synonyms;
	}	
}
