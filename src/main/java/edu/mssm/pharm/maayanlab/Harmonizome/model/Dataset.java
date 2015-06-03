package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.sql.Timestamp;
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
@Table(name = "dataset")
public class Dataset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_fk")
	private Resource resource;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dataset_type_fk")
	private DatasetType datasetType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dataset_group_fk")
	private DatasetGroup datasetGroup;

	@Column(name="association")
	private String association;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_type_fk")
	private AttributeType attributeType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_group_fk")
	private AttributeGroup attributeGroup;

	@Column(name = "download_date")
	private Timestamp downloadDate;
	
	@Column(name = "attribute_table_dl")
	private String attributeTableDl;

	@Column(name = "edge_list_dl")
	private String edgeListDl;

	@Column(name = "gene_set_library_dl")
	private String geneStLibraryDl;

	@Column(name = "up_gene_set_library_dl")
	private String upGeneSetLibraryDl;

	@Column(name = "down_gene_set_library_dl")
	private String downGeneSetLibraryDl;

	@Column(name = "attribute_set_library_dl")
	private String attributeSetLibraryDl;

	@Column(name = "up_attribute_set_library_dl")
	private String upAttributeSetLibraryDl;

	@Column(name = "down_attribute_set_library_dl")
	private String downAttributeSetLibraryDl;

	@Column(name = "gene_similarity_matrix_dl")
	private String geneSimilarityMatrixDl;

	@Column(name = "attribute_similarity_matrix_dl")
	private String attributeSimilarityMatrixDl;

	@Column(name = "gene_list_dl")
	private String geneListDl;

	@Column(name = "attribute_list_dl")
	private String attributeListDl;

	@Column(name = "processing_script_dl")
	private String processingScriptDl;
	
	@OneToMany(mappedBy = "dataset")
	private Set<Feature> features;

	public Dataset() {
	}

	public Dataset(String name, Resource resource, String description, DatasetType datasetType, DatasetGroup datasetGroup, String association, AttributeType attributeType,
			AttributeGroup attributeGroup, Timestamp downloadDate, String attributeTableDl, String edgeListDl, String geneStLibraryDl, String upGeneSetLibraryDl, String downGeneSetLibraryDl,
			String attributeSetLibraryDl, String upAttributeSetLibraryDl, String downAttributeSetLibraryDl, String geneSimilarityMatrixDl, String attributeSimilarityMatrixDl, String geneListDl,
			String attributeListDl, String processingScriptDl) {
		this.name = name;
		this.resource = resource;
		this.description = description;
		this.datasetType = datasetType;
		this.datasetGroup = datasetGroup;
		this.association = association;
		this.attributeType = attributeType;
		this.attributeGroup = attributeGroup;
		this.downloadDate = downloadDate;
		this.attributeTableDl = attributeTableDl;
		this.edgeListDl = edgeListDl;
		this.geneStLibraryDl = geneStLibraryDl;
		this.upGeneSetLibraryDl = upGeneSetLibraryDl;
		this.downGeneSetLibraryDl = downGeneSetLibraryDl;
		this.attributeSetLibraryDl = attributeSetLibraryDl;
		this.upAttributeSetLibraryDl = upAttributeSetLibraryDl;
		this.downAttributeSetLibraryDl = downAttributeSetLibraryDl;
		this.geneSimilarityMatrixDl = geneSimilarityMatrixDl;
		this.attributeSimilarityMatrixDl = attributeSimilarityMatrixDl;
		this.geneListDl = geneListDl;
		this.attributeListDl = attributeListDl;
		this.processingScriptDl = processingScriptDl;
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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DatasetType getDatasetType() {
		return datasetType;
	}

	public void setDatasetType(DatasetType datasetType) {
		this.datasetType = datasetType;
	}

	public DatasetGroup getDatasetGroup() {
		return datasetGroup;
	}

	public void setDatasetGroup(DatasetGroup datasetGroup) {
		this.datasetGroup = datasetGroup;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public AttributeType getAttrobiteType() {
		return attributeType;
	}

	public void setAttrobiteType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}

	public void setAttributeGroup(AttributeGroup attributeGroup) {
		this.attributeGroup = attributeGroup;
	}

	public Timestamp getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Timestamp downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getAttributeTableDl() {
		return attributeTableDl;
	}

	public void setAttributeTableDl(String attributeTableDl) {
		this.attributeTableDl = attributeTableDl;
	}

	public String getEdgeListDl() {
		return edgeListDl;
	}

	public void setEdgeListDl(String edgeListDl) {
		this.edgeListDl = edgeListDl;
	}

	public String getGeneStLibraryDl() {
		return geneStLibraryDl;
	}

	public void setGeneStLibraryDl(String geneStLibraryDl) {
		this.geneStLibraryDl = geneStLibraryDl;
	}

	public String getUpGeneSetLibraryDl() {
		return upGeneSetLibraryDl;
	}

	public void setUpGeneSetLibraryDl(String upGeneSetLibraryDl) {
		this.upGeneSetLibraryDl = upGeneSetLibraryDl;
	}

	public String getDownGeneSetLibraryDl() {
		return downGeneSetLibraryDl;
	}

	public void setDownGeneSetLibraryDl(String downGeneSetLibraryDl) {
		this.downGeneSetLibraryDl = downGeneSetLibraryDl;
	}

	public String getAttributeSetLibraryDl() {
		return attributeSetLibraryDl;
	}

	public void setAttributeSetLibraryDl(String attributeSetLibraryDl) {
		this.attributeSetLibraryDl = attributeSetLibraryDl;
	}

	public String getUpAttributeSetLibraryDl() {
		return upAttributeSetLibraryDl;
	}

	public void setUpAttributeSetLibraryDl(String upAttributeSetLibraryDl) {
		this.upAttributeSetLibraryDl = upAttributeSetLibraryDl;
	}

	public String getDownAttributeSetLibraryDl() {
		return downAttributeSetLibraryDl;
	}

	public void setDownAttributeSetLibraryDl(String downAttributeSetLibraryDl) {
		this.downAttributeSetLibraryDl = downAttributeSetLibraryDl;
	}

	public String getGeneSimilarityMatrixDl() {
		return geneSimilarityMatrixDl;
	}

	public void setGeneSimilarityMatrixDl(String geneSimilarityMatrixDl) {
		this.geneSimilarityMatrixDl = geneSimilarityMatrixDl;
	}

	public String getAttributeSimilarityMatrixDl() {
		return attributeSimilarityMatrixDl;
	}

	public void setAttributeSimilarityMatrixDl(String attributeSimilarityMatrixDl) {
		this.attributeSimilarityMatrixDl = attributeSimilarityMatrixDl;
	}

	public String getGeneListDl() {
		return geneListDl;
	}

	public void setGeneListDl(String geneListDl) {
		this.geneListDl = geneListDl;
	}

	public String getAttributeListDl() {
		return attributeListDl;
	}

	public void setAttributeListDl(String attributeListDl) {
		this.attributeListDl = attributeListDl;
	}

	public String getProcessingScriptDl() {
		return processingScriptDl;
	}

	public void setProcessingScriptDl(String processingScriptDl) {
		this.processingScriptDl = processingScriptDl;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
}