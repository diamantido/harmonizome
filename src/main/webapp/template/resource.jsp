<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<td class="col-sm-3">Description</td>
						<td class="col-sm-9">${resource.longDescription}</td>
					</tr>
					<tr>
						<td class="col-sm-3">Datasets</td>
						<td class="col-sm-9">
							<ul>
								<c:forEach var="dataset" items="${resource.datasets}">
									<li>
										<a href="dataset/${dataset.urlEncodedName}">${dataset.nameWithoutResource}</a>
									</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
					<tr>
						<td class="col-sm-3">Citation(s)</td>
						<td class="col-sm-9">
							<ul>
								<c:forEach var="publication" items="${publications}">
									<li>
										<a href="${publication.pubmedUrl}">${publication.longCitation}</a>
									</li>
								</c:forEach>
							</ul>
						</td>					
					</tr>
					<c:if test="${resource.acronym != NULL}">
						<tr>
							<td class="col-sm-3">Acronym</td>
							<td class="col-sm-9">${resource.acronym}</td>
						</tr>
					</c:if>
					<tr>
						<td class="col-sm-3">External Link</td>
						<td class="col-sm-9">
							<a href="${resource.url}" target="_blank">${resource.url}</a>
						</td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>