package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;
import java.util.Map;

public class ScriptConfigSchema {

	@SuppressWarnings("unused")
	private Map<String, String> datasets;
	
	@SuppressWarnings("unused")
	private List<String> downloads;

	public ScriptConfigSchema(Map<String, String> datasets, List<String> downloads) {
		this.datasets = datasets;
		this.downloads = downloads;
	}
}