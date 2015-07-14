<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css">
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container browse">
				<h1>Datasets</h1>
				<p class="instruction">Sort the table below clicking on the headers or type in the search bar.</p>
				<table class="data-table table">
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
						<% @SuppressWarnings("unchecked")
						List<Dataset> datasets = (List<Dataset>) request.getAttribute("datasets");
						for (int i = 0; i < datasets.size(); i++) { %>
							<% Dataset dataset = datasets.get(i); %>
							<tr>
								<td><%= dataset.getName() %></td>
								<td><%= dataset.getResource().getName() %></td>
								<td><%= dataset.getDescription() %></td>
								<td><%= dataset.getDatasetType().getName() %></td>
								<td><%= dataset.getAttributeType().getName() %></td>
								<td>
									<a href="dataset/<%= URLEncoder.encode(dataset.getName(), "UTF-8") %>" type="button" class="btn btn-default download" aria-label="Left Align">
										<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
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