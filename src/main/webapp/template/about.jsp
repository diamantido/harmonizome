<%@ page import="java.util.Collections" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Resource" %>

<%
@SuppressWarnings("unchecked")
Map<String, Long> stats = (Map<String, Long>) request.getAttribute("stats");
%>

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
			<div class="content container-full">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>About</h1>
							<p>Thanks to technological advances in genomics, transcriptomics, proteomics, metabolomics, and related fields, projects that generate a large number of measurements of the properties of cells, tissues, model organisms, and patients are becoming commonplace in biomedical research. In addition, curation projects are making great progress mining biomedical literature to extract and aggregate decades worth of research findings into online databases. Such projects are generating a wealth of information that potentially can guide research toward novel biomedical discoveries and advances in healthcare. To facilitate access to and learning from biomedical Big Data, we created the Harmonizome: a collection of information about genes and proteins from over 100 datasets provided by over 50 online resources.</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<h3>Summary</h3>
							<ul class="list-unstyled">
							<% for (Map.Entry<String, Long> stat : stats.entrySet()) { %>
								<li><strong><%= stat.getValue() %></strong> <%= stat.getKey() %></li>
							<% } %>
							</ul>
						</div>
						<div class="col-md-10">
							<h3>Methods</h3>
							<p>To create the Harmonizome, we distilled information from original datasets into attribute tables that define significant associations between genes and attributes, where attributes could be genes, proteins, cell lines, tissues, experimental perturbations, diseases, phenotypes, or drugs, depending on the dataset. Gene and protein identifiers were mapped to NCBI Entrez Gene Symbols and attributes were mapped to appropriate ontologies. We also computed gene-gene and attribute-attribute similarity networks from the attribute tables. These attribute tables and similarity networks can be integrated to perform many types of computational analyses for knowledge discovery and hypothesis generation.</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<canvas id="chart" class="img-responsive"></canvas>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>