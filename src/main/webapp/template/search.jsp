<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
		<script>
			<% @SuppressWarnings("unchecked") 
			List<String> genesArray = (List<String>) request.getAttribute("genes");
			String genes = "['" + StringUtils.join(genesArray, "', '") + "']";
			Long numberOfDatasets = (Long) request.getAttribute("numberOfDatasets");
			%>
			$(function() {
				HMZ({
					ALL_GENES: <%= genes %>
				});
			});
		</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container">
					<div id="intro" class="row">
					    <div class="col-sm-8">
				            <h2>Genes & Gene Sets</h2>
				            <p>Search gene symbols and gene sets.</p>
				            <div id="search" class="input-group">
								<input type="text" class="form-control" placeholder="">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit">Go!</button>
								</span>
							</div>
							<div class="top-buffer">
								Example searches:
								<ul class="list-inline">
									<li>
										<a href="search/nanog">nanog</a>
									</li>
									<li>
										<a href="search/breast+cancer">breast cancer</a>
									</li>
								</ul>
							</div>
					    </div>
						<div class="col-sm-4">
				            <h2>Datasets</h2>
				            <p>Browse ${numberOfDatasets} datasets, extracted from over 50 online resources.</p>
			                <a href="dataset/" class="btn btn-default">Browse datasets</a>
					    </div>
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>