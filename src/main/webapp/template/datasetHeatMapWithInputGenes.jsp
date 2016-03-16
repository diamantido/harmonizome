<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>

<t:wrapper title="Heat Map with Input Genes">

    <div class="dataset-heatmaps-page heat-map-page heat-map-with-input-genes-page">
        <h1 class="initial">Heat Map with Input Genes</h1>
        <p class="instruction">Select from the drop down menu to choose a dataset. Paste your gene list in the text box. Click submit to build a customized hierarchically clustered heat map visualization of the associations between your uploaded genes and the biological entities in your chosen dataset.</p>
		<div class="instruction restrictions">To place an upper bound on the size of the heat maps, which load more slowly as they grow larger, we have two restrictions:
			<ol>
				<li>Input gene list can have 500 genes maximum.</li>
				<li>Only datasets with 200 or fewer gene sets can be visualized.</li>
			</ol>
		</div>
		<div class="section row">
			<label class="col-md-1">Dataset</label>
            <div class="col-md-5">
            	<select id="dataset">
	                <option>(Please select a dataset)</option>
	                <c:forEach var="dataset" items="${datasets}">
	                    <option value="${dataset.name}"><c:out value="${dataset.name}"/></option>
	                </c:forEach>
	            </select>
            </div>
        </div>
        <div class="row">
        	<label class="col-md-1">Genes</label>
        	<div class="col-md-5">
		        <textarea class="form-control"></textarea>
        	</div>
        </div>
        <div class="row">
        	<div class="col-md-12">
        		<div class="button-wrapper">
        			<button class="btn btn-default" id="example-btn">Example input</button>
        			<button class="btn btn-default" id="submit-btn">Submit</button>
        		</div>
        	</div>
        </div>
        <div id="heat-map-wrapper">
        	<p class="heat-map-link"></p>
        	<div class="heat-map"></div>
        </div>
    </div>
    
    <script src="script/heat-maps.js"></script>
    <script>
    	HARMONIZOME.heatMaps.setupHeatMapWithInputGenesPage();
    </script>

</t:wrapper>