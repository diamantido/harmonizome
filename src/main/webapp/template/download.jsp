<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Download">
    <div class="download-page">
        <h1>Downloads</h1>
        <p class="instruction">Click on a dataset or <a href="documentation">use the API</a> to access data. Click on the column headers to sort the table. Type in the search bar to filter.</p>
        <div class="table-responsive">
            <table class="table dt-responsive data-table">
                <thead>
                    <tr>
                        <th>Resource</th>
                        <th>Dataset</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Attribute</th>
                        <th>Views</th>
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
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:wrapper>