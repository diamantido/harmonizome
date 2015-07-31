package edu.mssm.pharm.maayanlab.Harmonizome.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class GeneBaseSchema {
	
	public List<Map<String, String>> genes = new ArrayList<Map<String, String>>();
	
	public GeneBaseSchema(List<Gene> genes) {
		Map<String, String> geneData = new HashMap<String, String>();
		//genes.add(genes);
	}
}
