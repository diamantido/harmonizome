<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
		<title>Query Not Found</title>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
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
					<%@include file="searchBar.html" %>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>