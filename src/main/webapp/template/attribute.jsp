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
				<h1 data-entity-name="${name}">${name} <span class="note">Attribute</span></h1>
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
					<h2>Genes by dataset</h2>
					<p class="instruction">Click on a dataset to see genes for ${name}.</p>
					<table class="table entities-by-dataset attributes">
						<thead>
							<tr>
								<th></th>
								<th>Dataset</th>
								<th>Download gene set</th>
								<th>Downstream analysis tools</th>
							</tr>
						</thead>
						<% @SuppressWarnings("unchecked")
						List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>> genesByDataset = (List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>>) request.getAttribute("genesByDataset");
						for (Pair<Dataset, Pair<List<Gene>, List<Gene>>> pair : genesByDataset) {
							Dataset dataset = pair.getLeft();
							Pair<List<Gene>, List<Gene>> genes = pair.getRight();
							String datasetName = dataset.getName();
							String datasetURL = URLCodec.encode(datasetName);
							String className = StringUtils.join(datasetName.replace(",", "").split(" "), "-");
						%>
							<tr class="dataset-row <%= className %>">
								<td class="col-sm-1" data-dataset-group='<%= className %>'">
									<button class="btn btn-default glyphicon glyphicon-plus cursor-pointer" aria-hidden="true" ></button>
									<button class="btn btn-default glyphicon glyphicon-minus hidden cursor-pointer" aria-hidden="true"></button>
								</td>
								<td class="col-sm-4">
									<a href="dataset/<%= datasetURL %>"><%= datasetName %></a>
									<span class="badge"><%= genes.getLeft().size() + genes.getRight().size() %> genes</span>
								</td>
								<td class="col-sm-4">
									<div class="tool-tip">
										<button class="btn btn-default glyphicon glyphicon-download-alt" aria-hidden="true"></button>
										<span class="tool-tip-text">Download the gene set as a plain text, newline separated list of genes.</span>
									</div>
								</td>
								<td class="col-sm-3 tools">
									<div class="tool enrichr">
										<div class="tool-tip">
											<img src="http://amp.pharm.mssm.edu/Enrichr/images/enrichr.png">
											<span class="tool-tip-text">Perform enrichment analysis against over 70 gene set libraries with Enrichr, a popular gene set enrichment analysis tool.</span>
										</div>
									</div>
								</td>
							</tr>
							<tr class="entity-list">
								<td colspan="5">
									<div class="first">
										<% Iterator<Gene> posIter = genes.getLeft().iterator();
										if (posIter.hasNext()) { %>
											Up:
											<% while (posIter.hasNext()) {
												Gene gene = posIter.next();
												String symbol = gene.getSymbol();
											%>
												<a href="gene/<%= URLCodec.encode(symbol) %>" data-gene="<%= symbol %>">
													<%= symbol %></a><% if (posIter.hasNext()) { %>, <% } %>
											<% }
										} %>
									</div>
									<div>
										<% Iterator<Gene> negIter = genes.getRight().iterator();
										if (negIter.hasNext()) { %>
											Down:
											<% while (negIter.hasNext()) {
												Gene gene = negIter.next();
												String symbol = gene.getSymbol();
											%>
												<a href="gene/<%= URLCodec.encode(symbol) %>"><%= symbol %></a><% if (negIter.hasNext()) { %>, <% } %>
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