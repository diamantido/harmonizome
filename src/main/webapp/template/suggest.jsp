<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>

<t:wrapper title="Suggestions" navType="withSearch">
	<div class="suggest-page">
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
</t:wrapper>