package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;

public class DatasetListSchema {

	public int status = HttpStatusCode.OK.getValue();
	
	public String message = HttpStatusCode.OK.getMessage();
	
	public List<Dataset> datasets;
	
	public DatasetListSchema() {
	}
	
	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}
}
