<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<html lang="en">
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
					<div id="landing" class="row">
						<div class="col-md-12 text-center">
							<img src="image/logo_100x96.png">
							<h1 class="project-title">Harmonizome</h1>
							<p class="instruction">Search for genes and functional terms extracted and organized from over a hundred publicly available resources.</p>
						</div>
						<div class="row">
							<div id="search" class="col-md-offset-2 col-md-8 input-group">
								<input type="text" class="form-control" placeholder="">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit">Go!</button>
								</span>
							</div>	
						</div>
						<div class="row">
							<div class="col-md-12 top-buffer text-center">
								<strong>Example searches</strong>
								<ul class="list-inline">
									<li>
										<a href="<%= Constant.SEARCH_URL %>/achilles">achilles</a>
									</li>
									<li>
										<a href="<%= Constant.SEARCH_URL %>/STAT3">STAT3</a>
									</li>
									<li>
										<a href="<%= Constant.SEARCH_URL %>/breast+cancer">breast cancer</a>
									</li>
								</ul>
							</div>
					    </div>
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>