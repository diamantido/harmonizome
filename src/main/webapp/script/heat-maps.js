/* Utility functions for the analytics page.
 * -----------------------------------------
 */

HARMONIZOME.heatMaps = (function() {

    var URL_BASE = 'api/1.0/visualize/heat_map/',
        $VIZ_WRAPPER = $('.heat-map'),
        $VIZ_LINK = $('.heat-map-link');

    function setupIndividualDatasetHeatMapsPage(heatMapType) {
        $('select').change(function(evt) {
            var elem = $(evt.target).find(':selected'),
                encoded = encodeURIComponent(elem.text());

            if (elem.text() === '(Please select a dataset)') {
                emptyVisualization();
                return;
            }

            $.ajax({
                url: URL_BASE + heatMapType + "/" + encoded + '?type=' + heatMapType,
                type: 'GET',
                success: function(data) {
                    showDatasetHeatMap(JSON.parse(data));
                }
            });
        });
    }

    function setupDatasetPairHeatMapsPage() {

        var selectMsg = '(Please select a dataset)';

        $('select').eq(0).change(function(evt) {
            var ds1 = $(evt.target).find(':selected').text();
            if (ds1 === selectMsg) {
                $('#dataset-2').empty().parent().addClass('hidden');
                emptyVisualization();
                return;
            }
            $.ajax({
                url: 'api/1.0/visualize/heat_map/util/' + encodeURIComponent(ds1),
                method: 'GET',
                success: function(data) {
                    buildRightSelect(JSON.parse(data)['rightDatasets']);
                }
            });
        });

        $('button').click(function() {
            var ds1Text = $('select#dataset-1').find(':selected').text(),
                ds2Text = $('select#dataset-2').find(':selected').text(),
                ds1Val = encodeURIComponent(ds1Text),
                ds2Val = encodeURIComponent(ds2Text);

            if (ds1Text === selectMsg) {
                alert('Please select two datasets.');
                return;
            }

            $.ajax({
                url: URL_BASE + "dataset_pair/" + ds1Val + '/' + ds2Val,
                type: 'GET',
                success: function(data) {
                    var modifiedData = JSON.parse(data);
                    modifiedData.dataset1 = ds1Text;
                    modifiedData.dataset2 = ds2Text;
                    showDatasetPairVisualization(modifiedData);
                }
            });
        });
    }

    function buildRightSelect(datasets) {
        var $select = $('#dataset-2').empty();
        $.each(datasets, function(i, obj) {
            $select.append('<option>' + obj + '</option>');
        });
        $select.parent().removeClass('hidden');
    }

    function pluralize(attribute) {
        if (attribute === 'protein complex') {
            return attribute + 'es';
        }
        return attribute + 's';
    }

    function getIframeOrImage(data) {
        var image;
        if (typeof data.clustergrammerLink !== 'undefined') {
            return '<iframe src="' + data.clustergrammerLink + '"></iframe>';
        } else {
            image = data.imageLink || 'https://placeholdit.imgix.net/~text?txtsize=13&w=930&h=733';
            return '<img class="img-responsive" src="' + image + '"/>';
        }
    }

    function getLabelsIfNecessary(data, isPair) {
        var hasClustergrammerLink = typeof data.clustergrammerLink !== 'undefined';
        if (!isPair) {
            if (hasClustergrammerLink) {
                return '';
            } else {
                return '' +
                    '<div class="heat-map-header">' +
                    '   <p><em>No interactive hierarchical clustering is available at this time.</em></p>' +
                    '   <p><strong>' + data.colLabel + '</strong></p>' +
                    '   <p>compared with</p>' +
                    '   <p><strong>' + data.rowLabel + '</strong></p>' +
                    '</div>';
            }
        } else {
            if (hasClustergrammerLink) {
                return '';
            } else {
                return '' +
                    '<div class="heat-map-header">' +
                    '   <p>' + pluralize(data.attributeType1) + ' from</p>' +
                    '   <p><strong>' + data.dataset1 + '</strong></p>' +
                    '   <p>compared with</p>' +
                    '   <p>' + pluralize(data.attributeType2) + ' from</p>' +
                    '   <p><strong>' + data.dataset2 + '</strong></p>' +
                    '</div>';
            }
        }

    }

    function showDatasetHeatMap(data) {
        $VIZ_WRAPPER
            .hide()
            .empty()
            .append(
                $(getLabelsIfNecessary(data, false) + getIframeOrImage(data))
            )
            .fadeIn();
    }

    function showDatasetPairVisualization(data) {
        $VIZ_WRAPPER
            .hide()
            .empty()
            .append(
                getLabelsIfNecessary(data, true) + getIframeOrImage(data)
            )
            .fadeIn();
    }

    /* On dataset pages, show Clustergrammer when user clicks preview image.
     */
    var clickedHeatMap = [];
    function setupVisualizationsOnDatasetPages() {
        $('.dataset-page .heat-maps').click(function(evt) {
            var $img = $(evt.target),
                baseUrl = $img.attr('data-heat-map-url'),
                type = $img.attr('data-heat-map-type'),
                dataset = encodeURIComponent($img.attr('data-heat-map-dataset')),
                $clustergrammerWrapper = $('.clustergrammer-wrapper');

            if (clickedHeatMap[0] === type) {
                emptyVisualization();
                clickedHeatMap[0] = undefined;
                return;
            } else {
                clickedHeatMap[0] = type;
            }

            $.ajax({
                url: baseUrl + "/" + type + "/" + dataset,
                method: 'GET',
                success: function(data) {
                    data = JSON.parse(data);
                    showDatasetHeatMap(data);
                }
            });
        });
    }
    
    /* On heat map with input genes page, handles submit the form to the API.
     */
    function setupHeatMapWithInputGenesPage() {
    	$('form button').click(function(evt) {
    		evt.preventDefault();
    		emptyVisualization();
    		var genes = $('textarea').val().trim().split('\n'),
    			dataset = $('select').val(),
    			promise;

    		if (genes.length == 1 && genes[0] === '') {
    			alert('Please input a few genes.');
    			return;
    		}
    		if (dataset == '(Please select a dataset)') {
    			alert('Please select a dataset.');
    			return;
    		}

    		promise = $.post(URL_BASE + 'input_genes', {
            	'dataset': dataset,
            	'genes': genes,
            });
    		promise.done(function(data) {
            	var data = JSON.parse(data);
            	data.clustergrammerLink = data.link + '?preview=true';
            	data.colLabel = 'columns';
            	data.rowLabel = 'rows';
            	showHeatMapLink(data);
            	showDatasetHeatMap(data);
            });
    	});
    }
    
    function showHeatMapLink(data) {
    	$VIZ_LINK.show().text(data.link);
    }

    function emptyVisualization() {
    	$VIZ_LINK.hide();
        $VIZ_WRAPPER.empty();
    }
    
    return {
    	setupDatasetPairHeatMapsPage: setupDatasetPairHeatMapsPage,
    	setupVisualizationsOnDatasetPages: setupVisualizationsOnDatasetPages,
    	setupIndividualDatasetHeatMapsPage: setupIndividualDatasetHeatMapsPage,
    	setupHeatMapWithInputGenesPage: setupHeatMapWithInputGenesPage
    };
})();