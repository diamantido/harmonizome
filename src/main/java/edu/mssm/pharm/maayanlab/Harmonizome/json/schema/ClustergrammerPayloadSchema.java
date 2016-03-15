package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

public class ClustergrammerPayloadSchema {

	String title;
	String link;
	String filter;
	Boolean is_up_down;
	List<ClustergrammerColumnSchema> columns;

	public ClustergrammerPayloadSchema(String title, String link, String filter, Boolean is_up_down, List<ClustergrammerColumnSchema> columns) {
		this.title = title;
		this.link = link;
		this.filter = filter;
		this.is_up_down = is_up_down;
		this.columns = columns;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Boolean getIs_up_down() {
		return is_up_down;
	}

	public void setIs_up_down(Boolean is_up_down) {
		this.is_up_down = is_up_down;
	}

	public List<ClustergrammerColumnSchema> getColumns() {
		return columns;
	}

	public void setColumns(List<ClustergrammerColumnSchema> columns) {
		this.columns = columns;
	}
}
