$(function() {

	Chart.defaults.global.responsive = true;
	Chart.defaults.global.tooltipCornerRadius = 0;
	Chart.defaults.global.tooltipTemplate = '<%= Math.round(Math.pow(10, value)) %>';
	
	
	
	$.ajax({
		url: 'api/1.0/stats',
		method: 'GET',
		success: function(data) {
			buildBarChar(JSON.parse(data));
		},
		error: function(data) {
			console.log('Error');
		}
	});
	
	function buildBarChar(resources) {
		var labels = [],
			resourceSizes = [],
			ctx;
		for (var key in resources) {
			var count = resources[key];
			if (count > 1) {
				count = Math.log(resources[key]) / Math.log(10);
			}
			resourceSizes.push(count);
			labels.push(key);
		}

		ctx = $('#bar-chart').get(0).getContext('2d');
		new Chart(ctx).Bar({
			labels: labels,
			responsive: true,
			datasets: [
			    {
			    	fillColor: '#343844',
		            strokeColor: '#343844',
		            highlightFill: 'rgba(220,220,220,0.75)',
		            highlightStroke: 'rgba(220,220,220,1)',
		            data: resourceSizes
			    }
			]
		}, {});
	}
	
	buildPieChart();
	function buildPieChart() {
		
		var data = [
		            {
		                value: 300,
		                color:"#F7464A",
		                highlight: "#FF5A5E",
		                label: "Red"
		            },
		            {
		                value: 50,
		                color: "#46BFBD",
		                highlight: "#5AD3D1",
		                label: "Green"
		            },
		            {
		                value: 100,
		                color: "#FDB45C",
		                highlight: "#FFC870",
		                label: "Yellow"
		            }
		        ]
		
		ctx = $('.pie-chart').get(0).getContext('2d');
		new Chart(ctx).Pie(data, {});
		ctx = $('.pie-chart').get(1).getContext('2d');
		new Chart(ctx).Pie(data, {});
	}
});