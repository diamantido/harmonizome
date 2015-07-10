<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h3>${name}</h3>
				<table class="table">
					<tr>
						<td>Genes</td>
						<td>
							<% String[] genes = (String[]) request.getAttribute("genes");
							for (int i = 0; i < genes.length; i++) { %>
								<a href="gene/<%= genes[i] %>"><%= genes[i] %></a><%= i != genes.length-1 ? "," : "" %>
							<% } %>
						</td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>