package edu.mssm.pharm.maayanlab.Harmonizome.hibernateObjects;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "metrics", catalog = "harmonizome")
public class DbMetric {

	private int id;
	private String resource;
	private int pageViews;
	private int geneListDl;
	private int attributeListDl;
	private int edgeListDl;
	private int downGeneSetLibraryDl;
	private int upGeneSetLibraryDl;
	private int geneSetLibraryDl;
	private int downAttributeSetLibraryDl;
	private int upAttributeSetLibraryDl;
	private int attributeSetLibraryDl;
	private int geneSimilarityMatrixDl;
	private int attributeSimilarityMatrixDl;
	private int attributeTableDl;
	private int scriptDl;

	public DbMetric() {
	}

	public DbMetric(String resource, int pageViews, int geneListDl, int attributeListDl, int edgeListDl, int downGeneSetLibraryDl, int upGeneSetLibraryDl, int geneSetLibraryDl,
			int geneSimilarityMatrixDl, int downAttributeSetLibraryDl, int upAttributeSetLibraryDl, int attributeSetLibraryDl, int attributeSimilarityMatrixDl, int attributeTableDl, int scriptDl) {

		this.resource = resource;
		this.pageViews = pageViews;
		this.geneListDl = geneListDl;
		this.attributeListDl = attributeListDl;
		this.edgeListDl = edgeListDl;
		this.downGeneSetLibraryDl = downGeneSetLibraryDl;
		this.upGeneSetLibraryDl = upGeneSetLibraryDl;
		this.geneSetLibraryDl = geneSetLibraryDl;
		this.downAttributeSetLibraryDl = downAttributeSetLibraryDl;
		this.upAttributeSetLibraryDl = upAttributeSetLibraryDl;
		this.attributeSetLibraryDl = attributeSetLibraryDl;
		this.geneSimilarityMatrixDl = geneSimilarityMatrixDl;
		this.attributeSimilarityMatrixDl = attributeSimilarityMatrixDl;
		this.attributeTableDl = attributeTableDl;
		this.scriptDl = scriptDl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "resource")
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Column(name = "pageViews")
	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

	@Column(name = "geneListDl")
	public int getGeneListDl() {
		return geneListDl;
	}

	public void setGeneListDl(int geneListDl) {
		this.geneListDl = geneListDl;
	}

	@Column(name = "attributeListDl")
	public int getAttributeListDl() {
		return attributeListDl;
	}

	public void setAttributeListDl(int attributeListDl) {
		this.attributeListDl = attributeListDl;
	}

	@Column(name = "edgeListDl")
	public int getEdgeListDl() {
		return edgeListDl;
	}

	public void setEdgeListDl(int edgeListDl) {
		this.edgeListDl = edgeListDl;
	}

	@Column(name = "downGeneSetLibraryDl")
	public int getDownGeneSetLibraryDl() {
		return downGeneSetLibraryDl;
	}

	public void setDownGeneSetLibraryDl(int downGeneSetLibraryDl) {
		this.downGeneSetLibraryDl = downGeneSetLibraryDl;
	}

	@Column(name = "upGeneSetLibraryDl")
	public int getUpGeneSetLibraryDl() {
		return upGeneSetLibraryDl;
	}

	public void setUpGeneSetLibraryDl(int upGeneSetLibraryDl) {
		this.upGeneSetLibraryDl = upGeneSetLibraryDl;
	}

	@Column(name = "geneSetLibraryDl")
	public int getGeneSetLibraryDl() {
		return geneSetLibraryDl;
	}

	public void setGeneSetLibraryDl(int geneSetLibraryDl) {
		this.geneSetLibraryDl = geneSetLibraryDl;
	}

	@Column(name = "geneSimilarityMatrixDl")
	public int getGeneSimilarityMatrixDl() {
		return geneSimilarityMatrixDl;
	}

	public void setGeneSimilarityMatrixDl(int geneSimilarityMatrixDl) {
		this.geneSimilarityMatrixDl = geneSimilarityMatrixDl;
	}

	@Column(name = "downAttributeSetLibraryDl")
	public int getDownAttributeSetLibraryDl() {
		return downAttributeSetLibraryDl;
	}

	public void setDownAttributeSetLibraryDl(int downAttributeSetLibraryDl) {
		this.downAttributeSetLibraryDl = downAttributeSetLibraryDl;
	}

	@Column(name = "upAttributeSetLibraryDl")
	public int getUpAttributeSetLibraryDl() {
		return upAttributeSetLibraryDl;
	}

	public void setUpAttributeSetLibraryDl(int upAttributeSetLibraryDl) {
		this.upAttributeSetLibraryDl = upAttributeSetLibraryDl;
	}

	@Column(name = "attributeSetLibraryDl")
	public int getAttributeSetLibraryDl() {
		return attributeSetLibraryDl;
	}

	public void setAttributeSetLibraryDl(int attributeSetLibraryDl) {
		this.attributeSetLibraryDl = attributeSetLibraryDl;
	}

	@Column(name = "attributeSimilarityMatrixDl")
	public int getAttributeSimilarityMatrixDl() {
		return attributeSimilarityMatrixDl;
	}

	public void setAttributeSimilarityMatrixDl(int attributeSimilarityMatrixDl) {
		this.attributeSimilarityMatrixDl = attributeSimilarityMatrixDl;
	}

	@Column(name = "attributeTableDl")
	public int getAttributeTableDl() {
		return attributeTableDl;
	}

	public void setAttributeTableDl(int attributeTableDl) {
		this.attributeTableDl = attributeTableDl;
	}

	@Column(name = "scriptDl")
	public int getScriptDl() {
		return scriptDl;
	}

	public void setScriptDl(int scriptDl) {
		this.scriptDl = scriptDl;
	}
	
	@Transient
	public Map<String, Integer> getCounts() {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		counts.put("pageViews", this.getPageViews());
		counts.put("geneListDl", this.getGeneListDl());
		counts.put("attributeListDl", this.getAttributeListDl());
		counts.put("edgeListDl", this.getEdgeListDl());
		counts.put("downGeneSetLibraryDl", this.getDownGeneSetLibraryDl());
		counts.put("upGeneSetLibraryDl", this.getUpGeneSetLibraryDl());
		counts.put("geneSetLibraryDl", this.getGeneSetLibraryDl());
		counts.put("downAttributeSetLibraryDl", this.getDownAttributeSetLibraryDl());
		counts.put("upAttributeSetLibraryDl", this.getUpAttributeSetLibraryDl());
		counts.put("attributeSetLibraryDl", this.getAttributeSetLibraryDl());
		counts.put("geneSimilarityMatrixDl", this.getGeneSimilarityMatrixDl());
		counts.put("attributeSimilarityMatrixDl", this.getAttributeSimilarityMatrixDl());
		counts.put("attributeTableDl", this.getAttributeTableDl());
		counts.put("pagscriptDleViews", this.getScriptDl());
		return counts;
	}
}
