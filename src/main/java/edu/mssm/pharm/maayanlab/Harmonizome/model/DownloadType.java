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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", unique = true)
	private String typeName;
	
	@Column(name = "filename", unique = true)
	private String filename;

	@OneToMany(mappedBy = "type")
	private Set<Download> downloads;
	
	public DownloadType() {
	}

	public DownloadType(String typeName, String filename, Set<Download> downloads) {
		this.typeName = typeName;
		this.filename = filename;
		this.downloads = downloads;
	}

	public int getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Set<Download> getDownloads() {
		return downloads;
	}

	public void setDownloads(Set<Download> downloads) {
		this.downloads = downloads;
	}
}
