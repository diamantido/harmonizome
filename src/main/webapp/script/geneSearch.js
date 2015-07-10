$(function() {

	var $input = $('input').first().autocomplete({
		minLength: 3,
		source: genes
	});

	$('form').first().submit(function(evt) {
		evt.originalEvent.preventDefault();
		window.location.href = "gene/" + $input.val();
	});

});