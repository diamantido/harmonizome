package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dataset")
public class Dataset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "dataset_type")
	private DatasetType datasetType;

	@Column(name = "dataset_group")
	private DatasetGroup datasetGroup;

	@Column(name = "processing_script_url")
	private String processingScriptUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_fk")
	private Resource resource;

	@OneToMany(mappedBy = "features")
	private Set<Feature> features;

	public Dataset() {
	}

	public Dataset(String name, String description, DatasetType datasetType, DatasetGroup datasetGroup, String processingScriptUrl, Resource resource) {
		this.name = name;
		this.description = description;
		this.datasetType = datasetType;
		this.datasetGroup = datasetGroup;
		this.processingScriptUrl = processingScriptUrl;
		this.resource = resource;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DatasetType getDatasetType() {
		return datasetType;
	}

	public void setDatasetType(DatasetType datasetType) {
		this.datasetType = datasetType;
	}

	public DatasetGroup getDatasetGroup() {
		return datasetGroup;
	}

	public void setDatasetGroup(DatasetGroup datasetGroup) {
		this.datasetGroup = datasetGroup;
	}

	public String getProcessingScriptUrl() {
		return processingScriptUrl;
	}

	public void setProcessingScriptUrl(String processingScriptUrl) {
		this.processingScriptUrl = processingScriptUrl;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
