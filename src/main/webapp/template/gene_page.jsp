<!DOCTYPE HTML>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.URLEncoder" %>
<html>
	<head>
		<%@include file="common_includes.html"%>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h3>${symbol} <span class="note">${note}</span></h3>
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
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>