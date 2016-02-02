package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;

public class GeneSetAssociationsSchema {

	public Gene gene;

	public Dataset dataset;

	public AssociationsSchema up;

	public AssociationsSchema down;

	public GeneSetAssociationsSchema() {
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public AssociationsSchema getUp() {
		return up;
	}

	public void setUp(AssociationsSchema up) {
		this.up = up;
	}

	public AssociationsSchema getDown() {
		return down;
	}

	public void setDown(AssociationsSchema down) {
		this.down = down;
	}
}
