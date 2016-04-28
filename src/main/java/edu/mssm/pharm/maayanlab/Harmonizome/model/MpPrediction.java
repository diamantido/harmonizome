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
@Table(name = "mp_prediction")
public class MpPrediction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "in_training_set")
	private boolean inTrainingSet;
	
	@Column(name = "known_association")
	private boolean knownAssociation;
	
	@Column(name = "predicted_association_above_50pct_probability")
	private boolean predictedAssociationAbove50pctProbability;

	@Column(name = "probability")
	private double probability;

	@Column(name = "false_positive_rate")
	private double falsePositiveRate;

	@Column(name = "true_positive_rate")
	private double truePositiveRate;

	@Column(name = "misclassification_rate")
	private double misclassificationRate;

	@Column(name = "false_discovery_rate")
	private double falseDiscoveryRate;

	@Column(name = "f1_score")
	private double f1_score;

	@Column(name = "matthews_correlation_coefficient")
	private boolean matthewsCorrelationCoefficient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_fk")
	private Gene gene;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_set_fk")
	private GeneSet geneSet;
	
	public MpPrediction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isInTrainingSet() {
		return inTrainingSet;
	}

	public void setInTrainingSet(boolean inTrainingSet) {
		this.inTrainingSet = inTrainingSet;
	}

	public boolean isKnownAssociation() {
		return knownAssociation;
	}

	public void setKnownAssociation(boolean knownAssociation) {
		this.knownAssociation = knownAssociation;
	}

	public boolean isPredictedAssociationAbove50pctProbability() {
		return predictedAssociationAbove50pctProbability;
	}

	public void setPredictedAssociationAbove50pctProbability(boolean predictedAssociationAbove50pctProbability) {
		this.predictedAssociationAbove50pctProbability = predictedAssociationAbove50pctProbability;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getFalsePositiveRate() {
		return falsePositiveRate;
	}

	public void setFalsePositiveRate(double falsePositiveRate) {
		this.falsePositiveRate = falsePositiveRate;
	}

	public double getTruePositiveRate() {
		return truePositiveRate;
	}

	public void setTruePositiveRate(double truePositiveRate) {
		this.truePositiveRate = truePositiveRate;
	}

	public double getMisclassificationRate() {
		return misclassificationRate;
	}

	public void setMisclassificationRate(double misclassificationRate) {
		this.misclassificationRate = misclassificationRate;
	}

	public double getFalseDiscoveryRate() {
		return falseDiscoveryRate;
	}

	public void setFalseDiscoveryRate(double falseDiscoveryRate) {
		this.falseDiscoveryRate = falseDiscoveryRate;
	}

	public double getF1_score() {
		return f1_score;
	}

	public void setF1_score(double f1_score) {
		this.f1_score = f1_score;
	}

	public boolean isMatthewsCorrelationCoefficient() {
		return matthewsCorrelationCoefficient;
	}

	public void setMatthewsCorrelationCoefficient(boolean matthewsCorrelationCoefficient) {
		this.matthewsCorrelationCoefficient = matthewsCorrelationCoefficient;
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
}