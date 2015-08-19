package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;

@Entity
@Table(name = "resource")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "acronym")
	private String acronym;

	@Column(name = "short_description")
	@Type(type = "text")
	private String shortDescription;

	@Column(name = "long_description")
	@Type(type = "text")
	private String longDescription;

	@Column(name = "url", length = 2083)
	private String url;

	@Column(name = "image")
	private String image;

	@Column(name = "num_attributes")
	private Long numAttributes;
	
	/* Foreign key relationships
	 * ------------------------- */
	@OneToMany(mappedBy = "resource")
	private List<Dataset> datasets;
	
	/* Utilities
	 * ---------
	 */
	public static final String ENDPOINT = "resource";

	public Resource() {
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

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}
	
	/* Utility functions
	 * ----------------- */
	@Transient
	public String getUrlEncodedName() throws UnsupportedEncodingException {
		return UrlCodec.encode(name);
	}
	
	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}

	public void setNumAttributes(Long numAttributes) {
		this.numAttributes = numAttributes;
	}

	public Long getNumAttributes() {
		return numAttributes;
	}
}
