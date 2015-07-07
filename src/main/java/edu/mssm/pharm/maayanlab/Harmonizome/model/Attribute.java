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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "attribute")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@Column(name = "url", length = 2083)
	private String url;

	@Column(name = "id_from_naming_authority")
	private String idFromNamingAuthority;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_type_fk")
	private AttributeType attributeType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_group_fk")
	private AttributeGroup attributeGroup;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "naming_authority_fk")
	private NamingAuthority namingAuthority;

	@OneToMany(mappedBy = "attribute")
	private Set<Feature> features;

	@OneToMany(mappedBy = "attribute1")
	private Set<AttributeSimilarityMatrix> attributeSimilarities1;

	@OneToMany(mappedBy = "attribute2")
	private Set<AttributeSimilarityMatrix> attributeSimilarities2;

	public Attribute() {
	}

	public Attribute(int id, String name, String description, String url, String idFromNamingAuthority, AttributeType attributeType, AttributeGroup attributeGroup, NamingAuthority namingAuthority) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.idFromNamingAuthority = idFromNamingAuthority;
		this.attributeType = attributeType;
		this.attributeGroup = attributeGroup;
		this.namingAuthority = namingAuthority;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdFromNamingAuthority() {
		return idFromNamingAuthority;
	}

	public void setIdFromNamingAuthority(String namingAuthorityId) {
		this.idFromNamingAuthority = namingAuthorityId;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}

	public void setAttributeGroup(AttributeGroup attributeGroup) {
		this.attributeGroup = attributeGroup;
	}

	public NamingAuthority getNamingAuthority() {
		return namingAuthority;
	}

	public void setNamingAuthority(NamingAuthority namingAuthority) {
		this.namingAuthority = namingAuthority;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Set<AttributeSimilarityMatrix> getAttributeSimilarities1() {
		return attributeSimilarities1;
	}

	public void setAttributeSimilarities1(Set<AttributeSimilarityMatrix> attributeSimilarities1) {
		this.attributeSimilarities1 = attributeSimilarities1;
	}

	public Set<AttributeSimilarityMatrix> getAttributeSimilarities2() {
		return attributeSimilarities2;
	}

	public void setAttributeSimilarities2(Set<AttributeSimilarityMatrix> attributeSimilarities2) {
		this.attributeSimilarities2 = attributeSimilarities2;
	}
}
