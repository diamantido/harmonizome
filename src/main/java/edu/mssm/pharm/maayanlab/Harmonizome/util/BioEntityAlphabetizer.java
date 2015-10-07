package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;

import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntity;

public class BioEntityAlphabetizer implements Comparator<BioEntity> {

    @Override
    public int compare(BioEntity entity1, BioEntity entity2) {
    	String value1 = entity1.getValue().toLowerCase();
    	String value2 = entity2.getValue().toLowerCase();
        return value1.compareTo(value2);
    }
}