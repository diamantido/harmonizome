package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "gene")
public class Gene {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@Column(name = "ncbi_entrez_gene_id")
	private int ncbiEntrezGeneId;

	@Column(name = "ncbi_entrez_gene_url", length = 2083)
	private String ncbiEntrezGeneUrl;

	@OneToMany(mappedBy = "gene")
	private Set<Synonym> synonyms;

	@OneToMany(mappedBy = "gene")
	private Set<Feature> features;

	@OneToMany(mappedBy = "gene1")
	private Set<Gene> gene1s;

	@OneToMany(mappedBy = "gene2")
	private Set<Gene> gene2s;

	public Gene() {
	}

	public Gene(String symbol, String name, String description, int ncbiEntrezGeneId, String ncbiEntrezGeneUrl) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.ncbiEntrezGeneId = ncbiEntrezGeneId;
		this.ncbiEntrezGeneUrl = ncbiEntrezGeneUrl;
	}

	public int getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public int getNcbiEntrezGeneId() {
		return ncbiEntrezGeneId;
	}

	public void setNcbiEntrezGeneId(int ncbiEntrezGeneId) {
		this.ncbiEntrezGeneId = ncbiEntrezGeneId;
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

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Set<Gene> getGene1s() {
		return gene1s;
	}

	public void setGene1s(Set<Gene> gene1s) {
		this.gene1s = gene1s;
	}

	public Set<Gene> getGene2s() {
		return gene2s;
	}

	public void setGene2s(Set<Gene> gene2s) {
		this.gene2s = gene2s;
	}
}
