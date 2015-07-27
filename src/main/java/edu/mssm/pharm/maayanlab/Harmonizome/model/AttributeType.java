package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "attribute_type")
public class AttributeType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_group_fk")
	private AttributeGroup attributeGroup;

	@OneToMany(mappedBy = "attributeType")
	private Set<Attribute> attributes;

	@OneToMany(mappedBy = "attributeType")
	private Set<Dataset> datasets;
	
	public AttributeType() {
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
	
	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}

	public void setAttributeGroup(AttributeGroup attributeGroup) {
		this.attributeGroup = attributeGroup;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(Set<Dataset> datasets) {
		this.datasets = datasets;
	}
}
