<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Download" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Publication" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.DownloadComparator" %>
<%
Dataset dataset = (Dataset) request.getAttribute("dataset");
@SuppressWarnings("unchecked")
List<Attribute> attributesFromDataset = (List<Attribute>) request.getAttribute("attributesFromDataset");
Timestamp downloadDate = dataset.getLastUpdated();
String downloadDateStr = "";
if (downloadDate == null) {
	downloadDateStr = "";
} else {
	downloadDateStr = new SimpleDateFormat("MM/dd/yyyy").format(downloadDate);
}
NumberFormat numFormatter = NumberFormat.getNumberInstance(Locale.US);

%>
<html>
	<head>
		<%@include file="globalIncludes.html" %>
		<%@include file="commonIncludes.html" %>
		<script>
			$(function() {
				HMZ();
			});
		</script>
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
				    </table>
				</section>
				<section>
					<h2>Downloads</h2>
				    <table class="table">
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
						<a href="${attribute.endpoint}/${attribute.nameFromDataset}/${dataset.name}">
							<!-- Weird tag formatting to not create unwanted spaces. -->
							<c:out value="${attribute.nameFromDataset}"/></a><c:if test="${!loop.last}">, </c:if>
					</c:forEach>
				</section>
				<!-- End dataset content -->

			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>