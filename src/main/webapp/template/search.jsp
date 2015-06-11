<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html"%>
		<script src="scripts/search.js"></script>
		<script src="scripts/Select.js"></script>
		<script>
			var genes = ${genes};
		</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<div class="search">
					<h3>Search</h3>
					<div class="row"></div>
					<button class="go">Go</button>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>