<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h1>${resource.name} <span class="note">Resource</span></h1>
				<table class="table">
					<tr>
						<td class="col-md-2">Description</td>
						<td class="col-md-10">${resource.longDescription}</td>
					</tr>
					<tr>
						<td class="col-md-2">Datasets</td>
						<td class="col-md-10">
							<ul>
								<c:forEach var="dataset" items="${resource.datasets}">
									<li>
										<a href="dataset/${dataset.urlEncodedValue}">${dataset.nameWithoutResource}</a>
									</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
					<c:if test="${fn:length(publications) > 0}">
						<tr>
							<td class="col-md-2">Citation(s)</td>
							<td class="col-md-10">
								<ul>
									<c:forEach var="publication" items="${publications}">
										<li>
											<a href="${publication.pubmedUrl}" target="">${publication.longCitation}</a>
										</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resource.acronym}">
						<tr>
							<td class="col-md-2">Acronym</td>
							<td class="col-md-10">${resource.acronym}</td>
						</tr>
					</c:if>
					<c:if test="${not empty resource.url}">
						<tr>
							<td class="col-md-2">External Link</td>
							<td class="col-md-10">
								<a href="${resource.url}" target="_blank">${resource.url}</a>
							</td>
						</tr>
					</c:if>
					<tr>
						<td>Gene Sets</td>
						<td><c:out value="${resource.numGeneSets}"/></td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>