package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "acroynm")
	private String acroynm;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "long_description")
	private String longDescription;

	@Column(name = "url")
	private String url;

	@OneToMany(mappedBy = "resource")
	private Set<Dataset> datasets;

	@OneToMany(mappedBy = "resource")
	private Set<Publication> publication;

	@OneToMany(mappedBy = "resource")
	private Set<NamingAuthority> namingAuthority;

	public Resource() {
	}

	public Resource(String name, String acroynm, String shortDescription, String longDescription, String url) {
		this.name = name;
		this.acroynm = acroynm;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.url = url;
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

	public String getAcroynm() {
		return acroynm;
	}

	public void setAcroynm(String acroynm) {
		this.acroynm = acroynm;
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

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(Set<Dataset> datasets) {
		this.datasets = datasets;
	}

	public Set<Publication> getPublication() {
		return publication;
	}

	public void setPublication(Set<Publication> publication) {
		this.publication = publication;
	}

	public Set<NamingAuthority> getNamingAuthority() {
		return namingAuthority;
	}

	public void setNamingAuthority(Set<NamingAuthority> namingAuthority) {
		this.namingAuthority = namingAuthority;
	}
}
