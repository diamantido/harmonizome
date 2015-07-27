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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "threshold_value")
	private double thresholdValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_fk")
	private Gene gene;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_fk")
	private Attribute attribute;

	public Feature() {
	}

	public Feature(double thresholdValue, Gene gene, Attribute attribute) {
		this.thresholdValue = thresholdValue;
		this.gene = gene;
		this.attribute = attribute;
	}

	public int getId() {
		return id;
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
}