package edu.mssm.pharm.maayanlab.Harmonizome.json;

import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;


public class GenePageSchema {

	private Gene gene;
	
	private Map<String, List<Map<String, String>>> summaryMatrix;
	
	public GenePageSchema() {
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Map<String, List<Map<String, String>>> getSummaryMatrix() {
		return summaryMatrix;
	}

	public void setSummaryMatrix(Map<String, List<Map<String, String>>> summaryMatrix) {
		this.summaryMatrix = summaryMatrix;
	}
}