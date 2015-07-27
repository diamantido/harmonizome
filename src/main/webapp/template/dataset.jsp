<!DOCTYPE HTML>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Download" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Publication" %>
<%
Dataset dataset = (Dataset) request.getAttribute("dataset");
Timestamp downloadDate = dataset.getDownloadDate();
String downloadDateStr = "";
if (downloadDate == null) {
	downloadDateStr = "";
} else {
	downloadDateStr = new SimpleDateFormat("MM/dd/yyyy").format(downloadDate);
}

%>
<html>
	<head>
		<%@include file="globalIncludes.html" %>
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<h1><%= dataset.getName() %> <span class="note">Dataset</span></h1>
				<section>
				    <table class="table">
				    	<tr>
				    		<td>Description</td>
				    		<td><%= dataset.getDescription() %></td>
				    	</tr>
				    	<tr>
				    		<td>Measurement</td>
				    		<td><%= dataset.getMeasurement().getName() %></td>
				    	</tr>
				    	<tr>
				    		<td>Association</td>
				    		<td><%= dataset.getAssociation() %></td>
				    	</tr>
				    	<tr>
				    		<td>Category</td>
				    		<td><%= dataset.getDatasetGroup().getName() %></td>
				    	</tr>
				    	<tr>
				    		<td>Resource</td>
				    		<td>
				    			<a href="resource/<%= URLCodec.encode(dataset.getResource().getName()) %>">
				    				<%= dataset.getResource().getName() %>
				    			</a>	
				    		</td>
				    	</tr>
				    	<tr>
				    		<td>Citation(s)</td>
				    		<td>
							<% 
							Iterator<Publication> pubIter = dataset.getPublications().iterator();
							while (pubIter.hasNext()) {
								Publication pub = pubIter.next();
							%>
								<a href="<%= pub.getPubmedUrl() %>"><%= pub.getLongCitation() %></a>
								<% if (pubIter.hasNext()) { %>, <% } %>
							<% } %>
				    		</td>
				    	</tr>
				    	<tr>
				    		<td>Last Updated</td>
				    		<td></td>
				    	</tr>
				    	<tr>
				    		<td>No. Gene</td>
				    		<td>${numberOfGenes}</td>
				    	</tr>
				    	<tr>
				    		<td>No. Attributes</td>
				    		<td><%= dataset.getAttributes().size() %></td>
				    	</tr>
				    	<tr>
				    		<td>No. Gene-Attribute Associations</td>
				    		<td>${numberOfGeneAttributeAssociations}</td>
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
			    		<% @SuppressWarnings("unchecked")
			    		Set<Download> downloads = dataset.getDownloads();
			    		for (Download dl : downloads) { 
			    			String downloadType = dl.getType().getName();
				    		String glypiconType;
				    		if (downloadType.equals("gene list") || downloadType.equals("edge list") || downloadType.equals("attribute list")) {
				    			glypiconType = "glyphicon-align-justify";
				    		} else if (downloadType.equals("gene similarity matrix") || downloadType.equals("attribute similarity matrix") || downloadType.equals("attribute table")) {
				    			glypiconType = "glyphicon-th";
				    		} else if (
			    				downloadType.equals("gene set library") ||
			    				downloadType.equals("down gene set library") ||
			    				downloadType.equals("up gene set library") ||
			    				downloadType.equals("attribute set library") ||
			    				downloadType.equals("down attribute set library") ||
			    				downloadType.equals("up attribute set library")
			    			) {
				    			glypiconType = "glyphicon-th-list";
				    		} else if (downloadType.equals("processing script")) {
				    			glypiconType = "glyphicon-wrench";
				    		} else {
				    			glypiconType = "glyphicon-download";
				    		}
			    		%>
			    			<tr>
			    				<td><%= StringUtils.capitalize(downloadType) %></td>
			    				<td>
			    					<a href="download/<%= dl.getDirectory() + "/" + dl.getName() %>">
			    						<span class="btn btn-default glyphicon <%= glypiconType %>" aria-hidden="true"></span>
			    					</a>
			    				</td>
			    				<td><%= dl.getCount() %></td>
			    			</tr>
			    		<% } %>
				    	</tbody>
				    </table>
				</section>
				<!-- End dataset content -->

			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>