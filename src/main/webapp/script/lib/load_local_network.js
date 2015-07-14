function load_class_clustergram(inst_prot_class){

	// clear input search box 
	$('#gene_search_box').val('')

	// only make clustergram if its a new class
	if (inst_prot_class != glob_prot_class)	{

		// save global variable
		glob_prot_class = inst_prot_class;

		// set up wait message before request is made 
		$.blockUI({ css: { 
		        border: 'none', 
		        padding: '15px', 
		        backgroundColor: '#000', 
		        '-webkit-border-radius': '10px', 
		        '-moz-border-radius': '10px', 
		        opacity: .8, 
		        color: '#fff' 
		    } });

		// use d3 to load a json 
		d3.json('script/lib/'+inst_prot_class+'_cumul_probs.json', function(network_data){

			// make global copy of network_data 
			global_network_data = network_data;

			// pass the network data to d3_clustergram 
			make_d3_clustergram(network_data);

		  // turn off the wait sign 
		  $.unblockUI();

		});

	};
};

/* Generate a list of genes for autocomplete.
 */
function setupGeneSearchFunctionality(rowNodes) {
	var $geneSearchBox = $('#gene_search_box'),
		allGenes = [];
	_.each(rowNodes, function(node) {
		allGenes.push(node.name);
	});
	
	function findGeneInCluster() {
		var searchGene = $geneSearchBox.val()
		if (allGenes.indexOf(searchGene) != -1) {
			zoomAndHighlightGene(allGenes, searchGene);	
		}
	};
	
	$geneSearchBox
		.autocomplete({
			source: allGenes
		})
		.keyup(function(e) {
			//debugger;
		    if (e.keyCode == 13) {
		    	findGeneInCluster();
		    }
		});

	$('#gene_search_submit').click(findGeneInCluster);
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