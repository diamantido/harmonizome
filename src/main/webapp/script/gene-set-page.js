/* Utility functions for gene set pages.
 * -------------------------------------
 */

HARMONIZOME.setupDataTable = function(isContinuousValued) {

	var $table = $('.data-table');
	
	var config = {
        bPaginate: true,
        //bSort: false,
        iDisplayLength: 20,
        oLanguage: {
            sSearch: "Filter"
        },
        fnInitComplete: function() {
        	if (isContinuousValued) {
        		var $th = $table.find('thead th').eq(2);
        		//$th.wrapInner('<span></span>');
        		$th.append(' <span class="glyphicon glyphicon-question-sign"></span>');
        		var $span = $th.find('span');
        		$span.attr('data-toggle', 'tooltip');
        		$span.attr('title', 'Indicates the relative strength of the functional associations. Standardized values are related to empirical p-values as abs(standardized value) = -log10(p-value) and are only available for initially continuous-valued datasets.');
        		$span.tooltip();
        	}
        	$table.fadeIn();
        }
    };
	
	if (isContinuousValued) {
		config.order = [[ 2, 'desc' ]];
	}
	
	$table.dataTable(config);
};
