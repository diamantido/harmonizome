<!DOCTYPE HTML>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.URLEncoder" %>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
		<link rel="stylesheet" href="style/css/gene.css">
		<script src="script/gene.js"></script>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container">
				<h3>${symbol} <span class="note">${note}</span></h3>
				<section>
					<h4>Metadata</h4>
					<table class="table row">
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
									<a href="hgnc_root_family/<%= URLEncoder.encode(hgncRootFamilies[i]) %>">
									<%= hgncRootFamilies[i] %></a><%= i != hgncRootFamilies.length-1 ? "," : "" %>
								<% } %>
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">HGNC Terminal Families</td>
							<td class="col-sm-9">
								<% String[] hgncTerminalFamilies = (String[]) request.getAttribute("hgncTerminalFamilies");
								for (int i = 0; i < hgncRootFamilies.length; i++) { %>
									<a href="hgnc_terminal_family/<%= URLEncoder.encode(hgncTerminalFamilies[i]) %>">
									<%= hgncTerminalFamilies[i] %></a><%= i != hgncTerminalFamilies.length-1 ? "," : "" %>
								<% } %>
							</td>
						</tr>
					</table>
				</section>
				<section>
					<h4>Attributes</h4>
					<table class="table row attributes">
						<% @SuppressWarnings("unchecked")
						Map<String, Map<String, List<Attribute>>> organizedAttributes = (Map<String, Map<String, List<Attribute>>>) request.getAttribute("organizedAttributes");
						for (Map.Entry<String, Map<String, List<Attribute>>> groups : organizedAttributes.entrySet()) {
							String agName = groups.getKey();
							String className = StringUtils.join(agName.replace(",", "").split(" "), "-");
						%>
							<tr>
								<td class="col-sm-3">
									<%= agName %>
								</td>
								<td class="types col-sm-9" onclick="showAttributesByGroup('<%= className %>')">
									<% Map<String, List<Attribute>> attributesByType = groups.getValue();
									for (Map.Entry<String, List<Attribute>> types : attributesByType.entrySet()) {
										String atName = types.getKey();
									%>
										<%= atName %>
									<% } %>
								</td>
							</tr>
							<tr class="<%= className %> attributes-data hidden">
								<td class="col-sm-3"></td>
								<td class="col-sm-9">
									<% Map<String, List<Attribute>> attributesByType2 = groups.getValue();
									for (Map.Entry<String, List<Attribute>> types : attributesByType2.entrySet()) {
										String atName = types.getKey();
										List<Attribute> attributes = types.getValue();
										Iterator<Attribute> iter = attributes.iterator();
										
										while (iter.hasNext()) {
											String name = iter.next().getName();
										%>
											<a href="attribute/<%= URLEncoder.encode(name) %>">
												<%= name %><% if (iter.hasNext()) { %>, <% } %>
											</a>
										<% }
									} %>
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