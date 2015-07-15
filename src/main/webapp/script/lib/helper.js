

// reorder columns with row click 
function reorder_click_row(d,i){

  // set up a global variable to track when a reordering is occurring 
  global_reorder = 1;

  // get inst row (gene)
  inst_gene = d3.select(this).select('text').text();

  // highlight clicked column 
  // first un-highlight all others 
  d3.selectAll('.rol_label_text').select('text')
    .style('font-weight','normal');
  // remove previous id 
  d3.select('#clicked_row')
    .attr('id','');

  // highlight current 
  d3.select(this).select('text')
    .style('font-weight','bold')
    .attr('id','clicked_row');

  // find the row number of this term from row_nodes 
  // gather row node names 
  tmp_arr = []
  for (i=0; i<row_nodes.length; i++){
    tmp_arr.push(row_nodes[i].name);
  }

  // find index 
  inst_row = _.indexOf( tmp_arr, inst_gene );

  // gather the values of the input genes 
  tmp_arr = [];
  for (j=0; j<col_nodes.length; j++) {
    tmp_arr.push(matrix[inst_row][j].value);
  }

  // sort the rows 
  tmp_sort = d3.range( tmp_arr.length ).sort(function(a, b) { return tmp_arr[b]  - tmp_arr[a]; })

  // resort the columns (resort x)
  x_scale.domain(tmp_sort);

  // reorder
  ////////////////////

  // define the t variable as the transition function 
  var t = clust_group.transition().duration(2500);

  // reorder matrix
  t.selectAll(".cell")
    .attr('x', function(d){ 
    return x_scale(d.pos_x);
  });

  // Move Row Labels
  d3.select('#col_labels').selectAll(".col_label_text")
    .transition().duration(2500)
    .attr("transform", function(d, i) { 
      return "translate(" + x_scale(i) + ")rotate(-90)"; 
    })
    // set global reorder to 0 when done reordering
    .each('end', function(){
      // set global reorder value to 0
      global_reorder = 0;
    });

  // !! this causes a bug with reordering the columns 
  // // zoom into and highlight selected row 
  // zoom_and_highlight_found_gene(inst_gene);

};




