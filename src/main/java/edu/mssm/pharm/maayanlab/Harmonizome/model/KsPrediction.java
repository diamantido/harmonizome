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
@Table(name = "ks_prediction")
public class KsPrediction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "kinase_has_known_substrate")
	private boolean kinaseHasKnownSubstrate;
	
	@Column(name = "substrate_has_known_kinase")
	private boolean substrateHasKnownKinase;
	
	@Column(name = "in_training_set")
	private boolean inTrainingSet;
	
	@Column(name = "known_interaction")
	private boolean knownInteraction;
	
	@Column(name = "predicted_interaction_above_50pct_probability")
	private boolean predictedInteractionAbove50pctProbability;

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
	@JoinColumn(name = "kinase_gene_fk")
	private Gene kinaseGene;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "substrate_gene_fk")
	private Gene substrateGene;
	
	public KsPrediction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isKinaseHasKnownSubstrate() {
		return kinaseHasKnownSubstrate;
	}

	public void setKinaseHasKnownSubstrate(boolean kinaseHasKnownSubstrate) {
		this.kinaseHasKnownSubstrate = kinaseHasKnownSubstrate;
	}

	public boolean isSubstrateHasKnownKinase() {
		return substrateHasKnownKinase;
	}

	public void setSubstrateHasKnownKinase(boolean substrateHasKnownKinase) {
		this.substrateHasKnownKinase = substrateHasKnownKinase;
	}

	public boolean isInTrainingSet() {
		return inTrainingSet;
	}

	public void setInTrainingSet(boolean inTrainingSet) {
		this.inTrainingSet = inTrainingSet;
	}

	public boolean isKnownInteraction() {
		return knownInteraction;
	}

	public void setKnownInteraction(boolean knownInteraction) {
		this.knownInteraction = knownInteraction;
	}

	public boolean isPredictedInteractionAbove50pctProbability() {
		return predictedInteractionAbove50pctProbability;
	}

	public void setPredictedInteractionAbove50pctProbability(boolean predictedInteractionAbove50pctProbability) {
		this.predictedInteractionAbove50pctProbability = predictedInteractionAbove50pctProbability;
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

	public Gene getKinaseGene() {
		return kinaseGene;
	}

	public void setKinaseGene(Gene kinaseGene) {
		this.kinaseGene = kinaseGene;
	}

	public Gene getSubstrateGene() {
		return substrateGene;
	}

	public void setSubstrateGene(Gene substrateGene) {
		this.substrateGene = substrateGene;
	}
}