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
@Table(name = "gl_prediction")
public class GlPrediction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "gpcr_has_known_ligand")
	private boolean gpcrHasKnownLigand;
	
	@Column(name = "ligand_has_known_gpcr")
	private boolean ligandHasKnownGpcr;
	
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
	@JoinColumn(name = "gpcr_gene_fk")
	private Gene gpcrGene;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ligand_gene_fk")
	private Gene ligandGene;
	
	public GlPrediction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isGpcrHasKnownLigand() {
		return gpcrHasKnownLigand;
	}

	public void setGpcrHasKnownLigand(boolean gpcrHasKnownLigand) {
		this.gpcrHasKnownLigand = gpcrHasKnownLigand;
	}

	public boolean isLigandHasKnownGpcr() {
		return ligandHasKnownGpcr;
	}

	public void setLigandHasKnownGpcr(boolean ligandHasKnownGpcr) {
		this.ligandHasKnownGpcr = ligandHasKnownGpcr;
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

	public Gene getGpcrGene() {
		return gpcrGene;
	}

	public void setGpcrGene(Gene gpcrGene) {
		this.gpcrGene = gpcrGene;
	}

	public Gene getLigandGene() {
		return ligandGene;
	}

	public void setLigandGene(Gene ligandGene) {
		this.ligandGene = ligandGene;
	}
}