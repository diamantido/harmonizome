<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html" %>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<% if (request.getAttribute("useGene") == "true") { %>
					<%@include file="page_gene.jsp" %>
				<% } %>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>