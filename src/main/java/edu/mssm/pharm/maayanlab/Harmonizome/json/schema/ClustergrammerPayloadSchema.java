package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;

public class ClustergrammerPayloadSchema {

	String title = "harmonizome";
	String link = "http://amp.pharm.mssm.edu/Harmonizome/visualize/heat_map/input_genes";
	String filter = "N_row_sum";
	Boolean is_up_down = false;
	List<ClustergrammerColumnSchema> columns;

	public ClustergrammerPayloadSchema(List<ClustergrammerColumnSchema> columns) {
		this.columns = columns;
	}
}
