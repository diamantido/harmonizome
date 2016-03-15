package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.ArrayList;
import java.util.List;

public class ClustergrammerColumnSchema {

	String col_name;
	List<ClustergrammerRowSchema> data = new ArrayList<ClustergrammerRowSchema>();
	
	public ClustergrammerColumnSchema(String col_name) {
		this.col_name = col_name;
	}

	public String getCol_name() {
		return col_name;
	}

	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	public List<ClustergrammerRowSchema> getData() {
		return data;
	}

	public void setData(List<ClustergrammerRowSchema> data) {
		this.data = data;
	}
}
