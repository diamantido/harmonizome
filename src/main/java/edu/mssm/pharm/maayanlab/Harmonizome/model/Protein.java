package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "protein")
public class Protein {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@Column(name = "uniprot_id")
	private String uniprotId;

	@Column(name = "uniprot_url")
	private String uniprotUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_fk")
	private Gene gene;

	public Protein() {
	}
	
	public Protein(String name, String symbol, String description, String uniprotId, String uniprotUrl, Gene gene) {
		this.name = name;
		this.symbol = symbol;
		this.description = description;
		this.uniprotId = uniprotId;
		this.uniprotUrl = uniprotUrl;
		this.gene = gene;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUniprotId() {
		return uniprotId;
	}

	public void setUniprotId(String uniprotId) {
		this.uniprotId = uniprotId;
	}

	public String getUniprotUrl() {
		return uniprotUrl;
	}

	public void setUniprotUrl(String uniprotUrl) {
		this.uniprotUrl = uniprotUrl;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}
}
