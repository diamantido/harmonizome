<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container browse">
				<h1>Datasets</h1>
				<p class="instruction">Click on a dataset to download data or <a href="documentation">use the API</a>. Sort the table below clicking on the headers or type in the search bar.</p>
				<table class="table data-table">
					<thead>
						<tr>
							<th>Resource</th>
							<th>Dataset</th>
							<th>Description</th>
							<th>Category</th>
							<th>Attribute</th>
							<th>Views</th>
				    	</tr>
					</thead>
					<tbody>
						<c:forEach var="dataset" items="${datasets}">
							<tr>
								<td>
									<a href="resource/${dataset.resource.urlEncodedName}">
										${dataset.resource.name}
									</a>
								</td>
								<td>
									<a href="dataset/${dataset.urlEncodedName}">
										${dataset.nameWithoutResource}
									</a>
								</td>
								<td>${dataset.description}</td>
								<td>${dataset.datasetGroup.name}</td>
								<td>${dataset.attributeType.name}</td>
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