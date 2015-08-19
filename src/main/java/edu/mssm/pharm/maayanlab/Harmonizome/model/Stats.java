package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "num_datasets")
	private Long numDatasets;
	
	@Column(name = "num_genes")
	private Long numGenes;
	
	@Column(name = "num_attributes")
	private Long numAttributes;
	
	@Column(name = "num_resources")
	private Long numResources;
	
	@Column(name = "num_features")
	private Long numFeatures;
	
	public Stats() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getNumDatasets() {
		return numDatasets;
	}

	public void setNumDatasets(Long numDatasets) {
		this.numDatasets = numDatasets;
	}

	public Long getNumGenes() {
		return numGenes;
	}

	public void setNumGenes(Long numGenes) {
		this.numGenes = numGenes;
	}

	public Long getNumAttributes() {
		return numAttributes;
	}

	public void setNumAttributes(Long numAttributes) {
		this.numAttributes = numAttributes;
	}

	public Long getNumResources() {
		return numResources;
	}

	public void setNumResources(Long numResources) {
		this.numResources = numResources;
	}

	public Long getNumFeatures() {
		return numFeatures;
	}

	public void setNumFeatures(Long numFeatures) {
		this.numFeatures = numFeatures;
	}
}