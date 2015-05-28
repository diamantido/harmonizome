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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "dataset")
public class Dataset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_fk")
	private DatasetType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_fk")
	private DatasetGroup group;

	@Column(name = "processing_script_url", length = 2083)
	private String processingScriptUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_fk")
	private Resource resource;

	@OneToMany(mappedBy = "dataset")
	private Set<Feature> features;

	public Dataset() {
	}

	public Dataset(String name, String description, DatasetType type, DatasetGroup group, String processingScriptUrl, Resource resource) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.group = group;
		this.processingScriptUrl = processingScriptUrl;
		this.resource = resource;
	}

	public int getId() {
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
		return type;
	}

	public void setDatasetType(DatasetType type) {
		this.type = type;
	}

	public DatasetGroup getDatasetGroup() {
		return group;
	}

	public void setDatasetGroup(DatasetGroup group) {
		this.group = group;
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

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
}