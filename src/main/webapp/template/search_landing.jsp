<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html"%>
		<script src="scripts/search_landing.js"></script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h3>Search</h3>
				<table class="table">
					<tr>
						<td class="col-md-2">Genes</td>
						<td class="col-md-6"><input type="text"></input></td>
						<td class="col-md-4"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></td>
					</tr>
				</table>
				<!--<table class="table">
					<tr>
						<td class="col-md-2">Proteins</td>
						<td class="col-md-6"><input type="text"></input></td>
						<td class="col-md-4"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></td>
					</tr>
				</table>-->
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>