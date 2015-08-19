package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;

public class AttributeComparator implements Comparator<Attribute> {

    @Override
    public int compare(Attribute attribute1,Attribute attribute2) {
        return attribute1.getNameFromDataset().compareTo(attribute2.getNameFromDataset());
    }
}