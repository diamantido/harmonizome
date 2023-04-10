<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet" %>

<t:wrapper title="Dataset - ${dataset.name}" navType="withSearch">
    <jsp:attribute name="jsonld">
        {
            "@context": "http://schema.org",
            "@type": "Dataset",
            "distribution": [
                <c:forEach var="download" items="${dataset.sortedDownloads}" varStatus="loop">
                    {
                        "@type": "DataDownload",
                        "name": "${download.downloadType.name}",
                        "description": "${download.downloadType.description}",
                        "contentUrl": "${Constant.DATA_DIR()}/${dataset.directory}/${download.downloadType.filename}"
                    }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ],
            "description": "${dataset.description}",
            "keywords": "${dataset.datasetGroup.name}",
            "name": "${dataset.name}",
            "provider": {
                "@type": "Organization",
                "name": "Harmonizome"
            },
            "citation": [
                <c:forEach var="pub" items="${dataset.publications}" varStatus="loop">
                    "${pub.longCitation}"
                    <c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ],
            "sourceOrganization": {
                "@type": "Organization",
                "name": "Ma'ayan Laboratory of Computational Systems Biology",
                "url": "http://icahn.mssm.edu/research/labs/maayan-laboratory"
            }
        }
    </jsp:attribute>
    <jsp:body>
        <div class="dataset-page">
            <h1><c:out value="${dataset.name}"/> <span class="note dataset">Dataset</span></h1>
            <section>
                <table class="table">
                    <tr>
                        <td class="col-md-2">Description</td>
                        <td class="col-md-10 initial"><c:out value="${dataset.description}"/></td>
                    </tr>
                    <tr>
                        <td class="col-md-2">Measurement</td>
                        <td class="col-md-10 initial"><c:out value="${dataset.measurement.name}"/></td>
                    </tr>
                    <tr>
                        <td class="col-md-2">Association</td>
                        <td class="col-md-10 initial"><c:out value="${dataset.association}"/></td>
                    </tr>
                    <tr>
                        <td class="col-md-2">Category</td>
                        <td class="col-md-10 initial"><c:out value="${dataset.datasetGroup.name}"/></td>
                    </tr>
                    <tr>
                        <td class="col-md-2">Resource</td>
                        <td class="col-md-10">
                            <a href="${dataset.resource.endpoint}/${dataset.resource.urlEncodedValue}">
                                <c:out value="${dataset.resource.name}"/>
                            </a>
                        </td>
                    </tr>
                    <c:if test="${fn:length(dataset.publications) > 0}">
                        <tr>
                            <td class="col-md-2">Citation(s)</td>
                            <td class="col-md-10">
                                <ul>
                                    <c:forEach var="pub" items="${dataset.publications}">
                                        <li>
                                            <a href="${pub.pubmedUrl}" target="_blank">${pub.longCitation}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="col-md-2">Last Updated</td>
                        <td class="col-md-10">
                            <fmt:parseDate value="${dataset.lastUpdated}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate"/>
                            <fmt:formatDate value="${parsedDate}" pattern="yyyy MMM dd"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-2">Stats</td>
                        <td class="col-md-10">
                            <ol class="list-unstyled">
                                <li><c:out value="${numGenes}"/> genes</li>
                                <li><c:out value="${fn:length(dataset.geneSets)}"/> <c:out value="${dataset.attributeType.name}s"/></li>
                                <li><c:out value="${numGeneAttributeAssociations}"></c:out> gene-<c:out value="${dataset.attributeType.name}"/> associations</li>
                            </ol>
                        </td>
                    </tr>

                </table>
            </section>
            <section>
                <h2>Data Access</h2>
                <table class="table">
                    <tr>
                        <td class="col-md-2">API</td>
                        <td class="col-md-10">
                            <a href="${Constant.API_URL}/${dataset.endpoint}/${dataset.urlEncodedValue}" target="_blank">
                                <button class="btn btn-default glyphicon glyphicon-cloud" data-toggle="tooltip" data-placement="right" title="Access data through JSON API."></button>
                            </a>
                            <a href="static/harmonizomeapi.py" target="_blank">
                                <button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access API through Python module."></button>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-2">Script</td>
                        <td class="col-md-10">
                            <a href="static/harmonizomedownloader.py" target="_blank">
                                <button class="btn btn-default glyphicon glyphicon-check" data-toggle="tooltip" data-placement="right" title="Use Python script to pick and choose what to download for all datasets."></button>
                            </a>
                        </td>
                    </tr>
                    <tr id="download-links">
                        <td class="col-md-2">Downloads</td>
                        <td class="col-md-10">
                            <ul class="list-unstyled">
                                <c:forEach var="download" items="${dataset.sortedDownloads}">
                                    <li><a href="${Constant.DATA_DIR()}/${dataset.directory}/${download.downloadType.filename}" target="_blank">
                                        <span class="capitalize"
                                            download-id="${download.id}">
                                            <c:out value="${download.downloadType.name}"/>
                                        </span>
                                    </a>
                                    <span class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-placement="right" title="${download.downloadType.description}"></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </table>
            </section>
            <c:if test="${not empty dataset.datasetVisualizations}">
                <section class="heat-maps">
                    <h2>Visualizations</h2>
                    <ul class="list-inline">
                        <c:forEach var="heatMap" items="${dataset.datasetVisualizations}">
                            <li>
                            <c:choose>
                                <c:when test="${heatMap.type eq'umap'}">
                                    <p><strong>UMAP</strong></p>
                                </c:when>
                                <c:otherwise>
                                    <p><strong><c:out value="${cfn:convertVizTypeToPlainText(heatMap.type)}"/></strong></p>
                                </c:otherwise>
                            </c:choose>
                                <img class="preview"
                                    src="${Constant.HEAT_MAP_IMAGES_DIR()}/${heatMap.type}/${heatMap.image}"
                                    data-heat-map-type="${heatMap.type}"
                                    data-heat-map-dataset="${dataset.name}"
                                    data-heat-map-url="${Constant.HEAT_MAPS_API_URL}"/>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="heat-map"></div>
                </section>
            </c:if>
            <section>
                <h2 class="initial"><c:out value="${dataset.attributeType.name}"></c:out> <span class="note">Gene Sets</span></h2>
                <p class="instruction">
                    <c:out value="${fn:length(dataset.geneSets)}"/> <c:out value="${dataset.geneSetsDescription}"></c:out>
                </p>
                <table class="table data-table gene-sets">
                    <thead>
                        <tr>
                            <th>Gene Set</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <c:forEach var="geneSet" items="${dataset.geneSets}">
                        <tr>
                            <td class="col-md-2">
                                <a href="${GeneSet.ENDPOINT}/${geneSet.urlEncodedValue}"><c:out value="${geneSet.nameFromDataset}"/></a>
                            </td>
                            <td  class="col-md-10">
                                <c:out value="${geneSet.attribute.descriptionFromNamingAuthority}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
    </jsp:body>
</t:wrapper>

<script src="script/heat-maps.js"></script>
<script src="script/dataset-page.js"></script>
<script>
	HARMONIZOME.datasetPage.setupDataTable();
	HARMONIZOME.datasetPage.trackDownloads("${Constant.DOWNLOAD_COUNT_URL}");
	HARMONIZOME.heatMaps.setupVisualizationsOnDatasetPages();
</script>
