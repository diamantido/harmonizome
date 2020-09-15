package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.List;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class ClustergrammerPayloadSchema {

	String title = "harmonizome";
	String link = Constant.ORIGIN() + "/" + Constant.HARMONIZOME_PREFIX() + "/visualize/heat_map/input_genes";
	String filter = "N_row_sum";
	Boolean is_up_down = false;
	List<ClustergrammerColumnSchema> columns;

	public ClustergrammerPayloadSchema(List<ClustergrammerColumnSchema> columns) {
		this.columns = columns;
	}
}
