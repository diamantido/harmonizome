
/* Represents the margins of the visualization.
 */
var Margins = function(labels) {
	return {
		col: {
			top: labels.colWidth - labels.marginWidth,
			right: 0,
			bottom: 0,
			left: labels.rowWidth
		},
		row: {
			top: labels.colWidth,
			right: 0,
			bottom: 0,
			left: labels.rowWidth - labels.marginWidth
		},
		$: {
			top: labels.colWidth,
			right: 0,
			bottom: 0,
			left: labels.rowWidth 
		}
	};
}