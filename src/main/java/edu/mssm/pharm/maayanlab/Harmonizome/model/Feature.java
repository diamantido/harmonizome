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

@Entity
@Table(name = "feature")
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "imported_value")
	private double importedValue;

	@Column(name = "standardized_value")
	private double standardizedValue;

	@Column(name = "threshold_value")
	private double thresholdValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_fk")
	private Gene gene;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_fk")
	private Attribute attribute;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;

	public Feature() {
	}

	public Feature(double importedValue, double standardizedValue, double thresholdValue, Gene gene, Attribute attribute, Dataset dataset) {
		this.importedValue = importedValue;
		this.standardizedValue = standardizedValue;
		this.thresholdValue = thresholdValue;
		this.gene = gene;
		this.attribute = attribute;
		this.dataset = dataset;
	}

	public int getId() {
		return id;
	}

	public double getImportedValue() {
		return importedValue;
	}

	public void setImportedValue(double importedValue) {
		this.importedValue = importedValue;
	}

	public double getStandardizedValue() {
		return standardizedValue;
	}

	public void setStandardizedValue(double standardizedValue) {
		this.standardizedValue = standardizedValue;
	}

	public double getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(double thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
}
