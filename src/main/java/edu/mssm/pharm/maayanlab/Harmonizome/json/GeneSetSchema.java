package edu.mssm.pharm.maayanlab.Harmonizome.json;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;

public class GeneSetSchema {
	
	Dataset dataset = null;
	Attribute attribute = null;
	List<Feature> features = null;

	public GeneSetSchema() {
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
}
