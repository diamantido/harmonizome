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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "name_without_resource", unique = true)
	private String nameWithoutResource;

	@Column(name = "description")
	@Type(type = "text")
	private String description;
	
	@Column(name = "association")
	private String association;
	
	@Column(name = "gene_set_description")
	private String geneSetDescription;
	
	@Column(name = "attribute_set_description")
	private String attributeSetDescription;
	
	@Column(name = "positive_association")
	private String positiveAssociation;
	
	@Column(name = "negative_association")
	private String negativeAssociation;

	/* Foreign key relationships
	 * ------------------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "measurement_fk")
	private Measurement measurement;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_group_fk")
	private DatasetGroup datasetGroup;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resource_fk")
	private Resource resource;
	
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

	/* Back references
	 * --------------- */
	@OneToMany(mappedBy = "dataset")
	private Set<Download> downloads;

	@OneToMany(mappedBy = "dataset")
	private Set<Attribute> attributes;
	
	@OneToMany(mappedBy = "resource")
	private Set<Publication> publications;
	
	public Dataset() {
	}

	public Dataset(int id, String name, String nameWithoutResource, String description, String association, String geneSetDescription, String positiveAssociation, String negativeAssociation,
			Measurement measurement, DatasetGroup datasetGroup, Resource resource, AttributeType attributeType, AttributeGroup attributeGroup, Timestamp downloadDate, int numPageViews) {
		this.id = id;
		this.name = name;
		this.nameWithoutResource = nameWithoutResource;
		this.description = description;
		this.association = association;
		this.geneSetDescription = geneSetDescription;
		this.positiveAssociation = positiveAssociation;
		this.negativeAssociation = negativeAssociation;
		this.measurement = measurement;
		this.datasetGroup = datasetGroup;
		this.resource = resource;
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

	public String getNameWithoutResource() {
		return nameWithoutResource;
	}

	public void setNameWithoutResource(String nameWithoutResource) {
		this.nameWithoutResource = nameWithoutResource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public String getGeneSetDescription() {
		return geneSetDescription;
	}

	public void setGeneSetDescription(String geneSetDescription) {
		this.geneSetDescription = geneSetDescription;
	}

	public String getAttributeSetDescription() {
		return attributeSetDescription;
	}

	public void setAttributeSetDescription(String attributeSetDescription) {
		this.attributeSetDescription = attributeSetDescription;
	}

	public String getPositiveAssociation() {
		return positiveAssociation;
	}

	public void setPositiveAssociation(String positiveAssociation) {
		this.positiveAssociation = positiveAssociation;
	}

	public String getNegativeAssociation() {
		return negativeAssociation;
	}

	public void setNegativeAssociation(String negativeAssociation) {
		this.negativeAssociation = negativeAssociation;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public DatasetGroup getDatasetGroup() {
		return datasetGroup;
	}

	public void setDatasetGroup(DatasetGroup datasetGroup) {
		this.datasetGroup = datasetGroup;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Publication> getPublications() {
		return publications;
	}

	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}
}