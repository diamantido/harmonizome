<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html" %>
		<base href="/Harmonizome/" />
		<link rel="stylesheet" type="text/css" href="style/css/dataset-page.css">
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<% if (request.getAttribute("useDataset") == "true") { %>
					<%@include file="page_dataset.jsp" %>
				<% } %>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>