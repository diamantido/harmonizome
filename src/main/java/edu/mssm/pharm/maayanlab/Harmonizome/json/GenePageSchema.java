package edu.mssm.pharm.maayanlab.Harmonizome.json;

import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;


public class GenePageSchema {

	private Gene gene;
	
	private Map<String, Object> summaryMatrix;
	
	public GenePageSchema() {
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Map<String, Object> getSummaryMatrix() {
		return summaryMatrix;
	}

	public void setSummaryMatrix(Map<String, Object> summaryMatrix) {
		this.summaryMatrix = summaryMatrix;
	}
}