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
import javax.persistence.Transient;

@Entity
@Table(name = "download")
public class Download {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "count")
	private int count;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "download_type_fk")
	private DownloadType downloadType;

	public Download() {
	}

	public int getId() {
		return id;
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

	public DownloadType getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(DownloadType type) {
		this.downloadType = type;
	}
	
	@Transient
	public String getEndpoint() {
		return "download";
	}
}