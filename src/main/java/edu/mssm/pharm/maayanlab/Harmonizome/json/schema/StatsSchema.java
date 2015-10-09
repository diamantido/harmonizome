package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;

public class StatsSchema {

	public List<Resource> resources;

	public Map<String, Long> datasets;

	public Map<String, Long> attributes;

	public StatsSchema(List<Resource> resources, Map<String, Long> datasets, Map<String, Long> attributes) {
		this.resources = resources;
		this.datasets = datasets;
		this.attributes = attributes;
	}
}
