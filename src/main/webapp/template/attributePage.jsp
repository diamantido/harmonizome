<!DOCTYPE HTML>
<%@ page import="java.net.URLEncoder" %>
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
<html>
	<head>
		<%@include file="commonIncludes.html" %>
		<link rel="stylesheet" href="style/css/show.css">
		<script src="script/show.js"></script>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h1>${name} <span class="note">Attribute</span></h1>
				<section>
					<table class="table">
						<% if (request.getAttribute("description") != "") { %>
							<tr>
								<td class="col-sm-3">Description</td>
								<td class="col-sm-9">${description}</td>
							</tr>
						<% } %>
						<tr>
							<% String ag = (String) request.getAttribute("attributeGroup"); %>
							<td class="col-sm-3">Attribute group</td>
							<td class="col-sm-9">
								<a href="attribute_group/<%= URLEncoder.encode(ag, "UTF-8") %>"><%= ag %></a>
							</td>
						</tr>
						<tr>
							<% String at = (String) request.getAttribute("attributeType"); %>
							<td class="col-sm-3">Attribute type</td>
							<td class="col-sm-9">
								<a href="attribute_type/<%= URLEncoder.encode(at, "UTF-8") %>"><%= at %></a>
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
					<h2>Genes by dataset</h2>
					<p class="instruction">Click on a dataset to see genes for ${name}.</p>
					<table class="table attributes">
						<thead>
							<tr>
								<th></th>
								<th>Dataset</th>
							</tr>
						</thead>
						<% @SuppressWarnings("unchecked")
						List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>> genesByDataset = (List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>>) request.getAttribute("genesByDataset");
						for (Pair<Dataset, Pair<List<Gene>, List<Gene>>> pair : genesByDataset) {
							Dataset dataset = pair.getLeft();
							Pair<List<Gene>, List<Gene>> attributes = pair.getRight();
							String datasetName = dataset.getName();
							String datasetURL = URLEncoder.encode(datasetName, "UTF-8");
							String className = StringUtils.join(datasetName.replace(",", "").split(" "), "-");
						%>
							<tr class="<%= className %>">
								<td class="col-sm-1">
									<span class="glyphicon glyphicon-plus cursor-pointer" aria-hidden="true" onclick="showByGroup('<%= className %>')"></span>
									<span class="glyphicon glyphicon-minus hidden cursor-pointer" aria-hidden="true" onclick="showByGroup('<%= className %>')"></span>
								</td>
								<td class="col-sm-11">
									<a href="dataset/<%= datasetURL %>"><%= datasetName %></a>
								</td>
							</tr>
							<tr class="item-list">
								<td colspan="5">
									<div class="first">
										<% Iterator<Gene> posIter = attributes.getLeft().iterator();
										if (posIter.hasNext()) { %>
											Up:
											<% while (posIter.hasNext()) {
												Gene gene = posIter.next();
												String symbol = gene.getSymbol();
											%>
												<a href="gene/<%= URLEncoder.encode(symbol, "UTF-8") %>">
													<%= symbol %></a><% if (posIter.hasNext()) { %>, <% } %>
											<% }
										} %>
									</div>
									<div>
										<% Iterator<Gene> negIter = attributes.getRight().iterator();
										if (negIter.hasNext()) { %>
											Down:
											<% while (negIter.hasNext()) {
												Gene gene = negIter.next();
												String symbol = gene.getSymbol();
											%>
												<a href="gene/<%= URLEncoder.encode(symbol, "UTF-8") %>"><%= symbol %></a><% if (negIter.hasNext()) { %>, <% } %>
											<% }
										} %>
									</div>
								</td>
							</tr>
						<% } %>
					</table>
				</section>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>