function two_translate_zoom(pan_dx, pan_dy, fin_zoom){

  console.log('two_translate_zoom')
  console.log('pan_dx: '+String(pan_dx))
  console.log('pan_dy: '+String(pan_dy))
  console.log('fin_zoom: '+String(fin_zoom))

  // define the commonly used variable half_height
  var half_height = viz_height/2 ;

  // y pan room, the pan room has to be less than half_height since 
  // zooming in on a gene that is near the top of the clustergram also causes 
  // panning out of the visible region  
  var y_pan_room = ((half_height)/zoom_switch);


  // prevent visualization from panning down too much 
  // when zooming into genes near the top of the clustergram 
  if (pan_dy >= half_height - y_pan_room){

    console.log('restricting pan down')

    // prevent the clustergram from panning down too much 
    // if the amount of panning is equal to the half_height then it needs to be reduced
    // effectively, the the visualization needs to be moved up (negative) by some factor
    // of the half width of the visualization. If there was no zooming involved, then the
    // visualization would be centered first, then panned to center the top term, then 
    // the correction would re-center it. However, because of the zooming the offset is 
    // reduced by the zoom factor (this is because the panning is occurring on something 
    // that will be zoomed into - this is why the pan_dy value is not scaled in the two
    // translate transformations, but it has to be scaled afterwards to set the translate
    // vector)
    // pan_dy = half_height - (half_height)/zoom_switch

    // if pan_dy is greater than the pan room, then panning has to be restricted
    // start by shifting back up (negative) by half_height/zoom_switch then shift bak down
    // by the difference between half_height and pan_dy (so that the top of the clustergram is 
    // visible)
    var shift_top_viz = half_height - pan_dy ;
    var shift_up_viz  = - half_height/zoom_switch + shift_top_viz ; 

    // reduce pan_dy so that the visualization does not get panned to far down
    pan_dy = pan_dy + shift_up_viz ;
  };

  // prevent visualization from panning up too much
  // when zooming into genes at the bottom of the clustergram 
  if (pan_dy < -(half_height - y_pan_room) ){

    console.log('restricting pan up')
    var shift_top_viz = half_height + pan_dy ;

    // does not seem to be needed 
    /////////
    // move up by one row height 
    // var move_up_one_row = y_scale.rangeBand();
    // // do not move up one row if the clustergram is square 
    // if (zoom_switch == 1){
    //   move_up_one_row = 0;
    // };

    var shift_up_viz  =  half_height/zoom_switch - shift_top_viz; //- move_up_one_row;

    // reduce pan_dy so that the visualization does not get panned to far down
    pan_dy = pan_dy + shift_up_viz ;

  };

  // will improve this !!
  zoom_y = fin_zoom; 
  zoom_x = 1;

  // search duration - the duration of zooming and panning 
  search_duration =700;

  // center_y
  center_y = -(zoom_y -1)*half_height;

  // transform clsut group 
  clust_group
    .transition()
    .duration(search_duration)
    // first apply the margin transformation
    // then zoom, then apply the final transformation 
    .attr('transform', 'translate(' + [ margin.left,  margin.top + center_y ] + ')'+' scale('+ 1 +',' + zoom_y + ')'+'translate(' + [  pan_dx,  pan_dy ] + ')');

  // transform row labels 
  d3.select('#row_labels')
    .transition()
    .duration(search_duration)
    .attr('transform', 'translate(' + [ row_margin.left,  margin.top + center_y ] + ')'+' scale('+ zoom_y +',' + zoom_y + ')'+'translate(' + [  0,  pan_dy ] + ')');

  // transform col labels
  // move down col labels as zooming occurs, subtract trans_x - 20 almost works 
  d3.select('#col_labels')
    .transition()
    .duration(search_duration)
    .attr('transform', 'translate(' + [ col_margin.left,  col_margin.top ] + ')'+' scale('+ 1 +',' + 1 + ')'+'translate(' + [  pan_dx,  0 ] + ')');


  // Font Sizes 
  //////////////////
  // reduce font-size to compensate for zoom 
  // calculate the recuction of the font size 
  reduce_font_size = d3.scale.linear().domain([0,1]).range([1,zoom_y]).clamp('true');
  // scale down the font to compensate for zooming 
  fin_font = default_fs_row/(reduce_font_size(reduce_font_size_factor_row)); 
  // add back the 'px' to the font size 
  fin_font = fin_font + 'px';
  // change the font size of the labels 
  d3.selectAll('.row_label_text')
    .transition()
    .duration(search_duration)
    .select('text')
    .style('font-size', fin_font);

  // resize the highlighting bar when performing two transition zoom 
  // re-size of the highlighting rects 
  d3.select('#row_labels')
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
      .style('fill','yellow');
    });


  // reduce font-size to compensate for zoom 
  // calculate the recuction of the font size 
  reduce_font_size = d3.scale.linear().domain([0,1]).range([1,zoom_x]).clamp('true');
  // scale down the font to compensate for zooming 
  fin_font = default_fs_col/(reduce_font_size(reduce_font_size_factor_col)); 
  // add back the 'px' to the font size 
  fin_font = fin_font + 'px';
  // change the font size of the labels 
  d3.selectAll('.col_label_text')
    .transition()
    .duration(search_duration)
    .select('text')
    .style('font-size', fin_font);

  // set y translate: center_y is positive, positive moves the visualization down 
  // the translate vector has the initial margin, the first y centering, and pan_dy
  // times the scaling zoom_y  
  var net_y_offset = margin.top + center_y +  pan_dy * zoom_y  ;

  // reset the zoom translate and zoom 
  zoom.scale(zoom_y);
  zoom.translate([  pan_dx, net_y_offset])

};

// reorder rows with column click 
function reorderClickedColumn(clickedEl, col_nodes, row_nodes){

	// set up a global variable to track when a reordering is occurring 
	global_reorder = 1;

  // get inst col (term)
  inst_term = d3.select(clickedEl).select('text').attr('full_name')

  // highlight clicked column 
  // first un-highlight all others 
  d3.selectAll('.col_label_text').select('text')
    .style('font-weight','normal');
  // remove previous id 
  d3.select('#clicked_col')
    .attr('id','');

  // highlight current 
  d3.select(clickedEl).select('text')
    .style('font-weight','bold')
    .attr('id','clicked_col');

  // find the column number of this term from col_nodes 
  // gather column node names 
  tmp_arr = []
  for (i=0; i<col_nodes.length; i++){
    tmp_arr.push(col_nodes[i].name);
  };

  // find index 
  inst_col = _.indexOf( tmp_arr, inst_term );

  // gather the values of the input genes 
  tmp_arr = [];
  for (i=0; i<row_nodes.length; i++) {
    tmp_arr.push(matrix[i][inst_col].value);
  };

  // sort the rows 
  tmp_sort = d3.range( tmp_arr.length).sort(function(a, b) { return tmp_arr[b]  - tmp_arr[a]; })

  // resort rows - y axis 
  y_scale.domain(tmp_sort);

  // reorder
  // define the t variable as the transition function 
  var t = clust_group.transition().duration(2500);

  // reorder matrix
  t.selectAll(".row")
    .attr("transform", function(d, i) { return "translate(0," + y_scale(i) + ")"; })
    .selectAll(".cell")
    .attr('x', function(d){ 
      return x_scale(d.pos_x);
    })

  // Move Row Labels
  // 
  d3.select('#row_labels').selectAll('.row_label_text')
    .transition().duration(2500)
    .attr('transform', function(d, i) { return 'translate(0,' + y_scale(i) + ')'; });

  // t.selectAll(".column")
  d3.select('#col_labels').selectAll(".col_label_text")
    .transition().duration(2500)
    .attr("transform", function(d, i) { 
      return "translate(" + x_scale(i) + ")rotate(-90)"; 
    })
    // set global reorder to 0 when done reordering
    .each('end', function(){
      // set global reorder value to 0
      global_reorder = 0;
    });

  // highlight selected column 
  ///////////////////////////////

  // unhilight and unbold all columns (already unbolded earlier)
  d3.selectAll('.col_label_text')
    .select('rect')
    .style('opacity',0);

  // highlight column name
  d3.select(this)
    .select('rect')
    .style('opacity',1);


};

