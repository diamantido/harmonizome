// initialize protein class 
var inst_prot_class = 'KIN';

// save global variable 
glob_prot_class = inst_prot_class;

var blockUI = function() {
	$.blockUI({
		css: { 
	        border: 'none', 
	        padding: '15px', 
	        backgroundColor: '#000', 
	        '-webkit-border-radius': '10px', 
	        '-moz-border-radius': '10px', 
	        opacity: .8, 
	        color: '#fff' 
		}
	});
};

var unblockUI = function() {
	$.unblockUI();
};

function buildMatrix(networkData) {

	blockUI();
	networkData = networkData || global_network_data;

	var d3_parentEl = d3.select("#svg_div");
	make_d3_clustergram(networkData, d3_parentEl);
	
	// once everything has been set up show color key and search box
	d3.select('#res_color_key_title').style('display', 'block');
	d3.select('#gene_search_container').style('display', 'block')
	d3.select('#gene_search_box').style('display', 'inline-block')
	d3.select('#gene_search_button').style('display', 'inline-block')
	
	// turn off the wait sign 
	unblockUI();

	d3.select(window).on('resize', function() {
		blockUI();
		console.log('resizing');
		make_d3_clustergram(networkData, d3_parentEl);
		unblockUI();
	}); 
};

d3.json('script/lib/' + inst_prot_class + '_cumul_probs.json', function(networkData) {
	// Make global copy of network_data 
	global_network_data = networkData;
	buildMatrix(networkData);
});