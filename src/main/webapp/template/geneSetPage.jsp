<!DOCTYPE HTML>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang3.tuple.Pair" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Gene" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper attribute-page">
			<div class="content container">
				<h1><em>${attributeName}</em> ${attributeType} from <em>${datasetName}</em> <span class="note">Gene Set</span></h1>
				<section>
					<table class="table">
						<% if (request.getAttribute("datasetDescription") != "") { %>
							<tr>
								<td class="col-sm-3">Dataset Description</td>
								<td class="col-sm-9">${datasetDescription}</td>
							</tr>
						<% } %>
						<% if (request.getAttribute("attributeDescription") != "") { %>
							<tr>
								<td class="col-sm-3">Attribute Description</td>
								<td class="col-sm-9">${attributeDescription}</td>
							</tr>
						<% } %>
						<tr>
							<% String ag = (String) request.getAttribute("attributeGroup"); %>
							<td class="col-sm-3">Attribute group</td>
							<td class="col-sm-9">
								<a href="attribute_group/<%= URLCodec.encode(ag) %>"><%= ag %></a>
							</td>
						</tr>
						<tr>
							<% String at = (String) request.getAttribute("attributeType"); %>
							<td class="col-sm-3">Attribute type</td>
							<td class="col-sm-9">
								<a href="attribute_type/<%= URLCodec.encode(at) %>"><%= at %></a>
							</td>
						</tr>
						<tr>
							<% NamingAuthority na = (NamingAuthority) request.getAttribute("namingAuthority"); %>
							<td class="col-sm-3">Naming authority</td>
							<td class="col-sm-9">
								<% if (na.getUrl() != "") { %>
									<a href="<%= na.getUrl() %>" target="_blank"><%= na.getName() %></a>
								<% } else { %>
									<%= na.getName() %>
								<% } %>
							</td>
						</tr>
						<% if (request.getAttribute("idFromNamingAuthority") != "") { %>
							<tr>
								<td class="col-sm-3">ID from naming authority</td>
								<td class="col-sm-9">${idFromNamingAuthority}</td>
							</tr>
						<% }
						if (request.getAttribute("url") != "") { %>
							<tr>
								<td class="col-sm-3">URL</td>
								<td class="col-sm-9"><a href="${url}" target="_blank">${url}</a></td>
							</tr>
						<% } %>
					</table>
				</section>
				<section>
					<% @SuppressWarnings("unchecked")
					List<Gene> upGenes = (List<Gene>) request.getAttribute("upGenes"); %>
					<h2>Genes <span class="badge"><%= upGenes.size() %></span></h2>
					<table class="table data-table">
						<thead>
							<tr>
								<th>Symbol</th>
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
						<% for (Gene gene : upGenes) { %>
							<tr>
								<td><a href="gene/<%= URLCodec.encode(gene.getSymbol()) %>"><%= gene.getSymbol() %></a></td>
								<td><%= gene.getName() %></td>
							</tr>
						<% } %>
						</tbody>
					</table>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>