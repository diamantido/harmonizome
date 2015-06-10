package edu.mssm.pharm.maayanlab.Harmonizome.pojo;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;

public class JsonSchema {

	private List<Dataset> datasets;
	private List<DatasetGroup> datasetGroups;
	private List<DatasetType> datasetTypes;
	//private List<Attribute> attributes;
	private List<AttributeGroup> attributeGroups;
	/*private List<AttributeType> attributeTypes;
	private List<Gene> genes;
	private List<IdgFamily> idgFamilies;*/

	public JsonSchema() {
	}
	
	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	public List<DatasetGroup> getDatasetGroups() {
		return datasetGroups;
	}
	
	public void setDatasetGroups(List<DatasetGroup> datasetGroups) {
		this.datasetGroups = datasetGroups;
	}
	
	public List<DatasetType> getDatasetTypes() {
		return datasetTypes;
	}
	
	public void setDatasetTypes(List<DatasetType> datasetTypes) {
		this.datasetTypes = datasetTypes;
	}
	
	/*public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}*/

	public List<AttributeGroup> getAttributeGroups() {
		return attributeGroups;
	}
	
	public void setAttributeGroups(List<AttributeGroup> attributeGroups) {
		this.attributeGroups = attributeGroups;
	}
	
	/*public List<AttributeType> getAttributeTypes() {
		return attributeTypes;
	}
	public void setAttributeTypes(List<AttributeType> attributeTypes) {
		this.attributeTypes = attributeTypes;
	}
	public List<Gene> getGenes() {
		return genes;
	}
	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}
	public List<IdgFamily> getIdgFamilies() {
		return idgFamilies;
	}
	public void setIdgFamilies(List<IdgFamily> idgFamilies) {
		this.idgFamilies = idgFamilies;
	}*/
}