<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>

<t:wrapper title="Dataset Pair Heat Maps">

    <script src="script/heatmaps.js"></script>

    <div class="heat-map-page dataset-pair-heat-maps-page">
        <h1>Dataset Pair Heat Maps</h1>
        <p class="instruction">Select from the drop down menu to explore a hierarchically clustered heat map visualization of the similarity of attributes from two datasets.  Red tiles indicate pairs of attributes that are similar based on their associations with genes common to both datasets.  Blue tiles indicate pairs of attributes that are anti-similar--the two attributes have oppositely signed associations with many of the same genes.  White tiles indicate pairs of attributes with few to no overlapping associations.</p>
        <div class="section">
            <label>Dataset 1</label>
            <select id="dataset-1">
                <option>(Please select a dataset)</option>
                <c:forEach var="dataset" items="${datasets}">
                    <option value="${cfn:urlEncode(dataset)}"><c:out value="${dataset}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="section hidden">
            <label>Dataset 2</label>
            <select id="dataset-2">
                <option>(Please select a dataset)</option>
            </select>
        </div>
        <div class="button-wrapper">
            <button class="btn btn-default">Visualize</button>
        </div>
        <div class="heat-map"></div>
    </div>
</t:wrapper>