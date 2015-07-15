// TODO:
// - Wrap code in nxm constructor.
// - Unglobalize svg element by exposing it to the user of Clustergram
// - highlightDatasetTypes should do only one thing; it does two; read the top comment.
// - Unglobalize global_network_data 
// - Unglobalize matrix
// - Unglobalize spillover_x_offset
// - Merge load_local_network.js and visualize.js
// - Convert 1/0 to boolean.

	
var MIN_NODE_NUM = 10,
  	MAX_NODE_NUM = 2000,
	// TODO: Remove dependency on jQuery!
  	screenWidth  = $('body').width(),
	screenHeight = $('body').width();

var Clustergram = function(network_data, d$parentEl) {

	var col_nodes  = network_data.col_nodes;
	var row_nodes  = network_data.row_nodes;
	var inst_links = network_data.links;

	var matrix  = Matrix(network_data);
	var labels  = Labels(network_data);
	var margins = Margins(labels);

	debugger;
	var svg     = Svg(d$parentEl, network_data, matrix, labels, margins);
	var viz     = Viz();
	var fonts   = FontScale(d$parentEl, col_nodes, row_nodes, inst_links, svg);

	
	
	// Scaling functions to position cells.
	var x_scale = d3.scale.ordinal()
		.rangeBands([0, svg.svgWidth]);
	
	var y_scale = d3.scale.ordinal()
		.rangeBands([0, svg.svgHeight]); 

	var orders = Orders(network_data);
	x_scale.domain(orders.clust_row);
	y_scale.domain(orders.clust_col);

	border_width = x_scale.rangeBand() / 16.66;

	// define the zoom switch value - use viz_width and viz_height
	// switch from 1 to 2d zoom 
	zoom_switch = (svg.vizWidth / col_nodes.length)/(svg.vizHeight/row_nodes.length);



	// define global reordering value 
	// not currently reordering 
	global_reorder = false;	
	
	
	setVisualizationSize(svg, labels, col_nodes, row_nodes);
	
	
	initializeVariables(col_nodes, row_nodes, inst_links, d$parentEl, svg);
	res_color_dict = highlightDatasetGroups(col_nodes, row_nodes);
	setupGeneSearchFunctionality(row_nodes);

	// Define the variable function, a d3 method.
	zoom = d3.behavior.zoom().scaleExtent([1,real_zoom*zoom_switch]).on('zoom',zoomed);
	
	
	  // grey background rect for clustergram  
    d3.select('#clust_group')
	    .append("rect")
	    .attr("class", "background")
	    .attr('id','grey_background')
	    .attr("width", svg.width)
	    .attr("height", vizHeight);

	
	

	  // row labels 
	  //////////////////////////////////

	  // white background rect for row labels
	    	d$svgEl
	    .append('rect')
	    .attr('fill', 'white')
	    .attr('width', labels.rowWidth+'px')
	    .attr('height', '3000px')
	    .attr('class','white_bars');

	  // append group for row labels 
	  d$svgEl
	    .append("g")
	    .attr('id', 'row_labels')
	    .attr("transform", "translate(" + row_margin.left + "," + row_margin.top + ")")
	
	
  // generate and position the row labels
  var row_label_obj = d3.select('#row_labels')
    .selectAll('.row_label_text')
    .data(row_nodes)
    .enter()
    .append('g')
    .attr('class','row_label_text')
    .attr('transform', function(d, i) { return "translate(0," + y_scale(i) + ")"; })
    .on('click', reorder_click_row )
    .on('mouseover', function(){
      // highlight text
    	clust_group        .style('font-weight','bold');
    })
    .on("mouseout", function mouseout() {
      d3.select(this).select('text')
        .style('font-weight','normal');
      // reset highlighted col 
      d3.select('#clicked_row')
        .style('font-weight','bold');
    });

  // append row label text 
  row_label_obj
    .append('text')
    // !! this will be fixed once I have separate x and y scales 
    // !! can be improved 
    .attr('y', y_scale.rangeBand()/2 )
    .attr('dy', y_scale.rangeBand()/4)
    .attr('text-anchor','end')
    .style('font-size',fontScale.initial.row+'px')
    .text(function(d, i) { return d.name; } )

  // append rectangle behind text 
  row_label_obj
    .insert('rect','text')
    .attr('x',-10)
    .attr('y',0)
    .attr('width',10)
    .attr('height',10)
    .style('opacity',0);

  // change the size of the highlighting rects 
  row_label_obj
    .each(function(){
      // get the bounding box of the row label text 
      var bbox = d3.select(this)
                   .select('text')[0][0]
                   .getBBox();

      // use the bounding box to set the size of the rect 
      d3.select(this)
        .select('rect')
      .attr('x', bbox.x*0.5)
      .attr('y', 0)
      .attr('width', bbox.width*0.5)
      .attr('height', y_scale.rangeBand())
      .style('fill','yellow')
      .style('opacity',0);
    });

  // col labels 
  //////////////////////////////////

  // white background rect for col labels 
  d$svgEl
    .append('rect')
    .attr('fill', 'white')
    .attr('height', labels.colWidth+'px')
    .attr('width', '3000px')
    .attr('class','white_bars');

  // append group for column labels 
  d$svgEl
    .append("g")
    .attr('id', 'col_labels')
    .attr("transform", "translate(" + col_margin.left + "," + col_margin.top + ")");

  // offset click group column label 
  x_offset_click = x_scale.rangeBand()/2 + border_width
  // reduce width of rotated rects
  reduce_rect_width = x_scale.rangeBand()* 0.36 

  // add main column label group 
  col_label_obj = d3.select('#col_labels')
    .selectAll(".col_label_text")
    .data(col_nodes)
    .enter()
    .append("g")
    .attr("class", "col_label_text")
    .attr("transform", function(d, i) { return "translate(" + x_scale(i) + ") rotate(-90)"; })

  // append group for individual column label 
  col_label_click = col_label_obj
    // append new group for rect and label (not white lines)
    .append('g')
    .attr('class','col_label_click')
    // rotate column labels 
    .attr('transform', 'translate('+x_scale.rangeBand()/2+','+ x_offset_click +') rotate(45)')
    .on('click', function(d, i) {
    	reorderClickedColumn(this, col_nodes, row_nodes);
    })
    .on('mouseover', function(){
      // highlight text
      d3.select(this).select('text')
        .style('font-weight','bold');
    })
    .on("mouseout", function mouseout() {
      // d3.selectAll("text").classed("active", false);
      d3.select(this).select('text')
        .style('font-weight','normal');
      // reset highlighted col 
      d3.select('#clicked_col')
        .style('font-weight','bold');
    });

  // add column label 
  col_label_click
    .append("text")
    .attr("x", 0)
    .attr("y", x_scale.rangeBand() / 2)
    .attr('dx',2*border_width)
    // .attr("dy", ".32em")
    .attr("text-anchor", "start")
    .attr('full_name',function(d) { return d.name } )
    .style('font-size',fontScale.initial.col+'px')
    // remove underscores from name 
    .text(function(d, i) { return d.name.replace(/_/g, ' ') ; });

  // append rectangle behind text 
  col_label_click
    .insert('rect','text')
    .attr('x',10)
    .attr('y',0)
    .attr('width',10)
    .attr('height',10)
    .style('opacity',0);

  // change the size of the highlighting rects
  col_label_click
    .each(function(){

      // get the bounding box of the row label text 
      var bbox = d3.select(this)
                   .select('text')[0][0]
                   .getBBox();

      // use the bounding box to set the size of the rect 
      d3.select(this)
        .select('rect')
      .attr('x', bbox.x*1.25)
      .attr('y', 0)
      .attr('width', bbox.width*1.25)
      // used teh reduced rect width for the columsn 
      // reduced because thee rects are slanted
      .attr('height', x_scale.rangeBand()*0.6)
      // .attr('height', reduce_rect_width)
      .style('fill','yellow')
      .style('opacity',0);
    });


  // add triangle under rotated labels
  col_label_click
    .append('path')
    .style('stroke-width',0)
    .attr('d', function(d) { 
        // x and y are flipped since its rotated 
        origin_y = - border_width
        start_x  = 0;
        final_x  =  x_scale.rangeBand() - reduce_rect_width ;
        start_y  = -(x_scale.rangeBand() - reduce_rect_width + border_width) ;
        final_y  =  -border_width;
        output_string = 'M '+origin_y+',0 L ' + start_y + ',' + start_x + ', L ' + final_y + ','+final_x+' Z';
        return output_string;
       })
    // .attr('fill','#eee')
    // change the colors of the triangles 
    .attr('fill', function(d) {
      // look up color using data_group
      inst_color = res_color_dict[d.data_group];
      return inst_color;
    });


  // Rects to hide spillover 
  ///////////////////////////////

  // white rect to cover excess labels 
  d$svgEl
    .append('rect')
    .attr('fill', 'white')
    .attr('width',  labels.rowWidth+'px')
    .attr('height', labels.colWidth+'px')
    .attr('id','top_left_white');


  // hide spillover from right
  d$svgEl
    .append('rect')
    .attr('fill', 'white')
    .attr('width', '200px')
    .attr('height', '3000px')
    .attr('transform', function() { 
      tmp_left = margin.left + svg_width;
      // compensate for margin
      tmp_top = margin.top - 5;
      return 'translate('+tmp_left+','+tmp_top+')'
    })
    .attr('class','white_bars');

  // hide spillover from slanged column labels
  d$svgEl
    .append('path')
    .style('stroke-width','0')
    // mini-language for drawing path in d3, used to draw triangle 
    .attr('d', 'M 0,0 L 500,-500, L 500,0 Z')
    .attr('fill','white')
    .attr('id','slant_traingle')
    .attr('transform', function(){
      tmp_left = (margin.left + svg_width );
      tmp_top = labels.colWidth ; 
      return 'translate('+tmp_left+','+tmp_top+')' 
    })


  // initialize zoom and translate 
  ///////////////////////////////////

  // initialize translate vector to compensate for label margins 
  zoom.translate([ margin.left, margin.top]);

  // disable double-click zoom: double click should reset zoom level 
  // do this for all svg elements 
  d3.selectAll("svg").on("dblclick.zoom", null);    

  // double click to reset zoom - add transition 
  d$svgEl
    // for some reason, do not put brackets in these functions 
    .on('dblclick', function(){
      // apply the following two translate zoom to reset zoom 
      // programatically 
      // only apply programatic zoom if no transitions are occurring 
      if (global_reorder == 0){
        // apply programatic zoom  
        two_translate_zoom(0,0,1)
      };
    } );
};
