package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "metric", catalog = "harmonizome")
public class Metric {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "resource")
	private String resource;

	@Column(name = "pageViews")
	private int pageViews;

	@Column(name = "geneListDl")
	private int geneListDl;

	@Column(name = "attributeListDl")
	private int attributeListDl;

	@Column(name = "edgeListDl")
	private int edgeListDl;

	@Column(name = "downGeneSetLibraryDl")
	private int downGeneSetLibraryDl;

	@Column(name = "upGeneSetLibraryDl")
	private int upGeneSetLibraryDl;

	@Column(name = "geneSetLibraryDl")
	private int geneSetLibraryDl;

	@Column(name = "downAttributeSetLibraryDl")
	private int downAttributeSetLibraryDl;

	@Column(name = "upAttributeSetLibraryDl")
	private int upAttributeSetLibraryDl;

	@Column(name = "attributeSetLibraryDl")
	private int attributeSetLibraryDl;

	@Column(name = "geneSimilarityMatrixDl")
	private int geneSimilarityMatrixDl;

	@Column(name = "attributeSimilarityMatrixDl")
	private int attributeSimilarityMatrixDl;

	@Column(name = "attributeTableDl")
	private int attributeTableDl;

	@Column(name = "scriptDl")
	private int scriptDl;
	
	public Metric() {
	}

	public Metric(String resource, int pageViews, int geneListDl, int attributeListDl, int edgeListDl, int downGeneSetLibraryDl, int upGeneSetLibraryDl, int geneSetLibraryDl,
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

	public int getGeneListDl() {
		return geneListDl;
	}

	public void setGeneListDl(int geneListDl) {
		this.geneListDl = geneListDl;
	}

	public int getAttributeListDl() {
		return attributeListDl;
	}

	public void setAttributeListDl(int attributeListDl) {
		this.attributeListDl = attributeListDl;
	}

	public int getEdgeListDl() {
		return edgeListDl;
	}

	public void setEdgeListDl(int edgeListDl) {
		this.edgeListDl = edgeListDl;
	}

	public int getDownGeneSetLibraryDl() {
		return downGeneSetLibraryDl;
	}

	public void setDownGeneSetLibraryDl(int downGeneSetLibraryDl) {
		this.downGeneSetLibraryDl = downGeneSetLibraryDl;
	}

	public int getUpGeneSetLibraryDl() {
		return upGeneSetLibraryDl;
	}

	public void setUpGeneSetLibraryDl(int upGeneSetLibraryDl) {
		this.upGeneSetLibraryDl = upGeneSetLibraryDl;
	}

	public int getGeneSetLibraryDl() {
		return geneSetLibraryDl;
	}

	public void setGeneSetLibraryDl(int geneSetLibraryDl) {
		this.geneSetLibraryDl = geneSetLibraryDl;
	}

	public int getGeneSimilarityMatrixDl() {
		return geneSimilarityMatrixDl;
	}

	public void setGeneSimilarityMatrixDl(int geneSimilarityMatrixDl) {
		this.geneSimilarityMatrixDl = geneSimilarityMatrixDl;
	}

	public int getDownAttributeSetLibraryDl() {
		return downAttributeSetLibraryDl;
	}

	public void setDownAttributeSetLibraryDl(int downAttributeSetLibraryDl) {
		this.downAttributeSetLibraryDl = downAttributeSetLibraryDl;
	}

	public int getUpAttributeSetLibraryDl() {
		return upAttributeSetLibraryDl;
	}

	public void setUpAttributeSetLibraryDl(int upAttributeSetLibraryDl) {
		this.upAttributeSetLibraryDl = upAttributeSetLibraryDl;
	}

	public int getAttributeSetLibraryDl() {
		return attributeSetLibraryDl;
	}

	public void setAttributeSetLibraryDl(int attributeSetLibraryDl) {
		this.attributeSetLibraryDl = attributeSetLibraryDl;
	}

	public int getAttributeSimilarityMatrixDl() {
		return attributeSimilarityMatrixDl;
	}

	public void setAttributeSimilarityMatrixDl(int attributeSimilarityMatrixDl) {
		this.attributeSimilarityMatrixDl = attributeSimilarityMatrixDl;
	}

	public int getAttributeTableDl() {
		return attributeTableDl;
	}

	public void setAttributeTableDl(int attributeTableDl) {
		this.attributeTableDl = attributeTableDl;
	}

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
