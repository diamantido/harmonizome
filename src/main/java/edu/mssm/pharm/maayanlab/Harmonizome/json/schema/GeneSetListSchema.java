package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;

public class GeneSetListSchema {

	public String association;
	
	public List<GeneSet> list;
	
	public GeneSetListSchema() {
	}
	
	public GeneSetListSchema(String association, List<GeneSet> list) {
		this.association = association;
		this.list = list;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public List<GeneSet> getList() {
		return list;
	}

	public void setList(List<GeneSet> list) {
		this.list = list;
	}
}