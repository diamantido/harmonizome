
var Orders = function(network) {
	var colNodes = network.colNodes,
		rowNodes = network.rowNodes;
	return {
	    name:      d3.range(colNodes.length).sort(function(a, b) { return d3.ascending(colNodes[a].name, colNodes[b].name); }),
	    rank_row:  d3.range(colNodes.length).sort(function(a, b) { return colNodes[b].rank - colNodes[a].rank; }),
	    rank_col:  d3.range(rowNodes.length).sort(function(a, b) { return rowNodes[b].rank - rowNodes[a].rank; }),
	    clust_row: d3.range(colNodes.length).sort(function(a, b) { return colNodes[b].clust - colNodes[a].clust; }),
	    clust_col: d3.range(rowNodes.length).sort(function(a, b) { return rowNodes[b].clust - rowNodes[a].clust; })
	};
};