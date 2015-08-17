package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;

public class ErrorSchema {

	private int status;
	
	private String message;
	
	public ErrorSchema(HttpStatusCode statusCode) {
		this.status = statusCode.getValue();
		this.message = statusCode.getMessage();
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
