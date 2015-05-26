package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "publication")
public class Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "citation")
	private String citation;

	@Column(name = "url")
	private String url;

	@Column(name = "pmid")
	private int pmid;

	@Column(name = "pub_med_url")
	private String pubMedUrl;

	@Column(name = "first_author_last_name")
	private String firstAuthorLastName;

	@Column(name = "journal_abbreviation")
	private String journalAbbreviation;

	@Column(name = "year")
	private int year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_fk")
	private Resource resource;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "naming_authority_fk")
	private NamingAuthority namingAuthority;

	public Publication() {
	}

	public Publication(String citation, String url, int pmid, String pubMedUrl, String firstAuthorLastName, String journalAbbreviation, int year, Resource resource, Dataset dataset,
			NamingAuthority namingAuthority) {

		this.citation = citation;
		this.url = url;
		this.pmid = pmid;
		this.pubMedUrl = pubMedUrl;
		this.firstAuthorLastName = firstAuthorLastName;
		this.journalAbbreviation = journalAbbreviation;
		this.year = year;
		this.resource = resource;
		this.dataset = dataset;
		this.namingAuthority = namingAuthority;
	}

	public long getId() {
		return id;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public int getpmid() {
		return pmid;
	}

	public void setpmid(int pmid) {
		this.pmid = pmid;
	}

	public String getPubMedurl() {
		return pubMedUrl;
	}

	public void setPubMedurl(String pubMedUrl) {
		this.pubMedUrl = pubMedUrl;
	}

	public String getFirstAuthorLastName() {
		return firstAuthorLastName;
	}

	public void setFirstAuthorLastName(String firstAuthorLastName) {
		this.firstAuthorLastName = firstAuthorLastName;
	}

	public String getJournalAbbreviation() {
		return journalAbbreviation;
	}

	public void setJournalAbbreviation(String journalAbbreviation) {
		this.journalAbbreviation = journalAbbreviation;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
