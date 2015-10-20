<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="${query}">
	<div class="no-search-results-page">
		<h1>No Search Results</h1>
		<p class="instruction">Your search "<c:out value="${query}"/>" did not match any entities.</p>
		<div>
			<p>Suggestions:</p>
			<ul>
				<li>Check each keyword's spelling.</li>
				<li>Try different keywords.</li>
				<li>Try fewer or more general keywords.</li>
			</ul>
		</div>
		<div class="col-md-6 input-group">
			<jsp:include page="fragment/searchBar.jsp">
				<jsp:param value="on-no-results-page" name="location"/>
			</jsp:include>
		</div>
	</div>
</t:wrapper>