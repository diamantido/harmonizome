<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<%@include file="navbarWithSearch.jsp" %>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container search-results-page">
					<div class="col-md-12 metadata">
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
										<span class="badge">
											<a href="search?q=${query}&t=dataset">Dataset</a>
										</span>
									</c:if>
									<c:if test="${fn:length(genes) != 0}">
										<span class="badge">
											<a href="search?q=${query}&t=gene">Gene</a>
										</span>
									</c:if>
									<c:if test="${fn:length(attributes) != 0}">
										<span class="badge">
											<a href="search?q=${query}&t=attribute">Gene Set</a>
										</span>
									</c:if>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
					<div class="col-md-12">
						<table class="table data-table">
							<thead>
								<tr>
									<th class="col-md-12">Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="dataset" items="${datasets}">
									<tr>
										<td class="col-md-10">
											<h3>
												<a href="dataset/${dataset.urlEncodedName}">${dataset.name}</a> <span class="note">Dataset</span>
											</h3>
											<div class="description">
												<p>
													From <a href="resource/${dataset.resource.urlEncodedName}">${dataset.resource.name}</a>
												</p>
												<p>${dataset.description} (${dataset.datasetGroup.name})</p>
											</div>
										</td>
									</tr>
								</c:forEach>
								<c:forEach var="gene" items="${genes}">
									<tr>
										<td class="col-md-12">
											<h3>
												<a href="gene/${gene.urlEncodedSymbol}">${gene.symbol}</a> <span class="note">Gene</span>
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
										<td class="col-md-12">
											<h3>
												<a href="gene/${attribute.urlEncodedNameFromDataset}">${attribute.nameFromDataset}</a> <span class="note">Gene Set</span>
											</h3>
											<div class="description">
												<p>${fn:replace(attribute.dataset.geneSetDescription, "{0}", attribute.nameFromDataset)}</p>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>