package edu.mssm.pharm.maayanlab.Harmonizome.model;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gene_set")
@BioEntityMetadata(name = "gene_set", keyColumn = "name_from_dataset", jsp = "geneSet.jsp")
public class GeneSet implements BioEntity {

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
	
	/* Back references
	 * --------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_fk")
	private Attribute attribute;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;
	
	@OneToMany(mappedBy = "geneSet")
	Set<Association> associations;

	/* Foreign key relationships
	 * ------------------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_type_fk")
	private AttributeType attributeType;
	
	public static final String ENDPOINT = "gene_set";

	public GeneSet() {
	}

	/* Getters & Setters 
	 * ----------------- */	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public Set<Association> getAssociations() {
		return associations;
	}

	public void setAssociations(Set<Association> associations) {
		this.associations = associations;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}
	
	/* Utility functions
	 * ----------------- */
	@Transient
	public String getKey() {
		return "name";
	}
	
	@Transient
	public String getValue() {
		return getNameFromDataset() + "/" + getDataset().getName();
	}
	
	@Transient
	public String getUrlEncodedValue() {
		String attribute = UrlCodec.encode(getNameFromDataset());
		String dataset = UrlCodec.encode(getDataset().getName());
		return attribute + "/" + dataset;
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}