<!DOCTYPE html>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Resource" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.ResourceComparator" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="common_includes.html" %>
        <link rel="stylesheet" type="text/css" href="/Harmonizome/style/css/index.css">
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<%--<%@include file="search_bar.html" %>--%>
			<div class="content container-full">
				<div class="container">
					<div class="row">
						<h3 class="col-sm-12 text-center">Search a collection of processed data about genes and proteins from over 100 datasets provided by over 50 online resources.</h3>
						<p class="col-sm-12 text-center">
							Thanks to technological advances in genomics, transcriptomics, proteomics, metabolomics, and related fields, projects that generate a large number of measurements of the properties of cells, tissues, model organisms, and patients are becoming commonplace in biomedical research. In addition, curation projects are making great progress mining biomedical literature to extract and aggregate decades worth of research findings into online databases. Such projects are generating a wealth of information that potentially can guide research toward novel biomedical discoveries and advances in healthcare. To facilitate access to and learning from biomedical Big Data, we created the Harmonizome: a collection of information about genes and proteins from over 100 datasets provided by over 50 online resources.
						</p>
					</div>
					<div class="row">
						<% @SuppressWarnings("unchecked")
						List<Resource> resources = (List<Resource>) request.getAttribute("resources");
						//Collections.sort(resources, new ResourceComparator());
						for (Resource r : resources) { 
							String description = r.getShortDescription();
						%>
							<div class="col-sm-3">
								<div class="resource">
									<h4>
										<a href="<%= r.getUrl() %>" target="_blank"><%= r.getName() %></a>
										<span class="count"><%= r.getDatasets().size() %> dataset(s)</span>
									</h4>
									<div class="labels">
										<% Set<String> categories = new TreeSet<String>();
										for (Dataset ds : r.getDatasets()) {
											String name = ds.getDatasetGroup().getName();
											categories.add(name);
										}
										for (String cat : categories) { %>
											<span class="label <%= StringUtils.join(StringUtils.split(cat), "-") %>">
												<%= StringUtils.capitalize(cat) %>
											</span>
										<% } %>
									</div>
								</div>
							</div>
						<% } %>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>