// zoom into and highlight the found the gene 
function zoomAndHighlightGene(all_genes, search_gene){

  // unhighlight and unbold all genes 
  d3.selectAll('.row_label_text')
    .select('text')
    .style('font-weight','normal');
  d3.selectAll('.row_label_text')
    .select('rect')
    .style('opacity',0);

  // find the index of the gene 
  inst_gene_index = _.indexOf( all_genes, search_gene );  

  // get y position 
  inst_y_pos = y_scale(inst_gene_index)  ;

  // make row name bold 
  d3.selectAll('.row_label_text')
    .filter(function(d){ return d.name == search_gene})
    .select('text')
    .style('font-weight','bold');
  // highlight row name 
  d3.selectAll('.row_label_text')
    .filter(function(d){ return d.name == search_gene})
    .select('rect')
    .style('opacity',1);

  // calculate the y panning required to center the found gene 
  pan_dy = viz_height/2 - inst_y_pos;

  // use two translate method to control zooming 
  // pan_x, pan_y, zoom 
  // only apply programatic zoom if no transitions are occurring 
  if (!global_reorder){
    // apply programatic zoom  
    two_translate_zoom(0, pan_dy, zoom_switch );
  }; 
};


/* Set up the resource type color key and generate an array of genes for later use.
 */
function highlightDatasetGroups(colNodes, rowNodes) {

	var hexColors = ['#097054', '#FFDE00', '#6599FF', '#FF9900', '#834C24', '#003366', '#1F1209'],
		datasetGroups = [],
		hexColorToDatasetGroupMap = {};

	_.each(colNodes, function(node) {
		datasetGroups.push(node['data_group']);
	});

	// get unique groups
	datasetGroups = _.uniq(datasetGroups);

	_.each(datasetGroups, function(dsg, i) {
		hexColorToDatasetGroupMap[dsg] = hexColors[i];
	});

	var legendKeys = d3.select('#res_color_key_div')
		.selectAll('row')
		.data(datasetGroups)
		.enter()
		.append('row');/*
		.style('padding-top','15px');*/

	// add color 
	legendKeys
		.append('div')
		.attr('class','col-xs-2')
		// get rid of excess padding 
		/*.style('padding-left','5px')
		.style('padding-right','0px')
		.style('padding-top','1px')*/
		.append('div')
		.style('width','12px')
		.style('height','12px')
		.style('background-color', function(d){
			return hexColorToDatasetGroupMap[d];
		})

	// add names 
	legendKeys
		.append('div')
		.attr('class', 'col-xs-10 res_names_in_key')
		.append('text')
		.text(function(d) {
			return _(d.replace(/_/g, ' ')).capitalize();
		});

	
  
  return hexColorToDatasetGroupMap;


};



function reorder_clust_rank(order_type) {

  // set up a global variable to track when a reordering is occurring 
  global_reorder = true;

  // load orders 
  if ( order_type == 'clust' ){ 
    // order by enrichment 
    x_scale.domain(orders.clust_row);
    y_scale.domain(orders.clust_col);
  }
  else if (order_type == 'rank'){
    // order by enrichment 
    x_scale.domain(orders.rank_row);
    y_scale.domain(orders.rank_col);
  };

  // define the t variable as the transition function 
  var t = clust_group.transition().duration(2500);

  // reorder matrix
  t.selectAll(".row")
    .attr("transform", function(d, i) { return "translate(0," + y_scale(i) + ")"; })
    .selectAll(".cell")
    .attr('x', function(d){ 
      return x_scale(d.pos_x);
    })

  // Move Row Labels
  d3.select('#row_labels').selectAll('.row_label_text')
    .transition().duration(2500)
    .attr('transform', function(d, i) { 
      return 'translate(0,' + y_scale(i) + ')'; 
    });

  // Move Col Labels 
  d3.select('#col_labels').selectAll(".col_label_text")
    .transition().duration(2500)
    .attr("transform", function(d, i) { 
      return "translate(" + x_scale(i) + ")rotate(-90)"; 
    })
    // set global reorder to 0 when done reordering
    .each('end', function(){
      // set global reorder value to 0
    	global_reorder = false;
    });
};
