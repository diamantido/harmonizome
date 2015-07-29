<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Gene" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Ellipsizer" %>
<%
@SuppressWarnings("unchecked")
Set<Dataset> datasets = (Set<Dataset>) request.getAttribute("datasets");
@SuppressWarnings("unchecked")
Set<Gene> genes = (Set<Gene>) request.getAttribute("genes");
@SuppressWarnings("unchecked")
Set<Attribute> attributes = (Set<Attribute>) request.getAttribute("attributes");

int MAX_DESCRIPTION_LENGTH = 200;
%>

<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
        <%@include file="commonIncludes.html" %>
        <script>
			$(function() {
				HMZ();
			});
		</script>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container search-results-page">
					<div class="col-md-12 metadata">
						<h1>Search results</h1>
						<p class="instruction">
							<%= datasets.size() %> datasets, <%= genes.size() %> genes, <%= attributes.size() %> gene sets
						</p>
						<p>
							Filter your results:
							<span class="badge">
								<a href="search?q=${query}&t=dataset">datasets</a>
							</span>
							<span class="badge">
								<a href="search?q=${query}&t=gene">genes</a>
							</span>
							<span class="badge">
								<a href="search?q=${query}&t=attribute">gene sets</a>
							</span>
						</p>
					</div>
					<div class="col-md-12">
						<table class="table data-table">
							<thead>
								<tr>
									<th class="col-md-2">Search Result Type</th>
									<th class="col-md-10">Name</th>
								</tr>
							</thead>
							<tbody>
								<%
								int i = 0;
								for (Dataset dataset : datasets) { 
									String datasetName = dataset.getNameWithoutResource();
									String resourceName = dataset.getResource().getName();
								%>
								<tr>
									<td class="col-md-2">
										<strong><%= i == 0 ? "Datasets" : "" %></strong>
									</td>
									<td class="col-md-10">
										<h3>
											<a href="dataset/<%= URLCodec.encode(dataset.getName()) %>"><%= datasetName %></a> <span class="note">Dataset</span>
										</h3>
										<div class="description">
											<p>
												From <a href="resource/<%= URLCodec.encode(resourceName) %>"><%= resourceName %></a>
											</p>
											<p>
												<%= StringUtils.capitalize(Ellipsizer.trim(dataset.getDescription(), MAX_DESCRIPTION_LENGTH)) %> (<%= dataset.getDatasetGroup().getName() %>)
											</p>
										</div>
									</td>
								</tr>
								<% 
									i++;
								}
								
								int j = 0;
								for (Gene gene : genes) {
									String symbol = gene.getSymbol();
								%>
								<tr>
									<td class="col-md-2">
										<strong><%= j == 0 ? "Genes" : "" %></strong>
									</td>
									<td class="col-md-10">
										<h3>
											<a href="gene/<%= URLCodec.encode(symbol) %>"><%= symbol %></a> <span class="note">Gene</span>
										</h3>
										<% if (gene.getIdgFamily() != null) { %>
											From <%= gene.getIdgFamily().getName() %> family
										<% }
										if (gene.getDescription() != null) { %>
											<p class="description"><%= Ellipsizer.trim(gene.getDescription(), MAX_DESCRIPTION_LENGTH) %></p>
										<% } %>
									</td>
								</tr>
								<% 
									j++;
								}
								
								int k = 0;
								for (Attribute attribute : attributes) {
									String attributeName = attribute.getNameFromDataset();
									String datasetName = attribute.getDataset().getName();
									String geneSetUrl = URLCodec.encodeGeneSet(attributeName, datasetName);
								%>
								<tr>
									<td class="col-md-2">
										<strong><%= k == 0 ? "Gene Sets" : "" %></strong>
									</td>
									<td class="col-md-10">
										<h3>
											<a href="gene_set/<%= geneSetUrl %>">
												<%= StringUtils.capitalize(attributeName) %>
											</a> <span class="note">Gene Set</span>
										</h3>
										<div class="description">
											<p>
												From <a href="dataset/<%= URLCodec.encode(attribute.getDataset().getName()) %>"><%= attribute.getDataset().getName() %></a>
											</p>
											<p>
												<%= StringUtils.capitalize(Ellipsizer.trim(attribute.getDescriptionFromNamingAuthority(), MAX_DESCRIPTION_LENGTH)) %>
												<span class="">(<%= attribute.getAttributeType().getName() %>)</span>
											</p>
										</div>
									</td>
								</tr>
								<% 
									k++;
								} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>