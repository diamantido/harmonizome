package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;

public class GeneComparator implements Comparator<Gene> {
	
	@Override
	public int compare(final Gene gene1, final Gene gene2) {
        return gene1.getSymbol().compareTo(gene2.getSymbol());
    }
}