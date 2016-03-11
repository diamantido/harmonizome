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
import javax.persistence.Transient;

@Entity
@Table(name = "association")
public class Association {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "threshold_value")
	private double thresholdValue;
	
	@Column(name = "standardized_value")
	// This score is null for about half the datasets.
	private Double standardizedValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_fk")
	private Gene gene;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_set_fk")
	private GeneSet geneSet;

	/* Utilities
	 * --------- */
	public static final String ENDPOINT = "association";
	
	public Association() {
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public double getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(double thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	
	public Double getStandardizedValue() {
		return standardizedValue;
	}

	public void setStandardizedValue(Double standardizedValue) {
		this.standardizedValue = standardizedValue;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public GeneSet getGeneSet() {
		return geneSet;
	}

	public void setGeneSet(GeneSet geneSet) {
		this.geneSet = geneSet;
	}
	
	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}