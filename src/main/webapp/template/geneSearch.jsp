<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
		<script src="script/geneSearch.js"></script>
		<script>
			<% @SuppressWarnings("unchecked") 
			List<String> genesArray = (List<String>) request.getAttribute("genes");
			String genes = "['" + StringUtils.join(genesArray, "', '") + "']";
			%>
			var genes = <%= genes %>;
		</script>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<h3>Search Genes</h3>
				<form>
					<input type="text">
					<button type="submit">Go</button>
				</form>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>