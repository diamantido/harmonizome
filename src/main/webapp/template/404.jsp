<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<title>404 - Page Not Found</title>
	</head>
	<body>
		<%@include file="navbar.jsp"%>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container not-found-page">
					<h1>Oops. There is nothing here.</h1>
					<p class="instruction">Search below or <a href="">return to the homepage</a>.</p>
					<div class="col-md-6 input-group">
						<jsp:include page="searchBar.jsp">
							<jsp:param value="on-404-page" name="location"/>
						</jsp:include>
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>