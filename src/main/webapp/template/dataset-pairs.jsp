<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="Matrix Visualization">

    <script src="script/matrix.js"></script>

    <div class="matrices-page">
        <h1>Matrix Visualization</h1>

        <div>
            <label>Dataset 1</label>
            <select id="dataset-1">
                <option>(Please select a dataset)</option>
                <c:forEach var="dataset" items="${datasets}">
                    <option value="${dataset.urlEncodedValue}"><c:out value="${dataset.name}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label>Dataset 2</label>
            <select id="dataset-2">
                <option>(Please select a dataset)</option>
                <c:forEach var="dataset" items="${datasets}">
                    <option value="${dataset.urlEncodedValue}"><c:out value="${dataset.name}"/></option>
                </c:forEach>
            </select>
        </div>

        <button class="btn btn-default">Visualize</button>

        <div id="matrix">
        </div>
    </div>
</t:wrapper>