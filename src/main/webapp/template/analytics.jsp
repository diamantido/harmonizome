<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="Analytics">

	<div class="analytics-page">
		<h1>Analytics</h1>
		<div class="row">
			<div class="col-md-6">
				<h4>Search queries</h4>
		        <p class="instruction">Total queries: </p>
		        <table class="table data-table">
		            <thead>
		                <tr>
		                    <th>Search keywords</th>
		                    <th>Count</th>
		                </tr>
		            </thead>
		            <c:forEach var="term" items="${searchedWords}">
		                <tr>
		                    <td class="col-md-3"><c:out value="${term.keywords}"/></td>
		                    <td class="col-md-9"><c:out value="${term.count}"/></td>
		                </tr>
		            </c:forEach>
		        </table>
			</div>
			<div class="col-md-6">
				<h4>Download counts</h4>
				<table class="table data-table">
		            <thead>
		                <tr>
		                    <th>Dataset</th>
		                    <th>Download type</th>
		                    <th>Count</th>
		                </tr>
	                </thead>
		            <c:forEach var="download" items="${downloads}">
		                <tr>
		                	<td class="col-md-3"><c:out value="${download.dataset.name}"/></td>
		                    <td class="col-md-3"><c:out value="${download.downloadType.name}"/></td>
		                    <td class="col-md-9"><c:out value="${download.count}"/></td>
		                </tr>
		            </c:forEach>
	            </table>
			</div>
		</div>
	</div>
</t:wrapper>

<script src="script/analytics-page.js"></script>
<script>
	HARMONIZOME.setupDataTable();
</script>