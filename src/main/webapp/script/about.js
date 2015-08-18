$(function() {

	Chart.defaults.global.responsive = true;
	
	$.ajax({
		url: 'json/stats.json',
		method: 'GET',
		contentType: 'application/json',
		success: function(data) {
			buildCharts(data.stats);
		},
		error: function(data) {
			console.log('Error');
		}
	});
	
	function buildCharts(resources) {

		var labels = [],
			resourceSizes = [];
		resources.forEach(function(resource, i) {
			resourceSizes.push(Math.log(resource.size));
			labels.push(resource.name);
		});

		var ctx = document.getElementById('chart').getContext('2d');
		new Chart(ctx).Bar({
			labels: labels,
			datasets: [
			    {
			    	fillColor: "#343844",
		            strokeColor: "#343844",
		            highlightFill: "rgba(220,220,220,0.75)",
		            highlightStroke: "rgba(220,220,220,1)",
		            data: resourceSizes
			    }
			]
		}, {});
	}
});