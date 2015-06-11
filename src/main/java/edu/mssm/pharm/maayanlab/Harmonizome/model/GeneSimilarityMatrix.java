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
@Table(name = "gene_similarity_matrix")
public class GeneSimilarityMatrix {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cosine_value")
	private double cosineValue;

	@Column(name = "standardized_cosine_value")
	private double standardizedCosineValue;

	@Column(name = "threshold_cosine_value")
	private double thresholdCosineValue;

	@Column(name = "mi_value")
	private double miValue;

	@Column(name = "standardized_mi_value")
	private double standardizedMiValue;

	@Column(name = "threshold_mi_value")
	private double thresholdMiValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene1_fk")
	private Gene gene1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene2_fk")
	private Gene gene2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;

	public GeneSimilarityMatrix() {
	}

	public GeneSimilarityMatrix(int id, double cosineValue, double standardizedCosineValue, double thresholdCosineValue, double miValue, double standardizedMiValue, double thresholdMiValue, Gene gene1,
			Gene gene2, Dataset dataset) {
		this.id = id;
		this.cosineValue = cosineValue;
		this.standardizedCosineValue = standardizedCosineValue;
		this.thresholdCosineValue = thresholdCosineValue;
		this.miValue = miValue;
		this.standardizedMiValue = standardizedMiValue;
		this.thresholdMiValue = thresholdMiValue;
		this.gene1 = gene1;
		this.gene2 = gene2;
		this.dataset = dataset;
	}

	public int getId() {
		return id;
	}

	public double getCosineValue() {
		return cosineValue;
	}

	public void setCosineValue(double cosineValue) {
		this.cosineValue = cosineValue;
	}

	public double getStandardizedCosineValue() {
		return standardizedCosineValue;
	}

	public void setStandardizedCosineValue(double standardizedCosineValue) {
		this.standardizedCosineValue = standardizedCosineValue;
	}

	public double getThresholdCosineValue() {
		return thresholdCosineValue;
	}

	public void setThresholdCosineValue(double thresholdCosineValue) {
		this.thresholdCosineValue = thresholdCosineValue;
	}

	public double getMiValue() {
		return miValue;
	}

	public void setMiValue(double miValue) {
		this.miValue = miValue;
	}

	public double getStandardizedMiValue() {
		return standardizedMiValue;
	}

	public void setStandardizedMiValue(double standardizedMiValue) {
		this.standardizedMiValue = standardizedMiValue;
	}

	public double getThresholdMiValue() {
		return thresholdMiValue;
	}

	public void setThresholdMiValue(double thresholdMiValue) {
		this.thresholdMiValue = thresholdMiValue;
	}

	public Gene getGene1() {
		return gene1;
	}

	public void setGene1(Gene gene1) {
		this.gene1 = gene1;
	}

	public Gene getGene2() {
		return gene2;
	}

	public void setGene2(Gene gene2) {
		this.gene2 = gene2;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
}
