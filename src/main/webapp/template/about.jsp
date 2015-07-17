<%@ page import="java.util.Collections" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Resource" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.ResourceComparator" %>

<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
        <%@include file="commonIncludes.html" %>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/css/about.css">
        <style>
		form {
		  	position: absolute;
		  	right: 10px;
		  	top: 10px;
		}
		.node {
		    border: solid 1px white;
		    line-height: 0.95;
		    overflow: hidden;
		    position: absolute;
		}
		.node div {
		   	padding: 6px 4%;
		}
		#treemap {
			width: 100%;
			height: 1000px;
			color: white;
		}
        </style>
        <script>
        $(function() {
	
        	var tree = {
        		name: 'tree',
        		children: <%= request.getAttribute("resources") %>
        	};

        	var $parentEl = $('#treemap'),
        		MARGIN = 40,
	            color = d3.scale.ordinal()
	            	.domain(["disease or phenotype associations", "transcriptomics", "structural or functional annotations", "physical interactions", "genomics", "proteomics"])
	            	.range(["#2AB69D", "#E65848", "#FDC536", "#2F77A3", "#8BE3E8", "#EB7F00"])
	            div = d3.select("#treemap").append('div').style("position", "relative"),
	          	width = $parentEl.width() - MARGIN,
		       	height = $parentEl.height() - MARGIN;

	        var treemap = d3.layout.treemap()
	            .size([width, height])
	            .sticky(true)
	            .value(function(d) { return d.size; });
	        
	        var node = div.datum(tree).selectAll(".node")
	              .data(treemap.nodes)
	            .enter().append("div")
	              .attr("class", "node")
	              .call(position)
	              .style("background-color", function(d) {
	                  return d.name == 'tree' ? '#fff' : color(d.group); })
	              .append('div')
	              .style("font-size", function(d) {
	                  return "12px"; 
	              })
	              .attr("title", function(d) {
	            	  return d.size + " datasets";
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
		});
        </script>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container">
					<h1>About</h1>
					<p>Thanks to technological advances in genomics, transcriptomics, proteomics, metabolomics, and related fields, projects that generate a large number of measurements of the properties of cells, tissues, model organisms, and patients are becoming commonplace in biomedical research. In addition, curation projects are making great progress mining biomedical literature to extract and aggregate decades worth of research findings into online databases. Such projects are generating a wealth of information that potentially can guide research toward novel biomedical discoveries and advances in healthcare. To facilitate access to and learning from biomedical Big Data, we created the Harmonizome: a collection of information about genes and proteins from over 100 datasets provided by over 50 online resources.</p>
					<p>To create the Harmonizome, we distilled information from original datasets into attribute tables that define significant associations between genes and attributes, where attributes could be genes, proteins, cell lines, tissues, experimental perturbations, diseases, phenotypes, or drugs, depending on the dataset. Gene and protein identifiers were mapped to NCBI Entrez Gene Symbols and attributes were mapped to appropriate ontologies. We also computed gene-gene and attribute-attribute similarity networks from the attribute tables. These attribute tables and similarity networks can be integrated to perform many types of computational analyses for knowledge discovery and hypothesis generation.</p>
					<div id="treemap"></div>
					<%--<% @SuppressWarnings("unchecked")
						List<Resource> resources = (List<Resource>) request.getAttribute("resources");
						//Collections.sort(resources, new ResourceComparator());
						for (Resource r : resources) { 
							String description = r.getShortDescription();
						%>
							<div class="col-sm-3">
								<div class="resource">
									<h4>
										<a href="<%= r.getUrl() %>" target="_blank"><%= r.getName() %></a>
										<span class="count"><%= r.getDatasets().size() %> dataset(s)</span>
									</h4>
									<div class="labels">
										<% Set<String> categories = new TreeSet<String>();
										for (Dataset ds : r.getDatasets()) {
											String name = ds.getDatasetGroup().getName();
											categories.add(name);
										}
										for (String cat : categories) { %>
											<span class="badge <%= StringUtils.join(StringUtils.split(cat), "-") %>">
												<%= StringUtils.capitalize(cat) %>
											</span>
										<% } %>
									</div>
								</div>
							</div>
						<% } %>--%>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>