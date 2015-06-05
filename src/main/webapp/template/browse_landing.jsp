<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.URLEncoder" %>
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
						<% Dataset dataset = datasets.get(i); %>
						<tr>
							<td><%= dataset.getName() %></td>
							<td><%= dataset.getResource().getName() %></td>
							<td><%= dataset.getDescription() %></td>
							<td><%= dataset.getDatasetType().getName() %></td>
							<td><%= dataset.getAttributeType().getName() %></td>
							<td>
								<a href="dataset/<%= URLEncoder.encode(dataset.getName()) %>">
				                	<span class="glyphicon glyphicon-file" aria-hidden="true"></span>
				            	</a>
				            </td>
						</tr>
					<% } %>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>