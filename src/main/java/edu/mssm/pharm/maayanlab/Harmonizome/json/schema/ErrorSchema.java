package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

public class ErrorSchema {
	
	private int status;
	
	private String message;

	public ErrorSchema() {
		this.status = 404;
		this.message = "Not found.";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
