<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
	</head>
	<body>
		<%@include file="navbarWithSearch.jsp" %>
		<div class="wrapper">
			<div class="content container">
				<h1><c:out value="${namingAuthority.name}"/> <span class="note">Naming Authority</span></h1>
				<section>
				    <table class="table">
				    	<tr>
				    		<td class="col-md-2">Acronym</td>
				    		<td class="initial col-md-10">
				    			<c:out value="${namingAuthority.acronym}"/>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td class="col-md-2">Description</td>
				    		<td class="initial col-md-10">
				    			<c:out value="${namingAuthority.description}"/>
				    		</td>
				    	</tr>

				    	<tr>
				    		<td class="col-md-2">External Link</td>
				    		<td class="col-md-10">
				    			<a href="${namingAuthority.url}" target="_blank">${namingAuthority.url}</a>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td class="col-md-2">Citation</td>
				    		<td class="initial col-md-10">
				    			<a href="${namingAuthority.publication.pubmedUrl}" target="_blank">
				    				<c:out value="${namingAuthority.publication.longCitation}"></c:out>
				    			</a>
				    		</td>
				    	</tr>
				    </table>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>