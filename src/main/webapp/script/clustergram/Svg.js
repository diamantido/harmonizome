
/* Represents the SVG element, with helpers for creating, destroying, updating, clustering, etc.
 */
var Svg = function(d$parentEl, network, matrix, labels, margins) {

	var colNodes = network.col_nodes,
		rowNodes = network.row_nodes,
		x_offset = 50 + labels.spilloverXOffset,
		y_offset = 50,
		svgHeight = screenHeight - 230,
		svgWidth = screenWidth - 50;
		
	var d$svgEl = d$parentEl.append("svg");
	
	// Prevents visualization from stretching a few rows across the entire width 
	var preventColStretching = d3.scale.linear()
		.domain([1, 20])
		.range([0.05, 1])
		.clamp(true);

	var vizWidth = svgHeight * preventColStretching(colNodes.length) ; 

	// Ensure that width of cells is not less than height 
	if (colNodes.length > rowNodes.length) {
		vizHeight = svg.width*(rowNodes.length/colNodes.length);
		forceCellsToSquares = 1;
		// Ensure scaling does not cause the viz to be taller than the SVG. 
		if (vizHeight > svg.height){
			vizHeight = svg.height;
			forceCellsToSquares = 0;
		}
	} else {
		vizHeight = svgHeight;
		forceCellsToSquares = 0;
	}
	
	var x_scale = d3.scale.ordinal().rangeBands([0, vizWidth]);
	var y_scale = d3.scale.ordinal().rangeBands([0, vizHeight]); 

	var cluster = d$svgEl
		// The SVG can be larger than the visualization.
		.attr("width",  svgWidth  + margins.$.left + margins.$.right + labels.spilloverXOffset)
		.attr("height", svgHeight + margins.$.top  + margins.$.bottom)
		.attr('border', 1)
		//.call( zoom ) 
		.append("g")
		.attr('id', 'clust_group')
		.attr("transform", "translate(" + (margins.$.left) + "," + (margins.$.top) + ")")
		.selectAll(".row")
	    .data(matrix)
	    .enter()
	    .append("g")
	    .attr("class", "row")
	    .attr("transform", function(d, i) {
	    	return "translate(0," + y_scale(i) + ")";
	    })
	    .each(rowFunction);

    // horizontal lines
    row_obj.append('line')
    	.attr('x2', 20*svg.width)
    	.style('stroke-width', border_width/zoom_switch+'px');
    
    // append vertical line groups 
    var verticalLines = clust_group
    	.selectAll('.vert_lines')
    	.data(colNodes)
    	.enter()
    	.append('g')
    	.attr('class','vert_lines')
    	.attr('transform', function(d, i) {
    		return 'translate(' + x_scale(i) + ') rotate(-90)';
    	});

    verticalLines
    	.append('line')
    	.attr('x1',0)
    	.attr('x2',-20*vizHeight)
    	.style('stroke-width', border_width+'px');	
	
	function rowFunction(rowData) {
    	d3.select(this)
	     	.selectAll(".cell")
	     	.data(rowData)
	     	.enter()
	     	.append("rect")
	     	.attr('class', 'cell')
	     	.attr("x", function(d) {
	     		return x_scale(d.pos_x);
	     	})
	     	.attr("width", x_scale.rangeBand())
	     	.attr("height", y_scale.rangeBand())
	     	.style("fill-opacity", function(d) { 
	     		output_opacity = opacity_scale( Math.abs(d.value) );
	     		return output_opacity ; 
	     	}) 
	     	// Switch the color based on up/dn enrichment 
	     	.style('fill', function(d) { 
	     		if (d.value != 0){
	     			inst_color = d.color;
	     		} else {
	     			inst_color = null;
	     		}
	     		return inst_color ;
	     	})
	     	.on("mouseover", function(p) {
	     		d3.selectAll(".row_label_text text").classed("active", function(d, i) {
	     			return i == p.pos_y;
	     		});
	     		d3.selectAll(".col_label_text text").classed("active", function(d, i) {
	     			return i == p.pos_x;
	     		});
	     	})
	     	.on("mouseout", function mouseout() {
	     		d3.selectAll("text").classed("active", false);
	     	})
    };
	
	return {
	};
};