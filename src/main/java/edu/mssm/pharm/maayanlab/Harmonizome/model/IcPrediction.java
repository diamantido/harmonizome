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
@Table(name = "ic_prediction")
public class IcPrediction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "in_training_set")
	private boolean inTrainingSet;

	@Column(name = "known_ion_channel")
	private boolean knownIonChannel;
	
	@Column(name = "predicted_ion_channel_above_50pct_probability")
	private boolean predictedIonChannelAbove50pctProbability;

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
	
	@Column(name = "similar_ion_channel_1")
	private String similarIonChannel1;
	
	@Column(name = "similar_ion_channel_2")
	private String similarIonChannel2;

	@Column(name = "similar_ion_channel_3")
	private String similarIonChannel3;

	@Column(name = "similar_ion_channel_4")
	private String similarIonChannel4;

	@Column(name = "similar_ion_channel_5")
	private String similarIonChannel5;

	@Column(name = "similar_ion_channel_6")
	private String similarIonChannel6;

	@Column(name = "similar_ion_channel_7")
	private String similarIonChannel7;

	@Column(name = "similar_ion_channel_8")
	private String similarIonChannel8;
	
	@Column(name = "similar_ion_channel_9")
	private String similarIonChannel9;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gene_fk")
	private Gene gene;
	
	public IcPrediction() {
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

	public boolean isKnownIonChannel() {
		return knownIonChannel;
	}

	public void setKnownIonChannel(boolean knownIonChannel) {
		this.knownIonChannel = knownIonChannel;
	}

	public boolean isPredictedIonChannelAbove50pctProbability() {
		return predictedIonChannelAbove50pctProbability;
	}

	public void setPredictedIonChannelAbove50pctProbability(boolean predictedIonChannelAbove50pctProbability) {
		this.predictedIonChannelAbove50pctProbability = predictedIonChannelAbove50pctProbability;
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

	public String getSimilarIonChannel1() {
		return similarIonChannel1;
	}

	public void setSimilarIonChannel1(String similarIonChannel1) {
		this.similarIonChannel1 = similarIonChannel1;
	}

	public String getSimilarIonChannel2() {
		return similarIonChannel2;
	}

	public void setSimilarIonChannel2(String similarIonChannel2) {
		this.similarIonChannel2 = similarIonChannel2;
	}

	public String getSimilarIonChannel3() {
		return similarIonChannel3;
	}

	public void setSimilarIonChannel3(String similarIonChannel3) {
		this.similarIonChannel3 = similarIonChannel3;
	}

	public String getSimilarIonChannel4() {
		return similarIonChannel4;
	}

	public void setSimilarIonChannel4(String similarIonChannel4) {
		this.similarIonChannel4 = similarIonChannel4;
	}

	public String getSimilarIonChannel5() {
		return similarIonChannel5;
	}

	public void setSimilarIonChannel5(String similarIonChannel5) {
		this.similarIonChannel5 = similarIonChannel5;
	}

	public String getSimilarIonChannel6() {
		return similarIonChannel6;
	}

	public void setSimilarIonChannel6(String similarIonChannel6) {
		this.similarIonChannel6 = similarIonChannel6;
	}

	public String getSimilarIonChannel7() {
		return similarIonChannel7;
	}

	public void setSimilarIonChannel7(String similarIonChannel7) {
		this.similarIonChannel7 = similarIonChannel7;
	}

	public String getSimilarIonChannel8() {
		return similarIonChannel8;
	}

	public void setSimilarIonChannel8(String similarIonChannel8) {
		this.similarIonChannel8 = similarIonChannel8;
	}

	public String getSimilarIonChannel9() {
		return similarIonChannel9;
	}

	public void setSimilarIonChannel9(String similarIonChannel9) {
		this.similarIonChannel9 = similarIonChannel9;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}
}