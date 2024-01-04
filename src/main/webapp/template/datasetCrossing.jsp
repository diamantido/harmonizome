<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>

<t:wrapper title="Dataset Pair Gene Set Crossing">
    <div class="dataset-crossing-page">
        <h1>Common Fund Dataset Pair Gene Set Crossing</h1>
        <p class="instruction">Select from the drop down menu to explore the similarity of attributes from two datasets from the <a href="https://commonfund.nih.gov">NIH Common Fund Program</a>. Gene set pairs will be displayed, along with p-value, Jaccard index, and overlapping genes.
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
            <button id="button" class="btn btn-default">Submit</button>
        </div>
        <div class="table-responsive">
            <table id="dataTable" class="data-table table-responsive"></table>
        </div>
    </div>
    
    <script src="script/dataset-crossing.js"></script>
   	<script>
    	HARMONIZOME.cross.setupDatasetCrossingPage();
    </script>
</t:wrapper>