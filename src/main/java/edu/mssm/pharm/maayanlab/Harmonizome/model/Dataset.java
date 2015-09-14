package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DownloadComparator;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Ellipsizer;

@Entity
@Table(name = "dataset")
@BioEntityMetadata(name = "dataset", keyColumn = "name", jsp="dataset.jsp")
public class Dataset implements BioEntity {

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
	
	@Column(name = "gene_sets_description")
	private String geneSetsDescription;
	
	@Column(name = "attribute_set_description")
	private String attributeSetDescription;
	
	@Column(name = "positive_association")
	private String positiveAssociation;
	
	@Column(name = "negative_association")
	private String negativeAssociation;
	
	@Column(name = "last_updated")
	private Timestamp lastUpdated;

	@Column(name = "directory")
	private String directory;
	
	@Column(name = "num_page_views")
	private int numPageViews;

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

	/* Back references
	 * --------------- */
	@OneToMany(mappedBy = "dataset")
	private List<Download> downloads;

	@OneToMany(mappedBy = "dataset")
	private Set<Attribute> attributes;
		
	/* Utilities
	 * --------- */
	@Transient
	public static final String ENDPOINT = "dataset";
	
	/* Foreign key relationships
	 * ------------------------- */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "datasets_to_publications", joinColumns = { @JoinColumn(name = "dataset_fk") }, inverseJoinColumns = { @JoinColumn(name = "publication_fk") })
	private List<Publication> publications;
	
	public Dataset() {
	}

	/* Getters & Setters 
	 * ----------------- */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getGeneSetsDescription() {
		return geneSetsDescription;
	}

	public void setGeneSetsDescription(String geneSetsDescription) {
		this.geneSetsDescription = geneSetsDescription;
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

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public int getNumPageViews() {
		return numPageViews;
	}

	public void setNumPageViews(int numPageViews) {
		this.numPageViews = numPageViews;
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

	public List<Download> getDownloads() {
		return downloads;
	}

	public void setDownloads(List<Download> downloads) {
		this.downloads = downloads;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}
	

	/* Utility functions
	 * ----------------- */
	@Transient
	public String getKey() {
		return "name";
	}
	
	@Transient
	public String getValue() {
		return name;
	}
	
	@Transient
	public String getUrlEncodedValue() {
		return UrlCodec.encode(name);
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}

	@Transient
	public String getEllipsizedDescription() {
		return Ellipsizer.trim(getDescription(), Ellipsizer.MAX_DESCRIPTION_LENGTH);
	}
	
	@Transient
	public String getCssClassName() {
		return StringUtils.join(
			name.replace(",", "").replace(".", "").split(" "),
			"-"
		);
	}
	
	@Transient
	public List<Download> getSortedDownloads() {
		Collections.sort(downloads, new DownloadComparator());
		return downloads;
	}
}