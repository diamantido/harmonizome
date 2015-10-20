<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="404 - Page Not Found">
	<div class="not-found-page">
		<h1>Oops. There is nothing here.</h1>
		<p class="instruction">Search below or <a href="">return to the homepage</a>.</p>
		<div class="col-md-6 input-group">
			<jsp:include page="fragment/searchBar.jsp">
				<jsp:param value="on-404-page" name="location"/>
			</jsp:include>
		</div>
	</div>
</t:wrapper>