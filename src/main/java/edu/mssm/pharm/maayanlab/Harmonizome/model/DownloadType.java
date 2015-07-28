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
@Table(name = "download_type")
public class DownloadType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "filename", unique = true)
	private String filename;
	
	@Column(name = "description")
	private String description;
	
	// This is used by the front end to correctly order the downloads.
	@Column(name = "ordering")
	private int ordering;

	@OneToMany(mappedBy = "type")
	private Set<Download> downloads;
	
	public DownloadType() {
	}

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public Set<Download> getDownloads() {
		return downloads;
	}

	public void setDownloads(Set<Download> downloads) {
		this.downloads = downloads;
	}
}
