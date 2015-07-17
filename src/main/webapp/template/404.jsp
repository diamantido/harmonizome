<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>

		<title>Page Not Found</title>

		<link rel="shortcut icon" href="image/logo_16x16.png?v=c">
		<link rel="stylesheet" type='text/css' href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="/Harmonizome/style/css/common.css">

		<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
		<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		<script src="script/common.js"></script>
		<script>$(function() { HMZ(); });</script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container">
					<h1>404 Error</h1>
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