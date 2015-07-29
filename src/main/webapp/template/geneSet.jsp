<!DOCTYPE HTML>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang3.tuple.Pair" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Gene" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%
@SuppressWarnings("unchecked")
Pair<List<Gene>, List<Gene>> genesByAttribute = (Pair<List<Gene>, List<Gene>>) request.getAttribute("genesByAttribute");
Dataset dataset = (Dataset) request.getAttribute("dataset");
Attribute attribute = (Attribute) request.getAttribute("attribute");
%>

<html>
	<head>
		<%@include file="globalIncludes.html" %>
		<%@include file="commonIncludes.html" %>
		<script>
			<%
			@SuppressWarnings("unchecked") 
			List<String> upGenesArray = (List<String>) request.getAttribute("upGenes");
			String upGenesStr = "['" + StringUtils.join(upGenesArray, "', '") + "']";
			@SuppressWarnings("unchecked") 
			List<String> downGenesArray = (List<String>) request.getAttribute("downGenes");
			String downGenesStr = "['" + StringUtils.join(downGenesArray, "', '") + "']";
			%>
			
			$(function() {
				HMZ({
					ATTRIBUTE_NAME: "<%= request.getAttribute("attributeName") %>",
					DATASET_NAME: "<%= request.getAttribute("datasetName") %>",
					UP_GENES: <%= upGenesStr %>,
					DOWN_GENES: <%= downGenesStr %>
				});
			});
		</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper attribute-page">
			<div class="content container">
				<h1><%= StringUtils.capitalize(attribute.getNameFromDataset()) %> <span class="note">Gene Set</span>
				</h1>
				<section>
					<table class="table">
						<tr>
							<td class="col-sm-3">Source</td>
							<td class="col-sm-9">
								<a href="<%= Constant.DATASET_URL %>/<%= URLCodec.encode(dataset.getName()) %>"><%= dataset.getName() %></a>
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">Category</td>
							<td class="col-sm-9"><%= attribute.getAttributeType().getName() %></td>
						</tr>
						<tr>
							<td class="col-sm-3">Description</td>
							<% String description = "";
							if (attribute.getDescriptionFromNamingAuthority() != null) {
								description = attribute.getDescriptionFromNamingAuthority();
							} %>
							<td class="col-sm-9"><%= description %>
								(<a href="naming_authority/<%= URLCodec.encode(attribute.getNamingAuthority().getName()) %>"><%= attribute.getNameFromNamingAuthority() %></a>
								:
								<a href="<%= attribute.getUrlFromNamingAuthority() %>" target="_blank"><%= attribute.getIdFromNamingAuthority() %></a>)
							</td>
						</tr>
						<% if (attribute.getUrlFromDataset() != null) { %>
							<tr>
								<td class="col-sm-3">External Link</td>
								<td class="col-sm-9">
									<a href="<%= attribute.getUrlFromDataset() %>" target="_blank"><%= attribute.getUrlFromDataset() %></a>
								</td>
							</tr>
						<% } %>
						<tr>
							<td class="col-sm-3">Synonyms</td>
							<td class="col-sm-9"></td>
						</tr>
					</table>
				</section>
				<section>
					<h2>Downloads &amp; Tools</h2>
					<div class="tools">
						<button class="btn btn-default glyphicon glyphicon-file" data-toggle="tooltip" data-placement="right" title="Download the gene set as a plain text, newline separated list of genes."></button>
						<button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access the gene set from the API."></button>
						<button class="btn btn-default enrichr" data-toggle="tooltip" data-placement="right" title="Perform enrichment analysis against over 70 gene set libraries with Enrichr, a popular gene set enrichment analysis tool.">
							<img src="image/tool/enrichr-bw.png">
						</button>
					</div>
				</section>
				<section class="list">
					<%
						int numGenes = genesByAttribute.getLeft().size() + genesByAttribute.getRight().size();
						String desciption = StringUtils.capitalize(dataset.getGeneSetDescription());
						desciption = desciption.replace("{0}", attribute.getNameFromDataset());
						desciption = numGenes + " " + desciption;
					%>
					<h2><%= desciption %></h2>
					<div>
					<% Iterator<Gene> posIter = genesByAttribute.getLeft().iterator();
					if (posIter.hasNext()) { %>
						<div><strong><%= StringUtils.capitalize(dataset.getPositiveAssociation()) %></strong></div>
						<% while (posIter.hasNext()) {
							Gene gene = posIter.next();
							String symbol = gene.getSymbol();
						%>
							<a href="gene/<%= URLCodec.encode(symbol) %>"><%= symbol %></a><% if (posIter.hasNext()) { %>, <% } %>
						<% }
					} %>
					</div>
					<% Iterator<Gene> negIter = genesByAttribute.getRight().iterator();
					if (negIter.hasNext()) { %>
					<div class="last">
						<div><strong><%= StringUtils.capitalize(dataset.getNegativeAssociation()) %></strong></div>
						<% while (negIter.hasNext()) {
							Gene gene = negIter.next();
							String symbol = gene.getSymbol();
						%>
							<a href="gene/<%= URLCodec.encode(symbol) %>"><%= symbol %></a><% if (negIter.hasNext()) { %>, <% } %>
						<% } %>
					</div>
					<% } %>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>