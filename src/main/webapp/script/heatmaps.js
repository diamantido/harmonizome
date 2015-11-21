$(function() {

    $('button').click(function() {
       var selectMsg = '(Please select a dataset)',
           ds1 = $('#dataset-1').find(':selected'),
           ds2 = $('#dataset-2').find(':selected'),
           ds1Text = ds1.text(),
           ds2Text = ds2.text(),
           ds1Val = ds1.val(),
           ds2Val = ds2.val();

        if (ds1Text === selectMsg || ds2Text === selectMsg) {
            alert('Please select two datasets.');
            return;
        } else if (ds1Text === ds2Text) {
            alert('Please select distinct datasets.');
            return;
        }

        $.ajax({
            url: 'api/1.0/matrix/' + ds1Val + '/' + ds2Val,
            type: 'GET',
            success: function(data) {
                var modifiedData = JSON.parse(data);
                modifiedData.dataset_1 = ds1Text;
                modifiedData.dataset_2 = ds2Text;
                showImage(modifiedData);
            }
        });
    });

    function showImage(data) {

        var STATIC_URL = 'http://amp.pharm.mssm.edu/static/harmonizome/heat_maps/dataset_pairs/',
            $imgWrapper = $('.image-wrapper'),
            image,
            $img,
            attrType1,
            attrType2;

        if (data.image) {
            image = STATIC_URL + data.image
        } else {
            image = 'https://placeholdit.imgix.net/~text?txtsize=13&w=930&h=733';
        }
        if (data.attribute_type_1 === 'protein complex') {
            attrType1 = data.attribute_type_1 + 'es';
        } else {
            attrType1 = data.attribute_type_1 + 's';
        }
        if (data.attribute_type_2 === 'protein complex') {
            attrType2 = data.attribute_type_2 + 'es';
        } else {
            attrType2 = data.attribute_type_2 + 's';
        }

        $img = $('' +
            '<div class="header">' +
            '   <p><strong>' + attrType1 + ' from ' + data.dataset_1 + '</strong></p>' +
            '   <p>compared with</p>' +
            '   <p><strong>' + attrType2 + ' from ' + data.dataset_2 + '</strong></p>' +
            '</div>' +
            '<img src="' + image + '"/>'
        );

        $imgWrapper
            .hide()
            .empty()
            .append($img)
            .fadeIn();
    }
});