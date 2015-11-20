$(function() {

    $('button').click(function() {
       var selectMsg = '(Please select a dataset)',
           ds1 = $('#dataset-1').find(':selected').val(),
           ds2 = $('#dataset-2').find(':selected').val();

        if (ds1 === selectMsg || ds2 === selectMsg) {
            alert('Please select two datasets.');
            return;
        } else if (ds1 === ds2) {
            alert('Please select distinct datasets.');
            return;
        }

        $.ajax({
            url: 'api/1.0/matrix/' + ds1 + '/' + ds2,
            type: 'GET',
            success: function(data) {
                showImage(JSON.parse(data));
            }
        });
    });

    function showImage(data) {
        var $matrix = $('#matrix'),
            $img = $('<img src="static/hdfs/dataset-dataset-matrices/' + data.image + '"/>');
        $matrix.empty();
        $matrix.append($img);
    }
});