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