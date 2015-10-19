package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class ErrorSchema {
	
	public int status;
	
	public String message;
	
	public String description;

	public static enum Status {
		BAD_REQUEST,
		UNAUTHORIZED,
		FORBIDDEN,
		NOT_FOUND,
		METHOD_NOT_ALLOWED
	}
	
	private static Map<Status, Pair<Integer, String>> statusToMessage = new HashMap<Status, Pair<Integer, String>>();
	static {
		statusToMessage.put(Status.BAD_REQUEST, new ImmutablePair<Integer, String>(400, "Bad request."));
		statusToMessage.put(Status.UNAUTHORIZED, new ImmutablePair<Integer, String>(401, "Unauthorized."));
		statusToMessage.put(Status.FORBIDDEN, new ImmutablePair<Integer, String>(403, "Forbidden."));
		statusToMessage.put(Status.NOT_FOUND, new ImmutablePair<Integer, String>(404, "Not found."));
	}
	
	public ErrorSchema() {
		this.status = statusToMessage.get(Status.BAD_REQUEST).getLeft();
		this.message = statusToMessage.get(Status.BAD_REQUEST).getRight();
	}
	
	public ErrorSchema(Status status, String description) {
		this.status = statusToMessage.get(status).getLeft();
		this.message = statusToMessage.get(status).getRight();
		this.description = description;
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
