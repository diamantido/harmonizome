<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="globalIncludes.html" %>
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h1><c:out value="${geneFamily.name}"/> <span class="note">Gene Family</span></h1>
				<table class="table">
					<tr>
						<td class="col-md-2">HGNC Family ID</td>
						<td class="col-md-10">
							<a href="${geneFamily.url}" target="_blank">
								<c:out value="${geneFamily.familyId}"/>
							</a>
						</td>
					</tr>
					<tr>
						<td class="col-md-2">Genes</td>
						<td class="col-md-10">
							<c:forEach var="gene" items="${geneFamily.genes}" varStatus="loop">
								<a href="${gene.endpoint}/${gene.urlEncodedSymbol}"><c:out value="${gene.symbol}"/></a><c:if test="${!loop.last}">, </c:if>
							</c:forEach>
						</td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>