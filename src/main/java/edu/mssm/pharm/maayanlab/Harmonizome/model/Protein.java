package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.io.UnsupportedEncodingException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec;

@Entity
@Table(name = "protein")
public class Protein {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	/* Foreign key relationships
	 * ------------------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gene_fk")
	private Gene gene;

	public Protein() {
	}
	
	/* Getters & Setters 
	 * ----------------- */
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
	
	/* Utility functions
	 * ----------------- */
	@Transient
	public String getUrlEncodedName() throws UnsupportedEncodingException {
		return URLCodec.encode(name);
	}

	@Transient
	public String getEndpoint() {
		return "protein";
	}
}
