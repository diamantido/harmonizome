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
@Table(name = "attribute_group")
public class AttributeGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "num_attributes")
	private Long numAttributes;

	/* Back References
	 * --------------- */
	@OneToMany(mappedBy = "attributeGroup")
	private Set<AttributeType> attributeTypes;
	
	@OneToMany(mappedBy = "attributeGroup")
	private Set<Dataset> datasets;

	public AttributeGroup() {
	}

	/* Getters & Setters 
	 * ----------------- */
	public AttributeGroup(String name) {
		this.name = name;
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

	public Long getNumAttributes() {
		return numAttributes;
	}

	public void setNumAttributes(Long numAttributes) {
		this.numAttributes = numAttributes;
	}

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(Set<Dataset> datasets) {
		this.datasets = datasets;
	}

	public Set<AttributeType> getAttributeTypes() {
		return attributeTypes;
	}

	public void setAttributeTypes(Set<AttributeType> attributeTypes) {
		this.attributeTypes = attributeTypes;
	}

	/* Utility Functions 
	 * ----------------- */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AttributeGroup)) {
			return false;
		}
        if (obj == this) {
        	return true;
        }
        AttributeGroup ag = (AttributeGroup) obj;
        return new EqualsBuilder().append(this.name, ag.name).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.name).toHashCode();
	}
}