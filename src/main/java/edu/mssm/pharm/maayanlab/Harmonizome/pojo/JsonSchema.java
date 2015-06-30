package edu.mssm.pharm.maayanlab.Harmonizome.pojo;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;

public class JsonSchema {

	private List<Dataset> dataset;
	private List<DatasetGroup> datasetGroup;
	private List<DatasetType> datasetType;
	//private List<Attribute> attribute;
	private List<AttributeGroup> attributeGroup;
	private List<AttributeType> attributeType;
	private List<Gene> gene;
	/*private List<IdgFamily> idgFamily;*/

	public JsonSchema() {
	}
	
	public List<Dataset> getDataset() {
		return dataset;
	}

	public void setDataset(List<Dataset> dataset) {
		this.dataset = dataset;
	}

	public List<DatasetGroup> getDatasetGroup() {
		return datasetGroup;
	}
	
	public void setDatasetGroup(List<DatasetGroup> datasetGroup) {
		this.datasetGroup = datasetGroup;
	}
	
	public List<DatasetType> getDatasetType() {
		return datasetType;
	}
	
	public void setDatasetType(List<DatasetType> datasetType) {
		this.datasetType = datasetType;
	}
	
	/*public List<Attribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}*/

	public List<AttributeGroup> getAttributeGroup() {
		return attributeGroup;
	}
	
	public void setAttributeGroup(List<AttributeGroup> attributeGroup) {
		this.attributeGroup = attributeGroup;
	}
	
	public List<AttributeType> getAttributeType() {
		return attributeType;
	}
	
	public void setAttributeType(List<AttributeType> attributeType) {
		this.attributeType = attributeType;
	}
	
	public List<Gene> getGene() {
		return gene;
	}
	
	public void setGene(List<Gene> gene) {
		this.gene = gene;
	}
	
	/*public List<IdgFamily> getIdgFamily() {
		return idgFamily;
	}
	public void setIdgFamily(List<IdgFamily> idgFamily) {
		this.idgFamily = idgFamily;
	}*/
}