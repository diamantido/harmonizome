<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet" %>
<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper gene-set-page">
			<div class="content container">
				<h1 class="capitalize">${attribute.nameFromDataset} <span class="note">Gene Set</span></h1>
				<section>
					<table class="table">
						<tr>
							<td class="col-md-2">Source</td>
							<td class="col-md-10 initial">
								<a href="${dataset.endpoint}/${dataset.urlEncodedName}">${dataset.name}</a>
							</td>
						</tr>
						<tr>
							<td class="col-md-2">Category</td>
							<td class="col-md-10 initial">${attribute.attributeType.name}</td>
						</tr>
						<c:if test="${attribute.descriptionFromNamingAuthority != null}">
							<tr>
								<td class="col-md-2">Description</td>
								<td class="col-md-10 initial">${attribute.descriptionFromNamingAuthority} (<a href="${attribute.namingAuthority.endpoint}/${attribute.namingAuthority.urlEncodedName}">${attribute.namingAuthority.name}</a> : <a href="${attribute.urlFromNamingAuthority}" target="_blank">${attribute.idFromNamingAuthority}</a>)
								</td>
							</tr>
						</c:if>
						<c:if test="${attribute.urlFromDataset != null}">
							<tr>
								<td class="col-md-2">External Link</td>
								<td class="col-md-10">
									<a href="${attribute.urlFromDataset}" target="_blank">${attribute.urlFromDataset}</a>
								</td>
							</tr>
						</c:if>
						<tr>
							<td class="col-md-2">Downloads &amp; Tools</td>
							<td class="col-md-10">
								<div class="downloads-tools">
									<a href="${Constant.API_URL}/${GeneSet.ENDPOINT}/${attribute.urlEncodedNameFromDataset}/${dataset.urlEncodedName}" target="_blank">
										<button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access the gene set from the API."></button>
									</a>
									<!-- It doesn't make sense to pipe to Enrichr without enough genes -->
									<c:if test="${fn:length(genesByAttribute.right) <= 1}">
										<button class="btn btn-default enrichr" data-toggle="tooltip" data-placement="right" title="Perform enrichment analysis against over 70 gene set libraries with Enrichr, a popular gene set enrichment analysis tool.">
											<img src="image/tool/enrichr.png">
										</button>
									</c:if>
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
					<c:if test="${genesByAttribute.right != null}">
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