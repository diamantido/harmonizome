package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.sql.Timestamp;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resource_fk")
	private Resource resource;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_type_fk")
	private DatasetType datasetType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_group_fk")
	private DatasetGroup datasetGroup;

	@Column(name = "association")
	private String association;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_type_fk")
	private AttributeType attributeType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_group_fk")
	private AttributeGroup attributeGroup;

	@Column(name = "download_date")
	private Timestamp downloadDate;

	@Column(name = "num_page_views")
	private int numPageViews;

	@OneToMany(mappedBy = "dataset")
	private Set<Download> downloads;

	@OneToMany(mappedBy = "dataset")
	private Set<Feature> features;

	public Dataset() {
	}

	public Dataset(String name, Resource resource, String description, DatasetType datasetType, DatasetGroup datasetGroup, String association, AttributeType attributeType,
			AttributeGroup attributeGroup, Timestamp downloadDate, int numPageViews, String processingScriptDl) {
		this.name = name;
		this.resource = resource;
		this.description = description;
		this.datasetType = datasetType;
		this.datasetGroup = datasetGroup;
		this.association = association;
		this.attributeType = attributeType;
		this.attributeGroup = attributeGroup;
		this.downloadDate = downloadDate;
		this.numPageViews = numPageViews;
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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}

	public void setAttributeGroup(AttributeGroup attributeGroup) {
		this.attributeGroup = attributeGroup;
	}

	public Timestamp getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Timestamp downloadDate) {
		this.downloadDate = downloadDate;
	}

	public int getNumPageViews() {
		return numPageViews;
	}

	public void setNumPageViews(int numPageViews) {
		this.numPageViews = numPageViews;
	}

	public Set<Download> getDownloads() {
		return downloads;
	}

	public void setDownloads(Set<Download> downloads) {
		this.downloads = downloads;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
}