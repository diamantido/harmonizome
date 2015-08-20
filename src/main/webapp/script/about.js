$(function() {

	Chart.defaults.global.responsive = true;
	Chart.defaults.global.tooltipCornerRadius = 0;

	$.ajax({
		url: 'api/1.0/stats',
		method: 'GET',
		success: function(data) {
			data = JSON.parse(data);
			buildBarChart(data['attributesPerDataset']);
			buildGroupPieChart(data['datasetsPerDatasetGroup'], '#dataset-pie-chart', 'datasets');
			buildGroupPieChart(data['attributesPerAttributeGroup'], '#attribute-pie-chart', 'attributes');
		},
		error: function(data) {
			console.log('Error');
		}
	});
	
	function buildBarChart(resources) {
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
		Chart.defaults.global.tooltipTemplate = '<%= Math.round(Math.pow(10, value)) %>';
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
	
	function buildGroupPieChart(groups, elemId, colorKey) {
		
		var toColors = {
			datasets: {
				'disease or phenotype associations': '#203d5b',
				'genomics': '#2e5986',
				'omics': '#C3D2FF',
				'physical interactions': '#35679c',
				'proteomics': '#3c76b1',
				'structural or functional annotations': '#5c92ca',
				'transcriptomics': '#86aed9'
			},
			attributes: {
				'cell line, cell type or tissue': '#203d5b',
				'chemical': '#274b70',
				'disease, phenotype or trait': '#2e5986',
				'functional term, phrase or reference': '#35679c',
				'gene, protein or microRNA': '#3c76b1',
				'molecular profile': '#4784c2',
				'organism': 'red',
				'sequence feature': '#71a0d1',
				'structural feature': '#86aed9'
			}
		}
			
		
		var data = [];
		for (var key in groups) {
			data.push({
				value: groups[key],
				label: key.replace(',', ' or'),
		        color: toColors[colorKey][key],
		        highlight: 'rgba(220,220,220,0.75)'
			});
		}
		
		Chart.defaults.global.tooltipTemplate = '<%= label %> : <%= value %>';
		ctx = $(elemId).get(0).getContext('2d');
		new Chart(ctx).Pie(data, {
			segmentStrokeWidth : 1
		});
	}
});