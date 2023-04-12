<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>

<t:wrapper title="${cfn:convertVizTypeToPlainText(typeView)} Heat Maps">

    <div class="dataset-heatmaps-page heat-map-page" data-heat-map-type="${type}">
        <h1 class="initial"><c:out value="${cfn:convertVizTypeToPlainText(typeView)}"/> Heat Maps</h1>
        <p class="instruction">
            <c:choose>
                <c:when test="${type == 'gene_attribute'}">
                    <c:out value="Select from the drop down menu to explore a hierarchically clustered heat map visualization of a dataset. Red tiles indicate positive or unsigned gene-biological entity associations. Blue tiles indicate negative associations."/>
                </c:when>
                <c:when test="${type == 'gene_similarity'}">
                    <c:out value="Select from the drop down menu to explore a hierarchically clustered heat map visualization of the gene similarity matrix derived from a dataset.Red tiles indicate pairs of genes that are similar based on their associations with biological entities in the selected dataset. Blue tiles indicate pairs of genes that are anti-similar—the two genes have oppositely signed associations with many of the same biological entities. White tiles indicate pairs of genes with few to no overlapping associations."/>
                </c:when>
                <c:when test="${type == 'attribute_similarity'}">
                    <c:out value="Select from the drop down menu to explore a hierarchically clustered heat map visualization of the attribute similarity matrix derived from a dataset. Red tiles indicate pairs of attributes that are similar based on their associations with genes in the selected dataset. Blue tiles indicate pairs of attributes that are anti-similar—the two attributes have oppositely signed associations with many of the same genes. White tiles indicate pairs of attributes with few to no overlapping associations."/>
                </c:when>
                <c:when test="${type == 'umap'}">
                    <c:out value="Select from the drop down menu to explore an interactive UMAP visualization of the gene sets derived from a dataset. Gene set clusters are formed based on TF-IDF vectorization of their associated genes."/>
                </c:when>
            </c:choose>
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
        <div class="heat-map"></div>
    </div>
    
    <script src="script/heat-maps.js"></script>
    <script>
    	HARMONIZOME.heatMaps.setupIndividualDatasetHeatMapsPage("${type}");
    </script>

</t:wrapper>