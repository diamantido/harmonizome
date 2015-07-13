<!DOCTYPE HTML>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang3.tuple.Pair" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
		<script src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
		<link rel="stylesheet" href="style/css/show.css">
		<script src="script/show.js"></script>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<h1>${symbol} <span class="note">${note}</span></h1>
				<section>
					<table class="table">
						<tr>
							<td class="col-sm-3">Name</td>
							<td class="col-sm-9">${name}</td>
						</tr>
						<tr>
							<td class="col-sm-3">Description</td>
							<td class="col-sm-9">${description}</td>
						</tr>
						<tr>
							<td class="col-sm-3">Synonyms</td>
							<td class="col-sm-9">${synonyms}</td>
						</tr>
						<tr>
							<td class="col-sm-3">NCBI Entrez Gene URL</td>
							<td class="col-sm-9"><a href="${ncbiEntrezGeneUrl}" target="_blank">${ncbiEntrezGeneUrl}</a></td>
						</tr>
						<tr>
							<td class="col-sm-3">Proteins</td>
							<td class="col-sm-9">
								<% String[] proteins = (String[]) request.getAttribute("proteins");
								for (int i = 0; i < proteins.length; i++) { %>
									<a href="protein/<%= proteins[i] %>"><%= proteins[i] %></a><%= i != proteins.length-1 ? "," : "" %>
								<% } %>
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">IDG Family</td>
							<td class="col-sm-9">${idgFamily}</td>
						</tr>
						<tr>
							<td class="col-sm-3">IDG TDL Class</td>
							<td class="col-sm-9">${idgTdlClass}</td>
						</tr>
						<tr>
							<td class="col-sm-3">HGNC Root Families</td>
							<td class="col-sm-9">
								<% String[] hgncRootFamilies = (String[]) request.getAttribute("hgncRootFamilies");
								for (int i = 0; i < hgncRootFamilies.length; i++) { %>
									<a href="hgnc_root_family/<%= URLEncoder.encode(hgncRootFamilies[i], "UTF-8") %>">
									<%= hgncRootFamilies[i] %></a><%= i != hgncRootFamilies.length-1 ? "," : "" %>
								<% } %>
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">HGNC Terminal Families</td>
							<td class="col-sm-9">
								<% String[] hgncTerminalFamilies = (String[]) request.getAttribute("hgncTerminalFamilies");
								for (int i = 0; i < hgncRootFamilies.length; i++) { %>
									<a href="hgnc_terminal_family/<%= URLEncoder.encode(hgncTerminalFamilies[i], "UTF-8") %>">
									<%= hgncTerminalFamilies[i] %></a><%= i != hgncTerminalFamilies.length-1 ? "," : "" %>
								<% } %>
							</td>
						</tr>
					</table>
				</section>
				<section>
					<h2>Attributes by dataset</h2>
					<p class="instruction">Click on a dataset to see attributes for ${symbol}.</p>
					<table class="table attributes">
						<thead>
							<tr>
								<th></th>
								<th>Dataset</th>
								<th>Attribute</th>
							</tr>
						</thead>
						<% @SuppressWarnings("unchecked")
						List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> attributesByDataset = (List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>>) request.getAttribute("attributesByDataset");
						for (Pair<Dataset, Pair<List<Attribute>, List<Attribute>>> pair : attributesByDataset) {
							Dataset dataset = pair.getLeft();
							Pair<List<Attribute>, List<Attribute>> attributes = pair.getRight();
							String datasetName = dataset.getName();
							String datasetURL = URLEncoder.encode(datasetName, "UTF-8");
							String className = StringUtils.join(datasetName.replace(",", "").split(" "), "-");
						%>
							<tr class="<%= className %>">
								<td class="col-sm-1">
									<span class="glyphicon glyphicon-plus cursor-pointer" aria-hidden="true" onclick="showByGroup('<%= className %>')"></span>
									<span class="glyphicon glyphicon-minus hidden cursor-pointer" aria-hidden="true" onclick="showByGroup('<%= className %>')"></span>
								</td>
								<td class="col-sm-6">
									<a href="dataset/<%= datasetURL %>"><%= dataset.getName() %></a>
								</td>
								<td class="col-sm-5">
									<%= dataset.getAttributeType().getName() %>
								</td>
							</tr>
							<tr class="item-list">
								<td colspan="5">
									<div><em><%= StringUtils.capitalize(dataset.getAttributeType().getName()) + "s:" %></em></div>
									<div class="first">
										<% Iterator<Attribute> posIter = attributes.getLeft().iterator();
										if (posIter.hasNext()) { %>
											Up:
											<% while (posIter.hasNext()) {
												Attribute attribute = posIter.next();
												String name = attribute.getName();
											%>
												<a href="attribute/<%= URLEncoder.encode(name, "UTF-8") %>">
													<%= name %></a><% if (posIter.hasNext()) { %>, <% } %>
											<% }
										} %>
									</div>
									<div>
										<% Iterator<Attribute> negIter = attributes.getRight().iterator();
										if (negIter.hasNext()) { %>
											Down:
											<% while (negIter.hasNext()) {
												Attribute attribute = negIter.next();
												String name = attribute.getName();
											%>
												<a href="attribute/<%= URLEncoder.encode(name, "UTF-8") %>"><%= name %></a><% if (negIter.hasNext()) { %>, <% } %>
											<% }
										} %>
									</div>
								</td>
							</tr>
						<% } %>
					</table>				
				</section>
			</div>
			<%@include file="footer.html" %>
		</div>
	</body>
</html>