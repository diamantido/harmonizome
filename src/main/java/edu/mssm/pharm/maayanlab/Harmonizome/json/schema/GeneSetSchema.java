package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class GeneSetSchema {

	private int status = HttpStatusCode.OK.getValue();
	
	private String message = HttpStatusCode.OK.getMessage();
	
	private String next;
	
	private List<GeneSet> geneSets;
	
	public GeneSetSchema(int startAt) {
		int nextInt = startAt + Constant.API_MAX_RESULTS;
		this.next = "/" + Constant.API_URL + "/" + Gene.ENDPOINT + "?" + Constant.API_CURSOR + "=" + nextInt;
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

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public List<GeneSet> getGeneSets() {
		return geneSets;
	}

	public void setGeneSets(List<GeneSet> geneSets) {
		this.geneSets = geneSets;
	}
}
