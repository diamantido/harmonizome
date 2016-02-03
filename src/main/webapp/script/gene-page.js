/* Utility functions for gene pages.
 * ---------------------------------
 */

HARMONIZOME.setupShowByDatasetFunctionality = function() {
    var $showHideButtons = $('.dataset-row .glyphicon');
    $showHideButtons.click(function (evt) {
        var $dataset = $(evt.target).parent(),
            url = $dataset.attr('data-associations-more-url'),
            dataset = $dataset.attr('data-associations-css-selector'),
            $associations = $('.association-list.' + dataset);

        $.ajax({
            url: url,
            method: 'GET',
            success: function (data) {
                data = JSON.parse(data);
                HARMONIZOME.showAssociations($dataset, $associations, data);
            }
        });
    });
}

/* Show gene sets based on dataset selected on gene page.
 */
HARMONIZOME.showAssociations = function($dataset, $associations, data) {
    var $fillerTd = $('<td class="col-md-1"></td>'),
        $listTd = $('<td class="col-md-11" colspan="2"></td>'),
        $plusButton = $dataset.find('.glyphicon.glyphicon-plus'),
        $minusButton = $dataset.find('.glyphicon.glyphicon-minus');

    $associations.empty();
    $associations.append($fillerTd);
    $associations.append($listTd);

    if (data.up.name) {
        $listTd.append('<p><strong>' + data.up.list.length + ' <em>' + data.up.name + '</em> associations</strong></p>');
    }

    /* Sorts associations by standardized values if they exist. Otherwise sorts
     * alphabetically.
     */
    var assocSorter = function(a, b) {
    	if (a.standardizedValue && b.standardizedValue) {
    		if (a.standardizedValue < 0 && b.standardizedValue < 0) {
    			// Numerically, least to greatest, i.e. more negative is more interesting.
    			return a.standardizedValue - b.standardizedValue;
    		}
    		// Numerically, greatest to least.
    		return b.standardizedValue - a.standardizedValue;
    	} else {
    		// Alphabetically.
    		return a.geneSet.name.localeCompare(b.geneSet.name);
    	}
    };
    
    var upGenes = [],
    	upList;
    
    upList = data.up.list.sort(assocSorter);  
    $.each(upList, function (i, assoc) {
    	var assocHtml = '' +
    		'<span class="association">' +
				'<a href="' + HARMONIZOME.cleanGeneSetHref(assoc.geneSet.href) + '">' + HARMONIZOME.cleanGeneSetName(assoc.geneSet.name) + '</a>';
    	if (assoc.standardizedValue) {
    		assocHtml += ' [' + assoc.standardizedValue + ']';
    	}
    	assocHtml += '</span>'
        upGenes.push(assocHtml);
    });
    $listTd.append(upGenes.join(''));

    if (data.down.list.length != 0) {
        if (data.down.name) {
            $listTd.append('<p class="last"><strong>' + data.down.list.length + ' <em>' + data.down.name + '</em> associations</strong></p>');
        }
        
        var downGenes = [],
        	downList;
        
        downList = data.down.list.sort(assocSorter);  
        $.each(downList, function (i, assoc) {
        	var assocHtml = '' +
        		'<span class="association">' +
        			'<a href="' + HARMONIZOME.cleanGeneSetHref(assoc.geneSet.href) + '">' + HARMONIZOME.cleanGeneSetName(assoc.geneSet.name) + '</a>';
        	
        	if (assoc.standardizedValue) {
        		assocHtml += ' [' + assoc.standardizedValue + ']';
        	}
        	assocHtml += '</span>';
            downGenes.push(assocHtml);
        });
        $listTd.append(downGenes.join(''));
    }

    if ($associations.hasClass('active')) {
    	$associations.fadeOut();
    } else {
    	$associations.fadeIn();
    }

    $plusButton.toggleClass('hidden');
    $minusButton.toggleClass('hidden');
    $associations.toggleClass('active');
}

/* Returns a user readable version of a gene set name.
 */
HARMONIZOME.cleanGeneSetName = function(geneSetName) {
    return geneSetName.split('/')[0];
}

/* Returns a link for the gene set page rather than API endpoint.
 */
HARMONIZOME.cleanGeneSetHref = function (geneSetHref) {
    return geneSetHref.replace('/' + HARMONIZOME.API_URL, '');
}
