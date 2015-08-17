package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "publication")
public class Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "long_citation")
	private String longCitation;

	@Column(name = "short_citation")
	private String shortCitation;
	
	@Column(name = "url", length = 2083)
	private String url;

	@Column(name = "pmid")
	private int pmid;

	@Column(name = "pubmed_url", length = 2083)
	private String pubmedUrl;

	@Column(name = "first_author_last_name")
	private String firstAuthorLastName;

	@Column(name = "first_author_initials")
	private String firstAuthorInitials;
	
	@Column(name = "journal_abbreviation")
	private String journalAbbreviation;

	@Column(name = "year")
	private int year;

	@Column(name = "title")
	private String title;
	
	@Column(name = "volume")
	private String volume;
	
	@Column(name = "pages")
	private String pages;
	
	@ManyToMany(mappedBy = "publications")
	private Set<Dataset> datasets;
	
	public Publication() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongCitation() {
		return longCitation;
	}

	public void setLongCitation(String longCitation) {
		this.longCitation = longCitation;
	}

	public String getShortCitation() {
		return shortCitation;
	}

	public void setShortCitation(String shortCitation) {
		this.shortCitation = shortCitation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPmid() {
		return pmid;
	}

	public void setPmid(int pmid) {
		this.pmid = pmid;
	}

	public String getPubmedUrl() {
		return pubmedUrl;
	}

	public void setPubmedUrl(String pubmedUrl) {
		this.pubmedUrl = pubmedUrl;
	}

	public String getFirstAuthorLastName() {
		return firstAuthorLastName;
	}

	public void setFirstAuthorLastName(String firstAuthorLastName) {
		this.firstAuthorLastName = firstAuthorLastName;
	}

	public String getFirstAuthorInitials() {
		return firstAuthorInitials;
	}

	public void setFirstAuthorInitials(String lastAuthorInitials) {
		this.firstAuthorInitials = lastAuthorInitials;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(Set<Dataset> datasets) {
		this.datasets = datasets;
	}	
}
