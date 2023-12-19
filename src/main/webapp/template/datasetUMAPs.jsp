<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>

<t:wrapper title="UMAP">

    <div class="dataset-heatmaps-page heat-map-page" data-heat-map-type="umap">
        <h1 class="initial">Dataset UMAPs</h1>
        <p class="instruction">
            Select from the drop down menu to explore an interactive UMAP visualization of the gene sets derived from a dataset. Gene set clusters are formed based on TF-IDF vectorization of their associated genes.
        </p>
        <div class="section">
            <label>Dataset</label>
            <select id="dataset">
                <option>(Please select a dataset)</option>
                <c:forEach var="dataset" items="${datasets}">
                    <option value="${dataset.urlEncodedValue}"><c:out value="${dataset.name}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="umap heat-map"></div>
    </div>
    
    <script src="script/heat-maps.js"></script>
    <script>
    	HARMONIZOME.heatMaps.setupIndividualDatasetHeatMapsPage("umap");
    </script>

</t:wrapper>