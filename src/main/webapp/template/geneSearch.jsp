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
				
				<!--<form>
					<input type="text">
					<button type="submit">Go</button>
				</form> -->
				<div class="row">
					<div class="col-md-12">
						<h1>Search Genes</h1>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Please enter a gene symbol...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">Go!</button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>