package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.io.UnsupportedEncodingException;
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

import edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec;

@Entity
@Table(name = "attribute")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/* From datasets
	 * ------------- */
	@Column(name = "name_from_dataset")
	private String nameFromDataset;
	
	@Column(name = "id_from_dataset")
	private String idFromDataset;
	
	@Column(name = "description_from_dataset")
	@Type(type = "text")
	private String descriptionFromDataset;

	@Column(name = "url_from_dataset", length = 2083)
	private String urlFromDataset;
	
	/* From naming authority
	 * --------------------- */
	@Column(name = "name_from_naming_authority")
	private String nameFromNamingAuthority;
	
	@Column(name = "id_from_naming_authority")
	private String idFromNamingAuthority;
	
	@Column(name = "description_from_naming_authority")
	@Type(type = "text")
	private String descriptionFromNamingAuthority;

	@Column(name = "url_from_naming_authority", length = 2083)
	private String urlFromNamingAuthority;
	
	/* Foreign key relationships
	 * ------------------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_type_fk")
	private AttributeType attributeType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "naming_authority_fk")
	private NamingAuthority namingAuthority;

	/* Back references
	 * --------------- */
	@OneToMany(mappedBy = "attribute")
	private Set<Feature> features;

	public Attribute() {
	}

	public int getId() {
		return id;
	}

	public String getNameFromDataset() {
		return nameFromDataset;
	}

	public void setNameFromDataset(String nameFromDataset) {
		this.nameFromDataset = nameFromDataset;
	}

	public String getIdFromDataset() {
		return idFromDataset;
	}

	public void setIdFromDataset(String idFromDataset) {
		this.idFromDataset = idFromDataset;
	}

	public String getDescriptionFromDataset() {
		return descriptionFromDataset;
	}

	public void setDescriptionFromDataset(String descriptionFromDataset) {
		this.descriptionFromDataset = descriptionFromDataset;
	}

	public String getUrlFromDataset() {
		return urlFromDataset;
	}

	public void setUrlFromDataset(String urlFromDataset) {
		this.urlFromDataset = urlFromDataset;
	}

	public String getNameFromNamingAuthority() {
		return nameFromNamingAuthority;
	}

	public void setNameFromNamingAuthority(String nameFromNamingAuthority) {
		this.nameFromNamingAuthority = nameFromNamingAuthority;
	}

	public String getIdFromNamingAuthority() {
		return idFromNamingAuthority;
	}

	public void setIdFromNamingAuthority(String idFromNamingAuthority) {
		this.idFromNamingAuthority = idFromNamingAuthority;
	}

	public String getDescriptionFromNamingAuthority() {
		return descriptionFromNamingAuthority;
	}

	public void setDescriptionFromNamingAuthority(String descriptionFromNamingAuthority) {
		this.descriptionFromNamingAuthority = descriptionFromNamingAuthority;
	}

	public String getUrlFromNamingAuthority() {
		return urlFromNamingAuthority;
	}

	public void setUrlFromNamingAuthority(String urlFromNamingAuthority) {
		this.urlFromNamingAuthority = urlFromNamingAuthority;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
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
	
	/* Utility functions
	 * ----------------- */
	public String getUrlEncodedNameFromDataset() throws UnsupportedEncodingException {
		return URLCodec.encode(nameFromDataset);
	}
	
	public String getEndpoint() {
		return "gene_set";
	}
}
