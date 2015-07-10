package edu.mssm.pharm.maayanlab.Harmonizome.json;

public class MatrixNode {

	private int sort;
	
	private String name;
	
	public MatrixNode() {
	}
	
	public MatrixNode(int sort, String name) {
		this.sort = sort;
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}