
/* Constructs a Label instance, accounting for the number of characters in the
 * text, margins, etc.
 */
function Labels(network) {

	var colNodes = network.col_nodes,
		rowNodes = network.row_nodes,
	
		// Find the label with the most characters and use it to adjust the row and col margins 
		ROW_MAX_CHARS = _.max(rowNodes, function(node) { return node.name.length; }).name.length,
		COL_MAX_CHARS = _.max(colNodes, function(node) { return node.name.length; }).name.length,
		// Account for the triangular space created by the diagonal text.
		TRIANGLE_SPACE = 30,
		// Define label scale parameters; the more characters in the longest name, the larger the margin.
		MIN_NUM_CHARS = 5,
		MAX_NUM_CHARS = 40,
		MIN_LABEL_WIDTH = 60,
		MAX_LABEL_WIDTH = 200;
	
	// Scales the labels according to number of characters in max label.
	var labelPositionScale = d3.scale.linear()
		.domain([MIN_NUM_CHARS, MAX_NUM_CHARS])
		.range([MIN_LABEL_WIDTH, MAX_LABEL_WIDTH])
		.clamp(true);

	// TODO: Nest scales in "scale" object.
	return {
		// Distance between labels and clustergram
		marginWidth: 5,
		rowWidth: labelPositionScale(ROW_MAX_CHARS),
		colWidth: labelPositionScale(COL_MAX_CHARS) + TRIANGLE_SPACE,
		// Adds extra whitespace for diagonal labels.
		spilloverXOffset: labelPositionScale(COL_MAX_CHARS) * 0.8
	};
};