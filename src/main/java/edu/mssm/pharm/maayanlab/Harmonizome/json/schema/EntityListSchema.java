package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class EntityListSchema<T> {
	
	private String next;
	
	private List<T> entities;
	
	public EntityListSchema(String endpoint, int startAt) {
		int nextInt = startAt + Constant.API_MAX_RESULTS;
		this.next = "/" + Constant.API_URL + "/" + endpoint + "?" + Constant.API_CURSOR + "=" + nextInt;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
	
	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
}