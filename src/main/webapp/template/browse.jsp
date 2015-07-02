<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.URLEncoder" %>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css">
		<%@include file="common_includes.html"%>
		<script src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
		<script src="script/browse.js"></script>
		<link rel="stylesheet" href="style/css/browse.css">
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container browse">
				<h3>Datasets</h3>
				<table class="table">
					<thead>
						<tr>
							<td>Name</td>
							<td>Resource</td>
							<td>Description</td>
							<td>Dataset Type</td>
							<td>AttributeType</td>
							<td>Downloads</td>
							<td>Views</td>
				    	</tr>
					</thead>
					<tbody>
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
					                	<span class="download glyphicon glyphicon-file" aria-hidden="true"></span>
					            	</a>
					            </td>
					            <td><%= dataset.getNumPageViews() %></td>
							</tr>
						<% } %>
					</tbody>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>