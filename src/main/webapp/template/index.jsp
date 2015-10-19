<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    	<meta name="description" content="Search for genes and functional terms extracted and organized from over a hundred publicly available resources."/>
	</head>
	<body>
		<%@include file="navbar.jsp"%>
		<div class="content container-full">
			<div class="container">
				<div id="landing">
					<div class="row">
						<div class="col-md-12 text-center">
							<img src="image/logo_100x87.png">
							<h1 class="project-title">Harmonizome</h1>
							<p class="instruction">Search for genes or proteins and their functional terms extracted and organized from over a hundred publicly available resources. <a href="about#resources"><strong>Learn more</strong></a>.</p>
						</div>
					</div>
					<jsp:include page="searchBar.jsp">
						<jsp:param value="on-index-page" name="location"/>
					</jsp:include>
					<div class="row top-buffer text-center">
						<div class="col-md-12">
							<strong>Example searches</strong>
							<ul class="list-inline">
								<li>
									<a href="${Constant.SEARCH_URL}?q=achilles">achilles</a>
								</li>
								<li>
									<a href="${Constant.SEARCH_URL}?q=STAT3">STAT3</a>
								</li>
								<li>
									<a href="${Constant.SEARCH_URL}?q=breast+cancer">breast cancer</a>
								</li>
							</ul>
						</div>
				    </div>
				</div>
			</div>
		</div>
		<%@include file="footer.html"%>
	</body>
</html>