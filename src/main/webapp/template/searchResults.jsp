<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    </head>
    <body>
		<%@include file="navbarWithSearch.jsp" %>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container search-results-page">
					<div class="metadata">
						<p class="instruction">${summary}</p>
						<p>
							Filter By:
							<c:choose>
								<c:when test="${isFilteredPage}">
									<span class="badge">
										<a href="search?q=${query}">Clear Filter</a>
									</span>
								</c:when>
								<c:otherwise>
									<c:if test="${fn:length(datasets) != 0}">
										<span class="badge dataset">
											<a href="${Constant.SEARCH_URL}?q=${query}&t=dataset">Dataset</a>
										</span>
									</c:if>
									<c:if test="${fn:length(genes) != 0}">
										<span class="badge gene">
											<a href="${Constant.SEARCH_URL}?q=${query}&t=gene">Gene</a>
										</span>
									</c:if>
									<c:if test="${fn:length(attributes) != 0}">
										<span class="badge attribute">
											<a href="${Constant.SEARCH_URL}?q=${query}&t=attribute">Gene Set</a>
										</span>
									</c:if>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
					<table class="table data-table">
						<thead>
							<tr>
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dataset" items="${datasets}">
								<tr>
									<td>
										<h3>
											<a href="${dataset.endpoint}/${dataset.urlEncodedName}">${dataset.name}</a> <span class="badge dataset">Dataset</span>
										</h3>
										<div class="description">
											<p>
												From <a href="${dataset.resource.endpoint}/${dataset.resource.urlEncodedName}">${dataset.resource.name}</a>
											</p>
											<p>${dataset.description} (${dataset.datasetGroup.name})</p>
										</div>
									</td>
								</tr>
							</c:forEach>
							<c:forEach var="gene" items="${genes}">
								<tr>
									<td>
										<h3>
											<a href="${gene.endpoint}/${gene.urlEncodedSymbol}">${gene.symbol}</a> <span class="badge gene">Gene</span>
										</h3>
										<div class="description">
											<c:if test="${gene.name != null}">
												<p><em>${gene.name}</em></p>
											</c:if>
											<c:if test="${gene.description != null}">
												<p>${gene.description}</p>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
							<c:forEach var="attribute" items="${attributes}">
								<tr>
									<td>
										<h3>
											<a href="${attribute.endpoint}/${attribute.urlEncodedNameFromDataset}/${attribute.dataset.urlEncodedName}">${attribute.nameFromDataset}</a> <span class="badge attribute">Gene Set</span>
										</h3>
										<div class="description">
											<p><em>From <a href="${attribute.dataset.endpoint}/${attribute.dataset.urlEncodedName}">${attribute.dataset.name}</a></em></p>
											<p>${fn:replace(attribute.dataset.geneSetDescription, "{0}", attribute.nameFromDataset)}</p>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>