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
			
			<div id="wrapper" class='toggled'>

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <!-- Initial Content --> 
      <div id='main_container'>

        <!-- this row is required, -fluid causes problems --> 
        <div class="row" > 

          <div class="col-lg-12">

            <!-- visualization -->
            <div id='viz_container'>

              <div class='row'>
                <div id='clust_instruct_container' >
                  <h1 id='viz_website_title'>D3 Clustergram</h1>

                  <div id='viz_gmt_labels'></div>

                  <div id='title_viz_instruct_text'>About:</div>
                  <div class='viz_instruct_text'> Zoom into the clustergram using scroll. Reorder the clustergram using the toggle buttons or by clicking rows or columns. Double-click to programatically reset zoom/panning.  Search for a gene to zoom into the matrix. </div>
                  <div class='viz_instruct_text'> D3 Clustergram is developed by Nick Fernandez at the <a href="http://icahn.mssm.edu/">Icahn School of Medicine at Mount Sinai</a> in the <a href="http://icahn.mssm.edu/research/labs/maayan-laboratory">Ma'ayan Lab</a> a <a href="http://lincs-dcic.org/">DCIC BD2K-LINCS</a> center. </div>

                  <!-- input box for gene search -->  
                  <div id='gene_search_container' class='row'>
                      <input id='gene_search_box' type="text" class="form-control" placeholder="Input Gene" aria-describedby="sizing-addon2">

                      <div id='gene_search_button' class="btn-group" data-toggle="buttons" >
                        <label id='gene_search_submit' class="btn btn-primary active " onclick="find_gene_in_clust();">
                          <input type="radio" name="options" id="" autocomplete="off" checked > Search
                        </label>
                      </div>
                  </div>

                  <div id='toggle_order' class="btn-group" data-toggle="buttons" >
                      <label class="btn btn-primary active prot_class" onclick="reorder_clust_rank('clust');">
                        <input type="radio" name="options" id="clust_button" autocomplete="off" checked > Cluster
                      </label>
                      <label class="btn btn-primary prot_class" onclick="reorder_clust_rank('rank');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Rank
                      </label>
                    </div>
                  </div>
                </div>

                <div id='clustergram_container' >
                  <div id='col_title'>Columns</div>
                  <div id='clust_and_row_container'>
                    <div id='row_title'>Rows</div>
                    <div id='svg_div'></div>
                  </div>
                </div>
              </div>
            </div>
            <!-- end visualization -->

          </div>
        </div> <!-- end row -->
      </div>
    </div>
			
			
			<%@include file="footer.html" %>
        </div>
    </body>
</html>