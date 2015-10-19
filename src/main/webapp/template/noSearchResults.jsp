<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
		<title>Query Not Found</title>
	</head>
	<body>
		<%@include file="navbar.jsp"%>
		<div class="wrapper">
			<div class="content container no-search-results-page">
				<h1>No Search Results</h1>
				<p class="instruction">Your search "${query}" did not match any entities.</p>
				<div>
					<p>Suggestions:</p>
					<ul>
						<li>Check each keyword's spelling.</li>
						<li>Try different keywords.</li>
						<li>Try fewer or more general keywords.</li>
					</ul>
				</div>
				<div class="col-md-6 input-group">
					<jsp:include page="searchBar.jsp">
						<jsp:param value="on-no-results-page" name="location"/>
					</jsp:include>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>