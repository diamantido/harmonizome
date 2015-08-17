package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.SearchResults;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;

public class SearchResultsSchema {

	private int status = HttpStatusCode.OK.getValue();
	
	private String message = HttpStatusCode.OK.getMessage();
	
	private String next;
	
	private SearchResults searchResults;
	
	public SearchResultsSchema() {
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

	public SearchResults getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(SearchResults searchResults) {
		this.searchResults = searchResults;
	}
}
