<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="Analytics">

    <script src="script/analytics.js"></script>

	<div class="analytics-page">
        <h1>Search Analytics</h1>
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
</t:wrapper>