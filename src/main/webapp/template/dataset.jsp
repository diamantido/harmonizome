<!DOCTYPE HTML>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Download" %>
<html>
	<head>
		<%@include file="common_includes.html" %>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				
				<!-- Dataset content -->
				<section>
					<h3><%= request.getAttribute("name") %> <span class="note">Dataset</span></h3>
				    <table class="table">
				    	<caption>Details</caption>
				    	<tr>
				    		<td>Resource</td>
				    		<td><%= request.getAttribute("resource") %></td>
				    	</tr>
				    	<tr>
				    		<td>Data Description</td>
				    		<td><%= request.getAttribute("description") %></td>
				    	</tr>
				    	<tr>
				    		<td>Association</td>
				    		<td><%= request.getAttribute("association") %></td>
				    	</tr>
				    	<tr>
				    		<td>Attribute type</td>
				    		<td><%= request.getAttribute("attribute") %></td>
				    	</tr>
				    	<tr>
				    		<td>Citation(s)</td>
				    		<td><%= request.getAttribute("citation") %></td>
				    	</tr>
				    	<tr>
				    		<td>Created</td>
				    		<td><%= request.getAttribute("download_date") %></td>
				    	</tr>
				    </table>
				    <table class="table">
				    	<caption>Downloads</caption>
				    	<tr>
				    		<% Set<Download> downloads = (Set<Download>) request.getAttribute("downloads");
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
				    						<span class="glyphicon <%= glypiconType %>" aria-hidden="true"></span>
				    					</a>
				    				</td>
				    				<td><%= dl.getCount() %></td>
				    			</tr>
				    		<% } %>
				    	</tr>
				    </table>
				</section>
				<!-- End dataset content -->

			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>