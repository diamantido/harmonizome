package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;


public class DatasetWithAttributesAlphabetizer implements Comparator<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> {
	
    @Override
    public int compare(Pair<Dataset, Pair<List<Attribute>, List<Attribute>>> pair1, Pair<Dataset, Pair<List<Attribute>, List<Attribute>>> pair2) {
        return pair1.getLeft().getName().compareTo(pair2.getLeft().getName());
    }
}
