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
			buildBarChart(data['attributesPerDataset']);
			buildGroupPieChart(data['datasetsPerDatasetGroup'], '#dataset-pie-chart', 'Datasets by Category');
			buildGroupPieChart(data['attributesPerAttributeGroup'], '#attribute-pie-chart', 'Attributes by Category');
		},
		error: function(data) {
			console.log('Error');
		}
	});
	
	function buildBarChart(data) {
		var categories = [],
			series = [],
			resources = [],
			i,
			resource,
			count;
		
		for (key in data) {
			resources.push({
				name: key,
				count: data[key]
			});
		}
		
		resources.sort(function(a, b) {
	        if (a.count > b.count)
	            return -1;
	        if (a.count < b.count)
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
			count = resource.count;
			if (count > 1) {
				count = Math.log(count) / Math.log(10);
			}
			series[0].data.push(count);
		}

		$('#bar-chart').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Gene Sets by Resource'
	        },
	        subtitle: {
	            text: 'The the y-axis is on a log base 10 scale. Hover over the bars to see the actual counts.'
	        },
	        xAxis: {
	            categories: categories,
	            crosshair: true
	        },
	        yAxis: {
	            title: {
	                text: 'Gene Sets'
	            }
	        },
	        tooltip: {
	            formatter: function() {
	                return '' +
	                    '<p style="margin:0;">' + this.x + '</p>' +
	                    '<p style="margin:0;">' + Math.round(Math.pow(10, this.y)) + '</p>';
	            },
	            useHTML: true
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
//				,
//            	sliced: true,
//            	selected: true
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
//	        tooltip: {
//	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//	        },
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
	
	
	
//	function buildGroupPieChart(groups, elemId, colorKey) {
//		
//		var toColors = {
//			datasets: {
//				'disease or phenotype associations': '#203d5b',
//				'genomics': '#2e5986',
//				'omics': '#C3D2FF',
//				'physical interactions': '#35679c',
//				'proteomics': '#3c76b1',
//				'structural or functional annotations': '#5c92ca',
//				'transcriptomics': '#86aed9'
//			},
//			attributes: {
//				'cell line, cell type or tissue': '#203d5b',
//				'chemical': '#274b70',
//				'disease, phenotype or trait': '#2e5986',
//				'functional term, phrase or reference': '#35679c',
//				'gene, protein or microRNA': '#3c76b1',
//				'molecular profile': '#4784c2',
//				'organism': 'red',
//				'sequence feature': '#71a0d1',
//				'structural feature': '#86aed9'
//			}
//		}
//			
//		
//		var data = [];
//		for (var key in groups) {
//			data.push({
//				value: groups[key],
//				label: key.replace(',', ' or'),
//		        color: toColors[colorKey][key],
//		        highlight: 'rgba(220,220,220,0.75)'
//			});
//		}
//		
//		Chart.defaults.global.tooltipTemplate = '<%= label %> : <%= value %>';
//		ctx = $(elemId).get(0).getContext('2d');
//		new Chart(ctx).Pie(data, {
//			segmentStrokeWidth : 1
//		});
//	}
});