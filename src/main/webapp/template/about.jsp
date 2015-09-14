<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
        <script src="script/about.js"></script>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full about-page">
				<div class="container">
					<div class="row">
						<h1 class="col-md-12">About</h1>
						<div class="col-md-6"><h4>Intro</h4><p>${introText}</p></div>
						<div class="col-md-6"><h4>Methods</h4><p>To create the Harmonizome, we distilled information from original datasets into attribute tables that define significant associations between genes and attributes, where attributes could be genes, proteins, cell lines, tissues, experimental perturbations, diseases, phenotypes, or drugs, depending on the dataset. Gene and protein identifiers were mapped to NCBI Entrez Gene Symbols and attributes were mapped to appropriate ontologies. We also computed gene-gene and attribute-attribute similarity networks from the attribute tables. These attribute tables and similarity networks can be integrated to perform many types of computational analyses for knowledge discovery and hypothesis generation.</p></div>
					</div>
					<div class="row stats">
						<h2 class="col-md-12">Stats</h2>
						<div class="col-md-4">
							<h4>Content Summary</h4>
							<ul class="list-unstyled">
								<li><strong><c:out value="${stats.getNumFeatures()}"></c:out> associations</strong> between</li>
								<li><strong><c:out value="${stats.getNumAttributes()}"></c:out> attributes</strong> and</li>
								<li><strong><c:out value="${stats.getNumGenes()}"></c:out> genes</strong> from</li>
								<li><strong><c:out value="${stats.getNumDatasets()}"></c:out> datasets provided</strong> by</li>
								<li><strong><c:out value="${stats.getNumResources()}"></c:out> resources</strong></li>
							</ul>
						</div>
						<div class="col-md-4">
							<h4>Datasets by Category</h4>
							<div>
								<canvas id="dataset-pie-chart"></canvas>
							</div>
						</div>
						<div class="col-md-4">
							<h4>Attributes by Category</h4>
							<div>
								<canvas id="attribute-pie-chart"></canvas>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h4>Number of Gene Sets by Resource</h4>
							<p class="instruction">The the y-axis is on a log base 10 scale. Hover over the bars to see the actual counts.</p>
							<!--
								ChartJS scales the canvas to the width of the
								container. Since Bootstrap containers have
								padding, the canvas is sized slightly larger
								than the edges of the container. Just wrap the
								canvas in a div, so that the div sizes to the
								width of col-md-12, and the canvas scales to
								the width of the div.
							 -->
							<div>
								<canvas id="bar-chart"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>