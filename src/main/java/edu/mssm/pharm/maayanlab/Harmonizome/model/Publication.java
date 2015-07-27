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

	@Column(name = "last_author_initials")
	private String lastAuthorInitials;
	
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

	public int getId() {
		return id;
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

	public String getLastAuthorInitials() {
		return lastAuthorInitials;
	}

	public void setLastAuthorInitials(String lastAuthorInitials) {
		this.lastAuthorInitials = lastAuthorInitials;
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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public NamingAuthority getNamingAuthority() {
		return namingAuthority;
	}

	public void setNamingAuthority(NamingAuthority namingAuthority) {
		this.namingAuthority = namingAuthority;
	}
}
