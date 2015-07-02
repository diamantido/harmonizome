package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Comparator;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;

public class ResourceComparator implements Comparator<Resource> {
	
	@Override
    public int compare(Resource r1, Resource r2) {
    	String dst1 = r1.getDatasets().iterator().next().getDatasetType().getName();
    	String dst2 = r2.getDatasets().iterator().next().getDatasetType().getName();
    	return dst1.compareTo(dst2);
    }
}