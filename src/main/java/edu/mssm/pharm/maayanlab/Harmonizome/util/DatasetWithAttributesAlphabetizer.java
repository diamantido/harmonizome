package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;


public class DatasetWithAttributesAlphabetizer implements Comparator<Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>>> {
	
    @Override
    public int compare(Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>> pair1, Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>> pair2) {
        return pair1.getLeft().getName().compareTo(pair2.getLeft().getName());
    }
}
