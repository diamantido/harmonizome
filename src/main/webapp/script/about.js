$(function() {
	
	var API_URL = 'api/1.0/';
	
	Chart.defaults.global.responsive = true;
	
	$.ajax({
		url: API_URL + 'stats',
		method: 'GET',
		success: function(data) {
			data = JSON.parse(data);
			buildCharts(data);
		}
	});
	
	function buildCharts(resources) {
		var labels = [],
			resourceSizes = [];
		resources.forEach(function(resource, i) {
			resourceSizes.push(resource.size);
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


/*$(function() {
	
	var tree = {
		name: 'tree',
		children: <%= request.getAttribute("resources") %>
	};

	var $parentEl = $('#treemap'),
        color = d3.scale.ordinal()
        	.domain(["disease or phenotype associations", "transcriptomics", "structural or functional annotations", "physical interactions", "genomics", "proteomics"])
        	.range(["#aaa", "#aaa", "#aaa", "#aaa", "#aaa", "#aaa"])
        div = d3.select("#treemap").append('div').style("position", "relative"),
      	width = $parentEl.width(),
       	height = $parentEl.height();

	//var HEIGHT = d3.max(tree.children, function(d) { return d.size; });
	//var WIDTH = d3.max(tree.children, function(d) { return d.size; });
	
    var treemap = d3.layout.treemap()
        .size([width, height])
        .sticky(true)
        .value(function(d) { return d.size; });
    
    var tspan = div.datum(tree).selectAll(".node")
		.data(treemap.nodes)
       	.enter().append("div")
        .attr("class", "node")
        .call(position)
        .style("background-color", function(d) {
              return d.name == 'tree' ? '#fff' : color(d.group); })
          .append('div')
          .attr("title", function(d) {
        	  return d.size + " dataset(s)";
              })
              	.text(function(d) {
            	  	return d.children ? null : d.name;
           		});
   
         
        function position() {
          	this.style("left", function(d) { return d.x + "px"; })
          	.style("top", function(d) { return d.y + "px"; })
          	.style("width", function(d) { return Math.max(0, d.dx - 1) + "px"; })
          	.style("height", function(d) { return Math.max(0, d.dy - 1) + "px"; });
    }
});*/