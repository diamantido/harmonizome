package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;

import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntity;

public class BioEntityAlphabetizer implements Comparator<BioEntity> {

    @Override
    public int compare(BioEntity entity1, BioEntity entity2) {
        return entity1.getValue().compareTo(entity2.getValue());
    }
}