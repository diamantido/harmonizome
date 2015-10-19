<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<title>Unknown Server Error</title>
	</head>
	<body>
		<%@include file="navbar.jsp"%>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container not-found-page">
					<h1>500 Error</h1>
					<p class="instruction">Oops. This is our fault. <a href="">Please return to the homepage</a>.</p>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>