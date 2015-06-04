<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<html>
	<head>
		<%@include file="common_includes.html"%>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h3>Datasets</h3>
				<table class="table">
					<% List<Dataset> datasets = (List<Dataset>) request.getAttribute("datasets");
					for (int i = 0; i < datasets.size(); i++) { %>
						<% Dataset ds = datasets.get(i); %>
						<tr>
							<td><%= ds.getName() %></td>
							<td><%= ds.getResource().getName() %></td>
							<td><%= ds.getDescription() %></td>
							<td><%= ds.getDatasetType().getName() %></td>
							<td><%= ds.getAttributeType().getName() %></td>
							<td>${numberOfAttributes}</td>
							<td>${numberOfGenes}</td>
						</tr>
					<% } %>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>