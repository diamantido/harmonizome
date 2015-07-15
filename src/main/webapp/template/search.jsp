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
			<%--<%@include file="search_bar.html" %>--%>
			<div class="content container-full">
				<div class="container">
					<div id="intro" class="row">
					    <div class="col-sm-4">
					        <div class="portal">
					            <h2>Datasets</h2>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
				                <a href="dataset/" class="btn btn-default">Browse datasets</a>
					        </div>
					    </div>
					    <div class="col-sm-4">
					        <div class="portal">
					            <h2>Genes</h2>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <div id="gene-search" class="input-group">
									<input type="text" class="form-control" placeholder="Start typing a gene symbol">
									<span class="input-group-btn">
										<button class="btn btn-default" type="submit">Go!</button>
									</span>
								</div>
					        </div>
					    </div>
					    <div class="col-sm-4">
					        <div class="portal">
					            <h2>Attributes</h2>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <div id="attribute-search" class="input-group">
									<input type="text" class="form-control" placeholder="Please enter an attribute">
									<span class="input-group-btn">
										<button class="btn btn-default" type="submit">Go!</button>
									</span>
								</div>
					        </div>
					    </div>
					    examples
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>