package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class EntityListSchema<T> {
	
	private Long count;
	
	private Long[] selection = new Long[2];
	
	private String next;
	
	private List<T> entities;
	
	public EntityListSchema(Long count, String endpoint, int startAt) {
		this.count = count;
		
		Long nextInt;
		if (count - startAt < Constant.API_MAX_RESULTS) {
			nextInt = count;
			this.next = "";
		} else {
			nextInt = (long) (startAt + Constant.API_MAX_RESULTS);
			this.next = "/" + Constant.API_URL + "/" + endpoint + "?" + Constant.API_CURSOR + "=" + nextInt;
		}
		
		this.selection[0] = (long) startAt;
		this.selection[1] = nextInt;
	}
	
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}
	
	public Long[] getSelection() {
		return selection;
	}

	public void setSelection(Long[] selection) {
		this.selection = selection;
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