<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="${typeView} Heat Maps">

    <script src="script/heatmaps.js"></script>

    <div class="dataset-heatmaps-page heat-map-page" data-heat-map-type="${type}">
        <h1 class="initial"><c:out value="${typeView}"/> Heat Maps</h1>
        <p class="instruction">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        <div class="section">
            <label>Dataset</label>
            <select id="dataset">
                <option>(Please select a dataset)</option>
                <c:forEach var="dataset" items="${datasets}">
                    <option value="${dataset.urlEncodedValue}"><c:out value="${dataset.name}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="heat-map">
            <div class="image-wrapper"></div>
        </div>
    </div>
</t:wrapper>