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
		<%@include file="navbarWithSearch.jsp"%>
		
		<c:set var="dataset" value="${geneSet.dataset}"/>
		<c:set var="attribute" value="${geneSet.attribute}"/>
		
		<div class="wrapper gene-set-page">
			<div class="content container">
				<h1 class="capitalize">${geneSet.nameFromDataset} <span class="note gene-set">Gene Set</span></h1>
				<section>
					<table class="table">
						<tr>
							<td class="col-md-2">Source</td>
							<td class="col-md-10">
								<a href="${dataset.endpoint}/${dataset.urlEncodedValue}">${dataset.name}</a>
							</td>
						</tr>
						<tr>
							<td class="col-md-2">Type</td>
							<td class="col-md-10 initial">${geneSet.attributeType.name}</td>
						</tr>
						<c:if test="${attribute.descriptionFromNamingAuthority != null}">
							<tr>
								<td class="col-md-2">Description</td>
								<td class="col-md-10 initial">${attribute.descriptionFromNamingAuthority} (<a href="${attribute.namingAuthority.endpoint}/${attribute.namingAuthority.urlEncodedValue}">${attribute.namingAuthority.name}</a><c:if test="${not empty attribute.urlFromNamingAuthority}">, <a href="${attribute.urlFromNamingAuthority}" target="_blank">${attribute.idFromNamingAuthority}</a></c:if>)
								</td>
							</tr>
						</c:if>
						<c:if test="${geneSet.urlFromDataset != null}">
							<tr>
								<td class="col-md-2">External Link</td>
								<td class="col-md-10">
									<a href="${geneSet.urlFromDataset}" target="_blank">${geneSet.urlFromDataset}</a>
								</td>
							</tr>
						</c:if>
						<tr>
							<td class="col-md-2">Downloads &amp; Tools</td>
							<td class="col-md-10">
								<noscript>
									<p class="instruction">The Enrichr link requires JavaScript.</p>
								</noscript>
								<div class="downloads-tools">
									<a class="btn btn-default glyphicon glyphicon-cloud-download"
										data-toggle="tooltip"
										data-placement="right"
										title="Access the gene set from the API."
										href="${Constant.API_URL}/${GeneSet.ENDPOINT}/${geneSet.urlEncodedValue}"
										target="_blank">
									</a>

									<!-- It doesn't make sense to pipe to Enrichr without enough genes -->
									<c:if test="${fn:length(genesByAttribute.right) <= 1}">
										<button class="btn btn-default enrichr"
											data-toggle="tooltip"
											data-placement="right"
											title="Perform enrichment analysis against over 70 gene set libraries with Enrichr, a popular gene set enrichment analysis tool."
											><img src="image/tool/enrichr.png">
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
							<h4 class="initial">${dataset.positiveAssociation}</h4>
						</c:if>
						<table class="table data-table genes">
							<thead>
								<tr>
									<th class="col-md-2">Gene</th>
									<th class="col-md-10">Description</th>
								</tr>
							</thead>
							<c:forEach var="gene" items="${genesByAttribute.left}">
								<tr>
									<td class="col-md-2">
										<a href="${gene.endpoint}/${gene.urlEncodedValue}">
											<c:out value="${gene.symbol}"/>
										</a>
									</td>
									<td class="col-md-10">
										<c:out value="${gene.description}"/>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<c:if test="${fn:length(genesByAttribute.right) > 0}">
						<div class="last">
							<c:if test="${dataset.negativeAssociation != null}">
								<h4 class="initial">${dataset.negativeAssociation}</h4>
							</c:if>
							<table class="table data-table genes">
								<thead>
									<tr>
										<th>Gene</th>
										<th>Description</th>
									</tr>
								</thead>
								<c:forEach var="gene" items="${genesByAttribute.right}">
									<tr>
										<td class="col-md-2">
											<a href="${gene.endpoint}/${gene.urlEncodedValue}">
												<c:out value="${gene.symbol}"/>
											</a>
										</td>
										<td class="col-md-10">
											<c:out value="${gene.description}"/>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>