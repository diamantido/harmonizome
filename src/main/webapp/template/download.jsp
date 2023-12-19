<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Download">
    <div class="download-page">
        <h1>Downloads</h1>
        <p class="instruction">Click on a dataset to access its downloads. Click on a column header to sort the table by that column. Type in the search bar to filter.</p>
        <p class="instruction">To download the data programmatically, use <a href="documentation">the API</a> or <a href="static/harmonizomedownloader.py" target="_blank">this Python script</a>.</p>
        <div class="table-responsive">
            <button type="button" id="toggleArchived" class="btn btn-outline-primary position-absolute end-0" data-bs-toggle="button" autocomplete="off">Show Archived Datasets</button>
            <table class="table data-table">
                <thead>
                    <tr>
                        <th>Resource</th>
                        <th>Dataset</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Attribute</th>
                        <th>Views</th>
                        <th>Archived</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dataset" items="${datasets}">
                        <tr>
                            <td>
                                <a href="resource/${dataset.resource.urlEncodedValue}">
                                    ${dataset.resource.name}
                                </a>
                            </td>
                            <td>
                                <a href="dataset/${dataset.urlEncodedValue}">
                                    ${dataset.nameWithoutResource}
                                </a>
                            </td>
                            <td>${dataset.description}</td>
                            <td>${dataset.datasetGroup.name}</td>
                            <td>${dataset.attributeType.name}</td>
                            <td>${dataset.numPageViews}</td>
                            <td>${dataset.isArchived}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:wrapper>

<script src="script/download-page.js"></script>
<script>
	HARMONIZOME.setupDataTable();
</script>
