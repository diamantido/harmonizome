package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "dataset_group")
public class DatasetGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "datasetGroup")
	private Set<Dataset> datasets;

	public DatasetGroup() {
	}

	public DatasetGroup(String name) {
		this.name = name;
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

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(Set<Dataset> datasets) {
		this.datasets = datasets;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DatasetGroup)) {
			return false;
		}
        if (obj == this) {
        	return true;
        }
        DatasetGroup dsg = (DatasetGroup) obj;
        return new EqualsBuilder().append(this.name, dsg.name).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.name).toHashCode();
	}
}