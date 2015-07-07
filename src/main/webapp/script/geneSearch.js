$(function() {
	
	var $input = $('input').first();
	
	$('form').first().submit(function(evt) {
		evt.originalEvent.preventDefault();
		window.location.href = "gene/" + $input.val();
	});
	
	function buildGeneAutocomplete() {
		$.ajax({
			url: 'api/gene',
			method: 'GET',
			success: function(data) {
				$input.autocomplete({
					source: JSON.parse(data)
				});
			}
		});
	};
	
	buildGeneAutocomplete();

});