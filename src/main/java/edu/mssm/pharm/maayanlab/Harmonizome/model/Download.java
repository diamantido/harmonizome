package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Comparator;

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
@Table(name = "download")
public class Download {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "directory")
	private String directory;

	@Column(name = "count")
	private int count;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_fk")
	private DownloadType type;

	public Download() {
	}

	public Download(String name, String directory, int count, Dataset dataset, DownloadType type) {
		this.name = name;
		this.directory = directory;
		this.count = count;
		this.dataset = dataset;
		this.type = type;
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

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public DownloadType getType() {
		return type;
	}

	public void setType(DownloadType type) {
		this.type = type;
	}
}