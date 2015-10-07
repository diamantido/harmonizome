package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;

public class GeneSetAssociationsSchema {

	public Gene gene;

	public Dataset dataset;

	public GeneSetListSchema up;

	public GeneSetListSchema down;

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

	public GeneSetListSchema getUp() {
		return up;
	}

	public void setUp(GeneSetListSchema up) {
		this.up = up;
	}

	public GeneSetListSchema getDown() {
		return down;
	}

	public void setDown(GeneSetListSchema down) {
		this.down = down;
	}
}
