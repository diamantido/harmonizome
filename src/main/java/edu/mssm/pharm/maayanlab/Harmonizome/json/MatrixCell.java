package edu.mssm.pharm.maayanlab.Harmonizome.json;

public class MatrixCell {

	private String col;
	
	private String row;
	
	private double value;

	public MatrixCell(String col, String row, double value) {
		this.col = col;
		this.row = row;
		this.value = value;
	}

	public String getCol() {
		return col;
	}

	public void getCol(String col) {
		this.col = col;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
