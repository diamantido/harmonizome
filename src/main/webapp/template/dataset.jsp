<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet" %>
<!DOCTYPE HTML>
<html>
	<head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container dataset-page">
				<h1><c:out value="${dataset.name}"/> <span class="note dataset">Dataset</span></h1>
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
				    			<a href="${dataset.resource.endpoint}/${dataset.resource.urlEncodedValue}">
				    				<c:out value="${dataset.resource.name}"/>
				    			</a>
				    		</td>
				    	</tr>
				    	<c:if test="${fn:length(dataset.publications) > 0}">
					    	<tr>
					    		<td>Citation(s)</td>
					    		<td>
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
				    		<td>Last Updated</td>
				    		<td>
				    			<fmt:parseDate value="${dataset.lastUpdated}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate"/>
				    			<fmt:formatDate value="${parsedDate}" pattern="yyyy MMM dd"/>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td>Stats</td>
				    		<td>
				    			<ol class="list-unstyled">
				    				<li><c:out value="${numGenes}"/> genes</li>
				    				<li><c:out value="${fn:length(dataset.attributes)}"/> <c:out value="${dataset.attributeType.name}s"/></li>
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
									<button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access ${dataset.name} programmatically."></button>
								</a>
							</td>
						</tr>
						<tr>
							<td class="col-md-2">Downloads</td>
							<td class="col-md-10">
								<ul class="list-unstyled">
									<c:forEach var="download" items="${dataset.sortedDownloads}">
				    					<li><a href="${Constant.DATA_DIRECTORY}/${dataset.directory}/${download.downloadType.filename}" target="_blank">
				    						<span class="capitalize">
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
				<section>
					<h2 class="initial"><c:out value="${dataset.attributeType.name}"></c:out> <span class="note">Gene Sets</span></h2>
					<p class="instruction">
						<c:out value="${fn:length(dataset.attributes)}"/> <c:out value="${dataset.geneSetsDescription}"></c:out>
					</p>
					<table class="table data-table gene-sets">
						<thead>
							<tr>
								<th>Gene Set</th>
								<th>Description</th>
							</tr>
						</thead>
						<c:forEach var="attribute" items="${attributesFromDataset}">
							<tr>
								<td class="col-md-2">
									<a href="${GeneSet.ENDPOINT}/${attribute.nameFromDataset}/${dataset.name}"><c:out value="${attribute.nameFromDataset}"/></a>
								</td>
								<td  class="col-md-10">
									<c:out value="${attribute.descriptionFromNamingAuthority}"/>
								</td>
							</tr>
						</c:forEach>
					</table>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>