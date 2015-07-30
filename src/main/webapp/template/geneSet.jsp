<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
								<a href="${dataset.endpoint}/${dataset.urlEncodedName}">${dataset.name}</a>
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">Category</td>
							<td class="col-sm-9">${attribute.attributeType.name}</td>
						</tr>
						<c:if test="${attribute.descriptionFromNamingAuthority != null}">
							<tr>
								<td class="col-sm-3">Description</td>
								<td>${attribute.descriptionFromNamingAuthority} (<a href="${attribute.namingAuthority.endpoint}/${attribute.namingAuthority.urlEncodedName}">${attribute.namingAuthority.name}</a> : <a href="${attribute.urlFromNamingAuthority}" target="_blank">${attribute.idFromNamingAuthority}</a>)
								</td>
							</tr>
						</c:if>
						<c:if test="${attribute.urlFromDataset != null}">
							<tr>
								<td class="col-sm-3">External Link</td>
								<td class="col-sm-9">
									<a href="${attribute.urlFromDataset}" target="_blank">${attribute.urlFromDataset}</a>
								</td>
							</tr>
						</c:if>
						<!-- TODO: This should blank if no synonyms exist. -->
						<c:if test="true">
							<tr>
								<td class="col-sm-3">Synonyms</td>
								<td class="col-sm-9"></td>
							</tr>
						</c:if>
						<tr>
							<td class="col-sm-3">Downloads &amp; Tools</td>
							<td class="col-sm-9">
								<div class="tools">
									<button class="btn btn-default glyphicon glyphicon-file" data-toggle="tooltip" data-placement="right" title="Download the gene set as a plain text, newline separated list of genes."></button>
									<button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access the gene set from the API."></button>
									<button class="btn btn-default enrichr" data-toggle="tooltip" data-placement="right" title="Perform enrichment analysis against over 70 gene set libraries with Enrichr, a popular gene set enrichment analysis tool.">
										<img src="image/tool/enrichr-bw.png">
									</button>
								</div>
							</td>
						</tr>
					</table>
				</section>
				<section class="list">
					<h2>Genes</h2>
					<p class="instruction">${geneSetDescription}</p>
					<div>
						<c:if test="${dataset.positiveAssociation != null}">
							<p><strong>${dataset.positiveAssociation}</strong></p>
						</c:if>
						<c:forEach var="gene" items="${genesByAttribute.left}" varStatus="loop">
							<a href="${gene.endpoint}/${gene.urlEncodedSymbol}">${gene.symbol}</a><c:if test="${!loop.last}">, </c:if>
						</c:forEach>
					</div>
					<c:if test="${fn:length(genesByAttribute.right) != 0}">
						<div class="last">
							<c:if test="${dataset.negativeAssociation != null}">
								<p><strong>${dataset.negativeAssociation}</strong></p>
							</c:if>
							<c:forEach var="gene" items="${genesByAttribute.right}" varStatus="loop">
								<a href="${gene.endpoint}/${gene.urlEncodedSymbol}">${gene.symbol}</a><c:if test="${!loop.last}">, </c:if>
							</c:forEach>
						</div>
					</c:if>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>