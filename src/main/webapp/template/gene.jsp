<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang3.tuple.Pair" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%
@SuppressWarnings("unchecked")
List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> attributesByDataset = (List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>>) request.getAttribute("attributesByDataset");
%>

<!DOCTYPE HTML>
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
				<h1>${symbol} <span class="note">${note}</span></h1>
				<section>
					<table class="table">
						<tr>
							<td class="col-sm-3">Families (from HGNC)</td>
							<td class="col-sm-9">
								<% String[] hgncRootFamilies = (String[]) request.getAttribute("hgncRootFamilies");
								for (int i = 0; i < hgncRootFamilies.length; i++) { %>
									<a href="hgnc_root_family/<%= URLCodec.encode(hgncRootFamilies[i]) %>">
									<%= hgncRootFamilies[i] %></a><%= i != hgncRootFamilies.length-1 ? "," : "" %>
								<% } %>
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">Name</td>
							<td class="col-sm-9">${name}</td>
						</tr>
						<tr>
							<td class="col-sm-3">Description (from NCBI)</td>
							<td class="col-sm-9">${description}</td>
						</tr>
						<tr>
							<td class="col-sm-3">Synonyms</td>
							<td class="col-sm-9">${synonyms}</td>
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
							<td class="col-sm-3">External Link</td>
							<td class="col-sm-9"><a href="${ncbiEntrezGeneUrl}" target="_blank">National Center for Biotechnology Information (NCBI)</a></td>
						</tr>
					</table>
				</section>
				<section>
					<h2>Knowledge</h2>
					<p class="instruction">Click the + buttons to view associations for ${symbol} from each dataset.</p>
					<table class="table entities-by-dataset genes">
						<thead>
							<tr>
								<th></th>
								<th>Dataset</th>
								<th>Summary</th>
								<th>Downloads</th>
							</tr>
						</thead>
						<%
						for (Pair<Dataset, Pair<List<Attribute>, List<Attribute>>> pair : attributesByDataset) {
							Dataset dataset = pair.getLeft();
							Pair<List<Attribute>, List<Attribute>> attributes = pair.getRight();
							String datasetName = dataset.getName();
							String datasetURL = URLCodec.encode(datasetName);
							String className = StringUtils.join(datasetName.replace(",", "").split(" "), "-");
							String symbol = (String) request.getAttribute("symbol");
							String attributeSetDescription = dataset.getAttributeSetDescription().replace("{0}", symbol);
						%>
							<tr class="dataset-row <%= className %>">
								<td class="col-md-1" data-dataset-group="<%= className %>">
									<button class="btn btn-default glyphicon glyphicon-plus cursor-pointer" aria-hidden="true"></button>
									<button class="btn btn-default glyphicon glyphicon-minus hidden cursor-pointer" aria-hidden="true"></button>
								</td>
								<td class="col-md-3">
									<a href="dataset/<%= datasetURL %>"><%= dataset.getName() %></a>
								</td>
								<td class="col-md-6">
									<%= attributes.getLeft().size() + attributes.getRight().size() + " " + attributeSetDescription %>
								</td>
								<td class="col-md-2">
								</td>
							</tr>
							<tr class="list attribute-list">
								<td class="col-md-1"></td>
								<td class="col-md-9" colspan="2">
									<div>
										<% Iterator<Attribute> posIter = attributes.getLeft().iterator();
										if (posIter.hasNext()) { %>
											<div><strong><%= StringUtils.capitalize(dataset.getPositiveAssociation()) %></strong></div>
											<% while (posIter.hasNext()) {
												Attribute attribute = posIter.next();
												String attributeName = attribute.getNameFromDataset();
											%>
												<a href="gene_set/<%= URLCodec.encode(attributeName) %>/<%= URLCodec.encode(datasetName) %>">
													<%= attributeName %></a><% if (posIter.hasNext()) { %>, <% } %>
											<% }
										} %>
									</div>
									<% Iterator<Attribute> negIter = attributes.getRight().iterator();
									if (negIter.hasNext()) { %>
									<div class="last">
										<div><strong><%= StringUtils.capitalize(dataset.getNegativeAssociation()) %></strong></div>
										<% while (negIter.hasNext()) {
											Attribute attribute = negIter.next();
											String name = attribute.getNameFromDataset();
										%>
											<a href="attribute/<%= URLCodec.encode(name) %>"><%= name %></a><% if (negIter.hasNext()) { %>, <% } %>
										<% } %>
									</div>
									<% } %>
								</td>
								<td class="col-md-2"></td>
							</tr>
						<% } %>
					</table>				
				</section>
			</div>
			<%@include file="footer.html" %>
		</div>
	</body>
</html>