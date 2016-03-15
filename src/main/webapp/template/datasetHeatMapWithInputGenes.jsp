<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>

<t:wrapper title="Heat Map with Input Genes">

    <div class="dataset-heatmaps-page heat-map-page heat-map-with-input-genes-page">
        <h1 class="initial">Heat Map with Input Genes</h1>
        <p class="instruction">
            <c:out value="Select from the drop down menu to choose a dataset. Paste the list in the text box. Click submit to build a customized hierarchically clustered heat map visualization of the associations between your uploaded genes and the biological entities in your chosen dataset."/>
        </p>
        <div class="section">
            <label>Dataset</label>
            <select id="dataset">
                <option>(Please select a dataset)</option>
                <c:forEach var="dataset" items="${datasets}">
                    <option value="${dataset.name}"><c:out value="${dataset.name}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="row">
        	<form class="col-md-4">
	        	<textarea class="form-control"></textarea>
	        	<button class="btn btn-default">Submit</button>
	        </form>
        </div>
        <p class="heat-map-link"></p>
        <div class="heat-map"></div>
    </div>
    
    <script src="script/heat-maps.js"></script>
    <script>
    	HARMONIZOME.heatMaps.setupHeatMapWithInputGenesPage();
    </script>

</t:wrapper>