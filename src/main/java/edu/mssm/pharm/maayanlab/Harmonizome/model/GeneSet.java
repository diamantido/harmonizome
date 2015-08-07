package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.List;

/* This class is not persistent. It is a concept that is only available to
 * the user. In the database, we have Attributes and Datasets, and a gene set
 * is an (Attribute, Dataset) pair. This class is useful in disambiguating
 * these concepts throughout the codebase.
 */
public class GeneSet {

	Attribute attribute = null;
	Dataset dataset = null;
	List<Feature> features = null;
	
	public static final String ENDPOINT = "gene_set";

	public GeneSet() {
	}

	public GeneSet(Attribute attribute, Dataset dataset) {
		this.attribute = attribute;
		this.dataset = dataset;
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

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	public String getEndpoint() {
		return ENDPOINT;
	}
}