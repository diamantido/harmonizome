$(function() {
	
	Highcharts.setOptions({
		//colors: ['#343844', '#203d5b', '#2e5986', '#C3D2FF', '#35679c', '#3c76b1', '#5c92ca', '#86aed9'],
		colors: ['#2e5986', '#A51563', '#A026DE', '#2CA53C', '#86aed9', '#ED412B', '#EDB22B', '#EC1E8E', '#4161B9'],
		style: {
            fontFamily: '"Open Source Sans", helvetica, arial, sans-serif'
        }
	});

	$.ajax({
		url: 'api/1.0/stats',
		method: 'GET',
		success: function(data) {
			data = JSON.parse(data);
			buildBarChart(data['resources']);
			buildGroupPieChart(data['datasets'], '#dataset-pie-chart', 'Datasets by Category');
			buildGroupPieChart(data['attributes'], '#attribute-pie-chart', 'Attributes by Category');
		},
		error: function(data) {
			console.log('Error');
		}
	});
	
	function buildBarChart(resources) {
		var categories = [],
			series = [],
			i,
			resource,
			numGeneSets;
		
		resources.sort(function(a, b) {
	        if (a.numGeneSets > b.numGeneSets)
	            return -1;
	        if (a.numGeneSets < b.numGeneSets)
	            return 1;
	        return 0;
	    });

		series[0] = {
			data: [],
	        showInLegend: false,
	        name: 'Gene signature'
	    };

		for (i in resources) {
			resource = resources[i];
			categories.push(resource.name);
			numGeneSets = resource.numGeneSets;
			if (numGeneSets > 1) {
				numGeneSets = Math.log(numGeneSets) / Math.log(10);
			}
			series[0].data.push(numGeneSets);
		}
		
		$('#bar-chart').highcharts({
	        chart: {
	            type: 'bar',
	            height: series[0].data.length * 20 + 120
	        },
	        title: {
	            text: 'Gene Sets by Resource'
	        },
	        subtitle: {
	            text: 'The x-axis is on a log base 10 scale. Hover over the bars to see the actual counts.'
	        },
	        xAxis: {
	            categories: categories,
	            crosshair: true,
	            labels: {
	                formatter: function () {
	                	var name = this.value,
	                		resource;
	                	resource = $.grep(resources, function(v) {
		            	    return v.name === name;
		            	})[0];
	                    return '<span class="highchart-custom-label"><a href="' + resource.link + '">' + this.value + '</a></span>'
	                },
	                useHTML: true
	            }
	        },
	        yAxis: {
	            title: {
	                text: 'Gene Sets'
	            }
	        },
	        tooltip: {
	            formatter: function() {
	            	var name = this.x,
	            		resource;
	            	
	            	// Find the resource's metadata to display to user.
	            	resource = $.grep(resources, function(v) {
	            	    return v.name === name;
	            	})[0];

	                return '' +
	                	'<table>' +
	                    	'<caption>' + resource.name + '</caption>' +
	                    	'<tr>' +
	                    		'<td>Gene Sets</td><td>' + Math.round(Math.pow(10, this.y)) + '</td>' +
	                    	'</tr>' +
	                    	'<tr>' +
	                    		'<td>Description</td><td>' + resource.description + '</td>' +
	                    	'</tr>' +
	                    '</table>';
	            },
	            useHTML: true,
	            borderRadius: 0,
	            borderWidth: 1,
	            backgroundColor: "rgba(255,255,255,1)"
	        },
	        series: series
	    });
	}
	
	function buildGroupPieChart(groups, elemId, title) {
		var series = [{
            colorByPoint: true,
			data: []
		}];
		
		for (var key in groups) {
			series[0].data.push({
				name: key,
				y: groups[key]
			});
		}

		$(elemId).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        title: {
	            text: title
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
	            }
	        },
	        series: series
	    });
	}
});