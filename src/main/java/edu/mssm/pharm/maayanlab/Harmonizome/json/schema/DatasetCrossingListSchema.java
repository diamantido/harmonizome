package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetCrossing;

public class DatasetCrossingListSchema {

	public Dataset dataset1;

    public Dataset dataset2;

	public String attributetype1;

	public String attributetype2;
	
	public List<DatasetCrossing> gene_set_pairs;
	
	public DatasetCrossingListSchema() {
	}
	
	public DatasetCrossingListSchema(Dataset dataset1, Dataset dataset2, List<DatasetCrossing> gene_set_pairs) {
		this.dataset1 = dataset1;
        this.dataset2 = dataset2;
		this.attributetype1 = StringUtils.capitalize(dataset1.getAttributeType().getName());
		this.attributetype2 = StringUtils.capitalize(dataset2.getAttributeType().getName());
		this.gene_set_pairs = gene_set_pairs;
	}

	public Dataset getDataset1() {
		return dataset1;
	}

	public void setDataset1(Dataset dataset1) {
		this.dataset1 = dataset1;
	}

    public Dataset getDataset2() {
		return dataset2;
	}

	public void setDataset2(Dataset dataset2) {
		this.dataset2 = dataset2;
	}

	public String getAttributeType1() {
		return attributetype1;
	}

	public void setAttributeType1(String attributetype1) {
		this.attributetype1 = attributetype1;
	}

	public String getAttributeType2() {
		return attributetype2;
	}

	public void setAttributeType2(String attributetype2) {
		this.attributetype2 = attributetype2;
	}

	public List<DatasetCrossing> getList() {
		return gene_set_pairs;
	}

	public void setList(List<DatasetCrossing> gene_set_pairs) {
		this.gene_set_pairs = gene_set_pairs;
	}
}