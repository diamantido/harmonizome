<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>

<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container suggest-page">
					<h1>Suggestions</h1>
					<p class="note">Did you mean...</p>
					<ul class="list-inline">
					<c:forEach var="name" items="${datasetSuggestions}">
						<c:out value="${name}"/>
						<li>
							<span class="badge dataset">
								<a href="${Constant.API_URL}?q=${UrlCodec.encode(name)}">${name}</a>
							</span>
						</li>
					</c:forEach>
					<c:forEach var="name" items="${geneSuggestions}">
						<li>
							<span class="badge gene">
								<a href="${Constant.SEARCH_URL}?q=${UrlCodec.encode(name)}">${name}</a>
							</span>
						</li>
					</c:forEach>
					<c:forEach var="name" items="${geneSetSuggestions}">
						<li>
							<span class="badge gene-set">
								<a href="${Constant.SEARCH_URL}?q=${UrlCodec.encode(name)}">${name}</a>
							</span>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>