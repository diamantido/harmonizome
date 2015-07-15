
/* Representation of the fonts on the page, particularly their scales.
 */
var FontScale = function(d$parentEl, colNodes, rowNodes, links, svg) {

  	var parentDimensions = d$parentEl.node().getBoundingClientRect(),
  		MAX_SCREEN_WIDTH = parentDimensions.width;

	// Output range is the *initial* font size; the font size may change
  	// when the user zooms.
	var scaleInitially = d3.scale.log()
		.domain([MIN_NODE_NUM, MAX_NODE_NUM])
		.range([15, 0.05]).clamp('true');

  	// Controls how much the font size is increased by zooming when the number
  	// of nodes is maxed and zooming is required.
  	var scaleOnScreenResize = d3.scale.log()
  		.domain([MIN_NODE_NUM, MAX_NODE_NUM])
  		.range([0.95, 0.0])
  		.clamp(true);

  	// Define screen width font size scale 
  	// having a small screen width should reduce the font size of the columns 
  	// this will be compensated by increasing the available real zoom 
  	var scaleByScreenWidth = d3.scale.linear()
  		.domain([400, 2000])
  		.range([0.75,1.15])
  		.clamp(true);

  	var scaleByScreenHeight = d3.scale.linear()
  		.domain([400, 2000])
  		.range([0.75,1.15])
  		.clamp(true);

  	return {
		initial: {
  			col: scaleInitially(colNodes.length) * scaleByScreenWidth(svg.vizWidth),
  			row: scaleInitially(rowNodes.length) * scaleByScreenHeight(svg.vizHeight)
  		},
  		screenResize: {
  			col: scaleOnScreenResize(colNodes.length),
  			row: scaleOnScreenResize(rowNodes.length)
  		}
  	};
};
