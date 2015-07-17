<%@ page import="java.util.List" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Gene" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Ellipsizer" %>
<%
@SuppressWarnings("unchecked")
List<Dataset> datasets = (List<Dataset>) request.getAttribute("datasets");
@SuppressWarnings("unchecked")
List<Gene> genes = (List<Gene>) request.getAttribute("genes");
@SuppressWarnings("unchecked")
List<Attribute> attributes = (List<Attribute>) request.getAttribute("attributes");

int MAX_DESCRIPTION_LENGTH = 200;
%>

<!DOCTYPE html>
<html lang="en">
    <head>
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
			<div class="content container-full">
				<div class="container">
					<div class="col-md-12">
						<h1>Search results</h1>
						<p>
							<span class="badge dataset"><%= datasets.size() %> datasets</span>
							<span class="badge gene"><%= genes.size() %> genes</span>
							<span class="badge attribute"><%= attributes.size() %> gene sets</span>
						</p>
					</div>
					<div class="col-md-12">
						<table class="table data-table">
							<thead>
								<tr>
									<th class="col-md-2">Search Result Type</th>
									<th class="col-md-10">Name</th>
								</tr>
							</thead>
							<tbody>
								<% if (datasets.size() != 0) { %>
									<tr>
										<td class="col-md-2">
											<strong><em>Datasets</em></strong>
										</td>
										<td class="col-md-10"></td>
									</tr>
								<% for (Dataset dataset : datasets) { 
									String name = dataset.getName();
								%>
										<tr>
											<td class="col-md-2"></td>
											<td class="col-md-10">
												<strong class="badge dataset">
													<a href="dataset/<%= URLCodec.encode(name) %>"><%= name %></a>
												</strong>
												<span class="badge">
													<a href="<%= dataset.getResource().getUrl() %>" target="_blank"><%= dataset.getResource().getName() %></a>
												</span>
												<span class="badge"><%= dataset.getDatasetType().getName() %></span>
												<p class="search-description"><%= Ellipsizer.trim(dataset.getDescription(), MAX_DESCRIPTION_LENGTH) %></p>
											</td>
										</tr>
									<% }
								}
								if (genes.size() != 0) { %>
									<tr>
										<td class="col-md-2">
											<strong><em>Genes</em></strong>
										</td>
										<td></td>
									</tr>
									<% for (Gene gene : genes) {
										String symbol = gene.getSymbol();
									%>
										<tr>
											<td class="col-md-2"></td>
											<td class="col-md-10">
												<strong class="badge gene">
													<a href="gene/<%= URLCodec.encode(symbol) %>"><%= symbol %></a>
												</strong>
												<% if (gene.getIdgFamily() != null) { %>
													<span class="badge"><%= gene.getIdgFamily().getName() %></span>
												<% } %>
												<p class="search-description"><%= Ellipsizer.trim(gene.getDescription(), MAX_DESCRIPTION_LENGTH) %></p>
											</td>
										</tr>
									<% }
								}
								if (attributes.size() != 0) { %>
									<tr>
										<td class="col-md-2">
											<strong><em>Gene Sets</em></strong>
										</td>
										<td></td>
									</tr>
									<% for (Attribute attribute : attributes) {
									String name = attribute.getName();
									%>
										<tr>
											<td class="col-md-2"></td>
											<td class="col-md-10">
												<strong class="badge attribute">
													<a href="attribute/<%= URLCodec.encode(name) %>"><%= name %></a>
												</strong>
												<span class="badge"><%= attribute.getAttributeType().getName() %></span>
												<p class="search-description"><%= Ellipsizer.trim(attribute.getDescription(), MAX_DESCRIPTION_LENGTH) %></p>
											</td>
										</tr>
									<% }
								} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>