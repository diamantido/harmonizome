package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

public class ClustergrammerRowSchema {

	String row_name;
	Float val;
	
	public ClustergrammerRowSchema(String row_name, Float val) {
		this.row_name = row_name;
		this.val = val;
	}
	
	public String getRow_name() {
		return row_name;
	}

	public void setRow_name(String row_name) {
		this.row_name = row_name;
	}

	public Float getVal() {
		return val;
	}

	public void setVal(Float val) {
		this.val = val;
	}
}
