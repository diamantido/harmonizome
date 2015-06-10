<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html"%>
		<script src="scripts/search_landing.js"></script>
		<script>
			var genes = ${genes};
		</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<%@include file="filter_engine.jsp"%>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>