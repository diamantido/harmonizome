package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;

public class AssociationsSchema {

	public String name;
	
	public List<Association> list;
	
	public AssociationsSchema() {
	}
	
	public AssociationsSchema(String name, List<Association> list) {
		this.name = name;
		this.list = list;
	}

	public String getAssociation() {
		return name;
	}

	public void setAssociation(String name) {
		this.name = name;
	}

	public List<Association> getList() {
		return list;
	}

	public void setList(List<Association> list) {
		this.list = list;
	}
}