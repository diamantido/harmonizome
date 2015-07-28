<!DOCTYPE HTML>
<%@ page import="java.util.Iterator" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Publication" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Resource" %>
<%
Resource resource = (Resource) request.getAttribute("resource");
%>

<html>
	<head>
		<%@include file="globalIncludes.html" %>
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h1><%= resource.getName() %> <span class="note">Resource</span></h1>
				<table class="table">
					<tr>
						<td class="col-sm-3">Description</td>
						<td class="col-sm-9"><%= resource.getLongDescription() %></td>
					</tr>
					<tr>
						<td class="col-sm-3">Datasets</td>
						<td class="col-sm-9">
							<ul class="list-unstyled">
							<% for (Dataset dataset : resource.getDatasets()) { %>
								<li>
									<a href="dataset/<%= URLCodec.encode(dataset.getName()) %>"><%= dataset.getName() %></a>
								</li>
							<% } %>
							</ul>
						</td>
					</tr>
					<tr>
						<td class="col-sm-3">Citation(s)</td>
						<td class="col-sm-9">
						<% 
						Iterator<Publication> pubIter = resource.getPublications().listIterator();
						while (pubIter.hasNext()) {
							Publication pub = pubIter.next();
						%>
							<a href="<%= pub.getPubmedUrl() %>"><%= pub.getLongCitation() %></a>
							<% if (pubIter.hasNext()) { %>, <% } %>
						<% } %>
						</td>					
					</tr>
					<tr>
						<td class="col-sm-3">External Link</td>
						<td class="col-sm-9">
							<a href="<%= resource.getUrl() %>" target="_blank">
								<%= resource.getUrl() %>
							</a>
						</td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>