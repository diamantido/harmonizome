<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<html lang="en">
    <head>
		<%@include file="commonIncludes.html" %>
		<script>$(function() { HMZ(); });</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container">
					<h1>Sorry, that page doesn't exist!</h1>
					<p class="instruction">Search below or <a href="">return to the homepage</a>.</p>
					<div id="search" class="col-md-6 input-group">
						<input type="text" class="form-control" placeholder="">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Go!</button>
						</span>
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>