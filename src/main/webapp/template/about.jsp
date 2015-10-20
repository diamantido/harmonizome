<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="About">

    <script src="lib/highcharts/highcharts.js"></script>
    <script src="script/about.js"></script>

	<div class="about-page">
		<div class="row">
			<div class="col-md-12">
				<h1>About</h1>
				<p>${introText}</p>
			</div>
		</div>
		<div class="row stats">
			<div class="col-md-12">
				<h2>Stats</h2>
			</div>
			<div class="col-md-4">
				<ul class="list-unstyled">
					<li><strong><c:out value="${stats.getNumFeatures()}"/> associations</strong> between</li>
					<li><strong><c:out value="${stats.getNumAttributes()}"/> attributes</strong> and</li>
					<li><strong><c:out value="${stats.getNumGenes()}"/> genes</strong> from</li>
					<li><strong><c:out value="${stats.getNumDatasets()}"/> datasets provided</strong> by</li>
					<li><strong><c:out value="${stats.getNumResources()}"/> resources</strong></li>
				</ul>
			</div>
			<div class="col-md-4">
				<div id="dataset-pie-chart"></div>
			</div>
			<div class="col-md-4">
				<div id="attribute-pie-chart"></div>
			</div>
		</div>
		<div id="resources" class="row">
			<div class="col-md-12">
				<h2>Resources</h2>
				<p>To create the Harmonizome, we distilled information from original datasets into attribute tables that define significant associations between genes and attributes, where attributes could be genes, proteins, cell lines, tissues, experimental perturbations, diseases, phenotypes, or drugs, depending on the dataset. Gene and protein identifiers were mapped to NCBI Entrez Gene Symbols and attributes were mapped to appropriate ontologies. We also computed gene-gene and attribute-attribute similarity networks from the attribute tables. These attribute tables and similarity networks can be integrated to perform many types of computational analyses for knowledge discovery and hypothesis generation.</p>
				<div id="bar-chart"></div>
			</div>
		</div>
	</div>
</t:wrapper>