<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="globalIncludes.html" %>
		<%@include file="commonIncludes.html" %>
		<script>
			$(function() {
				HMZ();
			});
		</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container browse">
				<h1>Datasets</h1>
				<p class="instruction">Sort the table below clicking on the headers or type in the search bar.</p>
				<table class="table data-table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Resource</th>
							<th>Resource</th>
							<th>Description</th>
							<th>Category</th>
							<th>Attribute</th>
							<th>Downloads</th>
							<th>Views</th>
				    	</tr>
					</thead>
					<tbody>
						<c:forEach var="dataset" items="${datasets}">
							<tr>
								<td>${dataset.name}</td>
								<td>
									<a href="resource/${dataset.resource.urlEncodedName}">
										${dataset.resource.name}
									</a>
								</td>
								<td>${dataset.description}</td>
								<td>${dataset.datasetGroup.name}</td>
								<td>${dataset.datasetGroup.name}</td>
								<td>${dataset.attributeType.name}</td>
								<td>
									<a href="dataset/${dataset.urlEncodedName}" type="button" class="btn btn-default download" aria-label="Left Align">
										<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
						            </a>
					            </td>
					            <td>${dataset.numPageViews}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>