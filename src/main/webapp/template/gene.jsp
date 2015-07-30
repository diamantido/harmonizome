<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
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
			<div class="content container">
				<h1 class="initial">${gene.symbol} <span class="note">${note}</span></h1>
				<section>
					<table class="table">
						<tr>
							<td class="col-md-2">Families (from HGNC)</td>
							<td class="col-md-10">
								<c:forEach var="family" items="${gene.hgncRootFamilies}" varStatus="loop">
									<a href="${family.endpoint}/${family.urlEncodedName}">${family.name}</a><c:if test="${!loop.last}">, </c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="col-md-2 initial">Name</td>
							<td class="col-md-10">${gene.name}</td>
						</tr>
						<tr>
							<td class="col-md-2">Description (from NCBI)</td>
							<td class="col-md-10">${gene.description}</td>
						</tr>
						<tr>
							<td class="col-md-2">Synonyms</td>
							<td class="col-md-10">${synonyms}</td>
						</tr>
						<tr>
							<td class="col-md-2">Proteins</td>
							<td class="col-md-10">
								<c:forEach var="protein" items="${gene.proteins}" varStatus="loop">
									<a href="${protein.endpoint}/${protein.urlEncodedSymbol}">${protein.symbol}</a><c:if test="${!loop.last}">, </c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="col-md-2">NCBI Gene ID</td>
							<td class="col-md-10"><a href="${gene.ncbiEntrezGeneUrl}" target="_blank">${gene.ncbiEntrezGeneId}</a></td>
						</tr>
					</table>
				</section>
				<section>
					<h2>Knowledge</h2>
					<p class="instruction">Click the + buttons to view associations for ${symbol} from each dataset.</p>
					<p class="instruction">
						<c:out value="${allAssociationsSummary}"/>	
					</p>
					<table class="table entities-by-dataset genes">
						<thead>
							<tr>
								<th></th>
								<th>Dataset</th>
								<th>Summary</th>
								<th>Downloads</th>
							</tr>
						</thead>
						<c:forEach var="pair" items="${attributesByDataset}">
							<c:set var="dataset" value="${pair.left}"/>
							<c:set var="attributes" value="${pair.right}"/>
							<tr class="dataset-row ${dataset.cssClassName}">
								<td class="col-md-1" data-dataset-group="${dataset.cssClassName}">
									<button class="btn btn-default glyphicon glyphicon-plus cursor-pointer" aria-hidden="true"></button>
									<button class="btn btn-default glyphicon glyphicon-minus hidden cursor-pointer" aria-hidden="true"></button>
								</td>
								<td class="col-md-3">
									<a href="${dataset.endpoint}/${dataset.urlEncodedName}">
										<c:out value="${dataset.name}"/>
									</a>
								</td>
								<td class="col-md-6">
									<c:set var="attributeSetDescription" value="${fn:replace(dataset.attributeSetDescription, '{0}', gene.symbol)}"/>
									<c:set var="attributeCount" value="${fn:length(attributes.left) + fn:length(attributes.right)}"/>
									<c:out value="${attributeCount} ${attributeSetDescription}"/>
								</td>
								<td class="col-md-2">
								</td>
							</tr>
							<tr class="list attribute-list">
								<td class="col-md-1"></td>
								<td class="col-md-9" colspan="2">
									<c:set var="hasTwoAssociations" value="${fn:length(attributes.right) != 0}"/>
									<c:if test="${hasTwoAssociations}">
										<p>
											<strong>
												<c:out value="${dataset.positiveAssociation}"/>
											</strong>
										</p>
									</c:if>
									<c:set var="upAttributes" value="${attributes.left}"/>
									<c:forEach var="attribute" items="${upAttributes}" varStatus="loop">
										<a href="${attribute.endpoint}/${attribute.urlEncodedNameFromDataset}/${dataset.urlEncodedName}">
											<c:out value="${attribute.nameFromDataset}"/><c:if test="${!loop.last}">, </c:if>
										</a>
									</c:forEach>
									<c:if test="${hasTwoAssociations}">
										<div class="last">
											<p>
												<strong>
													<c:out value="${dataset.negativeAssociation}"/>
												</strong>
											</p>
											<c:set var="downAttributes" value="${attributes.right}"/>
											<c:forEach var="attribute" items="${downAttributes}" varStatus="loop">
												<a href="${attribute.endpoint}/${attribute.urlEncodedNameFromDataset}/${dataset.urlEncodedName}">
													<c:out value="${attribute.nameFromDataset}"/><c:if test="${!loop.last}">, </c:if>
												</a>
											</c:forEach>
										</div>
									</c:if>
								</td>
								<td class="col-md-2"></td>
							</tr>							
							
						</c:forEach>
					</table>
				</section>
			</div>
			<%@include file="footer.html" %>
		</div>
	</body>
</html>