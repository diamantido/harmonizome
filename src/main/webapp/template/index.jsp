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
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery.dotdotdot/1.7.3/jquery.dotdotdot.min.js"></script>
        <script>
        	$(document).ready(function() {
        		$(".resource .description").dotdotdot();
        	});
        </script>-->
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<%--<%@include file="search_bar.html" %>--%>
			<div class="content container-full">
				<div class="container">
					<div class="row">
						<h1 class="col-sm-12 text-center">The Harmonizome</h1>
						<h3 class="col-sm-12 text-center">Search a collection of processed data about genes and proteins from over 100 datasets provided by over 50 online resources.</h3>
						<p class="col-sm-12 text-center">
							Thanks to technological advances in genomics, transcriptomics, proteomics, metabolomics, and related fields, projects that generate a large number of measurements of the properties of cells, tissues, model organisms, and patients are becoming commonplace in biomedical research. In addition, curation projects are making great progress mining biomedical literature to extract and aggregate decades worth of research findings into online databases. Such projects are generating a wealth of information that potentially can guide research toward novel biomedical discoveries and advances in healthcare. To facilitate access to and learning from biomedical Big Data, we created the Harmonizome: a collection of information about genes and proteins from over 100 datasets provided by over 50 online resources.
						</p>
					</div>
					<div class="row">
						<%
						List<Resource> resources = (List<Resource>) request.getAttribute("resources");
						//Collections.sort(resources, new ResourceComparator());
						for (Resource r : resources) { 
							String description = r.getShortDescription();
						%>
							<div class="col-sm-4">
								<div class="resource">
									<h4>
										<a href="<%= r.getUrl() %>" target="_blank"><%= r.getName() %></a>
										<% String image = r.getImage();
										if (image != "") { %>
											<div class="logo pull-right">
												<img src="image/resource/<%= r.getImage() %>">
											</div>
										<% } %>
										<%--<span class="count"><%= r.getDatasets().size() dataset(s)</span>--%>
									</h4>
									<p class="description"><%= StringUtils.capitalize(description) %></p>
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

					
					
					
					
					<!--<div id="intro" class="row">
					    <div class="col-sm-6">
					        <div class="portal pull-left">
					            <h3>Browse datasets</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="dataset/">
					                <button>Browse</button>
					            </a>
					        </div>
					    </div>
					    <div class="col-sm-6">
					        <div class="portal pull-right">
					            <h3>Search data</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="search">
					                <button>Search</button>
					            </a>
					        </div>
					    </div>
					</div>-->
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>