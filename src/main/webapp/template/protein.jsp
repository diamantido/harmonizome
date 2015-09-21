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
				<h1><c:out value="${protein.symbol}"/> <span class="note">Protein</span></h1>
				<table class="table">
					<tr>
						<td class="col-md-2">Name</td>
						<td class="col-md-10">
							<c:out value="${protein.name}"/>
						</td>
					</tr>
					<tr>
						<td class="col-md-2">Description</td>
						<td class="col-md-10">
							<c:out value="${protein.description}"/>
						</td>
					</tr>
					<tr>
						<td class="col-md-2">UniProt ID</td>
						<td class="col-md-10">
							<a href="${protein.uniprotUrl}" target="_blank">
								<c:out value="${protein.uniprotId}"/>
							</a>
						</td>
					</tr>
					<tr>
						<td class="col-md-2">Gene</td>
						<td class="col-md-10">
							<a href="${protein.gene.endpoint}/${protein.gene.urlEncodedValue}" target="_blank">
								<c:out value="${protein.gene.symbol}"/>
							</a>
						</td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>