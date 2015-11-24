$(function() {

    var URL_BASE = 'api/1.0/visualize/heat_map/',
        $VIZ_WRAPPER = $('.image-wrapper');

    if ($('.dataset-pair-heat-maps-page').length) {
        setupDatasetPairHeatMapsPage();
    } else {
        var heatMapType = $('.heat-map-page').attr('data-heat-map-type');
        console.log(heatMapType);
        setupIndividualDatasetHeatMapsPage(heatMapType);
    }

    function setupIndividualDatasetHeatMapsPage(heatMapType) {
        $('select').change(function(evt) {
            var elem = $(evt.target).find(':selected'),
                encoded = encodeURIComponent(elem.text());

            if (elem.text() === '(Please select a dataset)') {
                emptyVisualization();
                return;
            }

            $.ajax({
                url: URL_BASE + encoded + '?type=' + heatMapType,
                type: 'GET',
                success: function(data) {
                    showDatasetVisualization(JSON.parse(data));
                }
            });
        });
    }

    function setupDatasetPairHeatMapsPage() {
        $('button').click(function() {
            var selectMsg = '(Please select a dataset)',
                ds1Text = $('select#dataset-1').find(':selected').text(),
                ds2Text = $('select#dataset-2').find(':selected').text(),
                ds1Val = encodeURIComponent(ds1Text),
                ds2Val = encodeURIComponent(ds2Text);

            if (ds1Text === selectMsg || ds2Text === selectMsg) {
                alert('Please select two datasets.');
                return;
            } else if (ds1Text === ds2Text) {
                alert('Please select distinct datasets.');
                return;
            }

            $.ajax({
                url: URL_BASE + ds1Val + '/' + ds2Val,
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

    function pluralize(attribute) {
        if (attribute === 'protein complex') {
            return attribute + 'es';
        }
        return attribute + 's';
    }

    function getVisualElem(data) {
        var image;
        if (typeof data.clustergrammerLink !== 'undefined') {
            return '<iframe src="' + data.clustergrammerLink + '"></iframe>';
        } else {
            image = data.imageLink || 'https://placeholdit.imgix.net/~text?txtsize=13&w=930&h=733';
            return '<img class="img-responsive" src="' + image + '"/>';
        }
    }

    function showDatasetVisualization(data) {
        var $img = $('' +
            '<div class="header">' +
            //'   <p>' + pluralize(data.attributeType1) + ' from</p>' +
            //'   <p><strong>' + data.dataset1 + '</strong></p>' +
            //'   <p>compared with</p>' +
            //'   <p>' + pluralize(data.attributeType2) + ' from</p>' +
            //'   <p><strong>' + data.dataset2 + '</strong></p>' +
            '</div>' +
            getVisualElem(data)
        );

        $VIZ_WRAPPER
            .hide()
            .empty()
            .append($img)
            .fadeIn();
    }

    function showDatasetPairVisualization(data) {
        var $img = $('' +
            '<div class="header">' +
            '   <p>' + pluralize(data.attributeType1) + ' from</p>' +
            '   <p><strong>' + data.dataset1 + '</strong></p>' +
            '   <p>compared with</p>' +
            '   <p>' + pluralize(data.attributeType2) + ' from</p>' +
            '   <p><strong>' + data.dataset2 + '</strong></p>' +
            '</div>' +
            getVisualElem(data)
        );

        $VIZ_WRAPPER
            .hide()
            .empty()
            .append($img)
            .fadeIn();
    }

    function emptyVisualization() {
        $VIZ_WRAPPER.empty();
    }
});