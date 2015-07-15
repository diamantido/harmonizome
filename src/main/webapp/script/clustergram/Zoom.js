









// define zoomed function 
function zoomed() {

  // gather transformation components 
  /////////////////////////////////////
  // gather zoom components 
  zoom_x = d3.event.scale;
  zoom_y = d3.event.scale;

  // gather translate vector components 
  trans_x = d3.event.translate[0] - margin.left;
  trans_y = d3.event.translate[1] - margin.top;
  
  // apply transformation: no transition duration when zooming with mouse 
  apply_transformation(trans_x, trans_y, zoom_x, zoom_y, 0);

  // reset highlighted col 
  d3.select('#clicked_col')
    // .style('font-size',fontScale.initial.col*1.25)
    .style('font-weight','bold');
};

// apply transformation 
function apply_transformation(trans_x, trans_y, zoom_x, zoom_y, duration){
 
  // define d3 scale 
  d3_scale = zoom_x ; 

  // y - rules 
  ///////////////////////////////////////////////////

  // available panning room in the y direction 
  // multiple extra room (zoom - 1) by the width
  // always defined in the same way 
  pan_room_y = (d3_scale - 1) * svg_height ;

  // if the transformation is from a gene search, the remove pan_room_y restriction 
  if (duration > 0){
    // set pan_room_y to svg_height - removing restriction 
    pan_room_y = svg_height;
  };

  // do not translate if translate in y direction is positive 
  if (trans_y >= 0 ) {
    console.log('\nrestrict panning in the positive y direction')
    console.log(trans_y)
    // restrict transformation parameters 
    // no panning in either direction 
    trans_y = 0; 
  }
  // restrict y pan to pan_room_y if necessary 
  else if (trans_y <= -pan_room_y) {
    trans_y = -pan_room_y; 
    console.log('\nrestrict y panning based on y panning ')
  };

  // x - rules 
  ///////////////////////////////////////////////////
  // zoom in y direction only - translate in y only
  if (d3_scale < zoom_switch) {
    // no x translate or zoom 
    trans_x = 0;
    zoom_x = 1;

  }
  // zoom in both directions 
  // scale is greater than zoom_switch 
  else{

    // available panning room in the x direction 
    // multiple extra room (zoom - 1) by the width
    pan_room_x = (d3_scale/zoom_switch - 1) * svg_width ;

    // no panning in the positive direction 
    if (trans_x > 0){

      // restrict transformation parameters 
      // no panning in the x direction 
      trans_x = 0; 
      // set zoom_x
      zoom_x = d3_scale/zoom_switch;

    }
    // restrict panning to pan_room_x 
    else if (trans_x <= -pan_room_x){

      // restrict transformation parameters 
      // no panning in the x direction 
      trans_x = -pan_room_x; 
      // set zoom_x 
      zoom_x = d3_scale/zoom_switch;

    }
    // allow two dimensional panning 
    else{

      // restrict transformation parameters 
      // set zoom_x 
      zoom_x = d3_scale/zoom_switch;

    };

  };
 
  // apply transformation and reset translate vector 
  // the zoom vector (zoom.scale) never gets reset 
  ///////////////////////////////////////////////////
  // translate clustergram 
  clust_group
    .attr('transform','translate(' + [ margin.left + trans_x, margin.top + trans_y ] + ') scale('+ zoom_x +',' + zoom_y + ')');

  // transform row labels 
  d3.select('#row_labels')
    .attr('transform','translate(' + [row_margin.left , margin.top + trans_y] + ') scale(' + zoom_y + ')');

  // transform col labels
  // move down col labels as zooming occurs, subtract trans_x - 20 almost works 
  d3.select('#col_labels')
    .attr('transform','translate(' + [col_margin.left + trans_x , col_margin.top] + ') scale(' + zoom_x + ')');

  // reset translate vector - add back margins to trans_x and trans_y  
  zoom
    .translate([ trans_x +  margin.left, trans_y + margin.top]);

  // Font Sizes 
  //////////////////
  // reduce the font size by dividing by some part of the zoom 
  // if reduce_font_size_factor_ is 1, then the font will be divided by the whole zoom - and the labels will not increase in size 
  // if reduce_font_size_factor_ is 0, then the font will be divided 1 - and the labels will increase cuction of the font size 
  reduce_font_size = d3.scale.linear().domain([0,1]).range([1,zoom_y]).clamp('true');
  // scale down the font to compensate for zooming 
  fin_font = fontScale.initial.row / (reduce_font_size(fontScale.screenResize.row)); 
  // add back the 'px' to the font size 
  fin_font = fin_font + 'px';
  // change the font size of the labels 
  d3.selectAll('.row_label_text')
    .select('text')
    .style('font-size', fin_font);

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
  fin_font = fontScale.initial.col/(reduce_font_size(fontScale.screenResize.col)); 
  // add back the 'px' to the font size 
  fin_font = fin_font + 'px';
  // change the font size of the labels 
  d3.selectAll('.col_label_text')
    .select('text')
    .style('font-size', fin_font);

  // change the size of the highlighting rects
  // col_label_click
  d3.select('#col_labels')
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
      .attr('width', bbox.width * 1.25)
      // used teh reduced rect width for the columsn 
      // reduced because thee rects are slanted
      .attr('height', x_scale.rangeBand()*0.6)
      .style('fill','yellow')
      .style('opacity',0);
    });

};