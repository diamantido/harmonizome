package edu.mssm.pharm.maayanlab.Harmonizome.net;

public enum HttpStatusCode {

	OK(200, "Success."), NOT_FOUND(404, "Not found.");

	private int value;
	
	private String message;

	private HttpStatusCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }
    
    public String getMessage() {
    	return message;
    }
}