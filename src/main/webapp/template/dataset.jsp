<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<h1><c:out value="${dataset.name}"/> <span class="note">Dataset</span></h1>
				<section>
				    <table class="table">
				    	<tr>
				    		<td>Description</td>
				    		<td class="initial"><c:out value="${dataset.description}"/></td>
				    	</tr>
				    	<tr>
				    		<td>Measurement</td>
				    		<td class="initial"><c:out value="${dataset.measurement.name}"/></td>
				    	</tr>
				    	<tr>
				    		<td>Association</td>
				    		<td class="initial"><c:out value="${dataset.association}"/></td>
				    	</tr>
				    	<tr>
				    		<td>Category</td>
				    		<td class="initial"><c:out value="${dataset.datasetGroup.name}"/></td>
				    	</tr>
				    	<tr>
				    		<td>Resource</td>
				    		<td>
				    			<a href="${dataset.resource.endpoint}/${dataset.resource.urlEncodedName}">
				    				<c:out value="${dataset.resource.name}"/>
				    			</a>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td>Citation(s)</td>
				    		<td>
								<c:forEach var="pub" items="${dataset.publications}" varStatus="loop">
									<a href="${pub.pubmedUrl}" target="_blank">${pub.longCitation}</a><c:if test="${!loop.last}">, </c:if>
								</c:forEach>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td>Last Updated</td>
				    		<td>
				    			<c:out value="${dataset.lastUpdated}"/>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td>No. Genes</td>
				    		<td><c:out value="${numGenes}"/></td>
				    	</tr>
				    	<tr>
				    		<td class="capitalize">No. <c:out value="${dataset.attributeType.name}s"/></td>
				    		<td><c:out value="${fn:length(dataset.attributes)}"/></td>
				    	</tr>
				    	<tr>
				    		<td class="capitalize"><c:out value="No. Gene-${dataset.attributeType.name} Associations"/></td>
				    		<td><c:out value="${numGeneAttributeAssociations}"/></td>
				    	</tr>
						<tr>
							<td class="col-md-2">API</td>
							<td class="col-md-10">
								<a href="${Constant.API_URL}/${dataset.endpoint}/${dataset.urlEncodedName}" target="_blank">
									<button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access ${dataset.name} programmatically."></button>
								</a>
							</td>
						</tr>
				    </table>
				</section>
				<section>
					<h2>Downloads</h2>
				    <table class="table downloads">
				    	<thead>
				    		<tr>
				    			<th>Content</th>
				    			<th>Download link</th>
				    			<th>Number of downloads</th>
				    		</tr>
				    	</thead>
				    	<tbody>
				    		<c:forEach var="download" items="${dataset.sortedDownloads}">
				    			<tr>
				    				<td>
				    					<span class="capitalize">
				    						<c:out value="${download.downloadType.name}"/>
				    					</span>
				    					<span class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-placement="right" title="${download.downloadType.description}">
				    					</span>
				    				</td>
				    				<td>
				    					<a href="${download.endpoint}/${dataset.directory}/${download.downloadType.name}">
				    						<span class="btn btn-default glyphicon glyphicon-download-alt" aria-hidden="true"></span>
				    					</a>
				    				</td>
				    				<td><c:out value="${download.count}"/></td>
				    			</tr>
				    		</c:forEach>
				    	</tbody>
				    </table>
				</section>
				<section>
					<h2>Gene Sets <span class="note"><c:out value="${fn:length(dataset.attributes)}"/></span></h2>
					<c:forEach var="attribute" items="${attributesFromDataset}" varStatus="loop">
						<a href="${attribute.endpoint}/${attribute.nameFromDataset}/${dataset.name}"><c:out value="${attribute.nameFromDataset}"/></a><c:if test="${!loop.last}">, </c:if>
					</c:forEach>
				</section>
				<!-- End dataset content -->

			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>