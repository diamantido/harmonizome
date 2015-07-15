
var Matrix = function(network) {
	
	var matrix = [];
	
	network.row_nodes.forEach( function(tmp,i) {
		matrix[i] = d3.range(network.col_nodes.length)
			.map(function(j) {
				return {
					pos_x: j,
					pos_y: i,
					value:0,
					group:0};
				});
	});

	network.links.forEach(function(link) {
		matrix[link.source][link.target].value = link.value;
		matrix[link.source][link.target].group = 1;
		matrix[link.source][link.target].color = link.color;
	});
	
	return matrix;
};
	