<!DOCTYPE HTML>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.URLEncoder" %>
<html>
	<head>
		<%@include file="common_includes.html"%>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js" charset="utf-8"></script>
		<script src="script/genePage.js"></script>
		<script>
			loadSummaryMatrix("<%= request.getAttribute("symbol") %>");
		</script>
	</head>
	<style>
		/* prevents horizontal scrolling */
html {
  /*min-width: 1040px;*/
  max-width: 100%;
  overflow-x: hidden;
}


h1 {
  font-size: 45px;
  font-weight: 300;
  margin-top: 10px;
  margin-bottom: 20px;
}

h2 {
  margin-top: 2em;
}


/*grey background*/
.background {
  fill: #eee;
}

line {
  stroke: white; 
}

text.active {
  font-weight: bold;
}

/*position the title of the columns and rows*/
#col_title{
  font-size: 20px;
  display: block;
  margin-left:140px;
  margin-top:2px;
  font-weight: 300;
}

#row_title{
  font-size:20px;
  display: block;
  transform:rotate(-90deg );
  float:left;
  /*this is sensitive to the length of the 
  row title*/
  margin-left: -13px;
  margin-top: 300px;
  font-weight: 300;
}

#svg_div{
  /*margin-top: 0px;*/
  /*margin-left: 50px;*/
  position:absolute;
  top:0px;
  left:25px;
  /*position:fixed;*/
  /*float:left;*/
}

#main_svg{
  /*position:fixed;*/
  /*top:100px;
  left:280px;*/
  /*need to reduce this from -47px
  not sure why*/
  margin-left: 0px;
  font-size:8.0px;
  display:inline-block;
 }

/*try to get auto height*/
#wrapper{
  height:100vh;
}

#main_container{
  position: relative;
  margin-left: 30px;
  /*!! need to improve this*/
  /*width:100%;*/
  /*height:1000px;*/
}

#initial_container{
  position:relative;
}

#clust_and_row_container{
  /*so that the child div can be positioned absolutely*/
  position: relative;
}

/*the viz container is wider
than the initial container 
because the sidebar will be closed*/
#viz_container{
  position: relative;
  /*!! this constraints the width of svg*/
  /*width: 80%;*/
  /*background-color: blue;*/
}

#clustergram_container{
  display: block;
  border-style: solid;
  border-width: 3px;
  border-color: #f5f5f5;
  margin-top: 10px;
  /*set height here so that it will 
  look normal with out clustergram*/
  /*height: 950px;*/
  margin-left: 260px;
}

#clust_instruct_container{
  display: block;
  padding-left: 10px;
  border-left: 0px;
  /*border-style: solid;*/
  /*border-width: 5px;*/
  /*border-color: #f5f5f5;*/
  /*set up fixed positions*/
  position:absolute;
  width:245px;

}

#website_title{
  margin-top: 20px;
  display: none;
}

#gene_input_box{
  height:200px;
  width: 75%;
  margin: 0 auto;
  position:relative;
  display:block;
  resize:none;
  font-size: 15px;
  font-weight: 300;
  padding-left: 6px;
}

#title_input_gene_list{
  width:99%;
  margin: 0 auto;
  color:white;
  text-align: center;
  margin-top: 32px;
  margin-bottom: 10px;
  font-size: 22px;
  font-weight: 300;
}

/*title for instructions*/
#title_viz_instruct_text{
  width:99%;
  margin: 0 auto;
  color:#333;
  text-align: left;
  margin-top: 5px;
  padding-top:5px;
  margin-bottom: 5px;
  font-size: 22px;
  font-weight: 300;
  /*clear float left*/
  clear:both;
}

/* the instructions for the visualization should
look like the input gene list text title*/
.viz_instruct_text{
  width:99%;
  margin: 0 auto;
  color:#333;
  text-align: left;
  margin-top:10px;
  margin-bottom: 10px;
  font-size: 12px;
  font-weight: 300;
}

.initial_paragraph{
  font-size: 20px;
  width:590px;
  font-weight: 300;
  display: none;
}


#title_gmt_menu{
  display: none;
  color:white;
  padding-top:12px;
  padding-bottom:12px;
  padding-left:20px;
  width:410px;
  text-align: left; 
  background: #333333;  
  font-size: 22px;
  font-weight: 300;
}

.h_top{
  padding-left:20px;
  width:410px;
  text-align: left; 
  background: #8C8C8C;
  font-size: 17px;
  font-weight: 300;
  border-radius: 0;
  opacity: 0;
}

.h_medium{
  padding-left:30px;
  width:410px;
  text-align: left; 
  background: #D0D0D0;
  border-radius: 0;
  font-size: 15px;
  font-weight: 300;
  padding-top: 0px;
  padding-bottom: 0px;
}

#gmt_menu{
  display: none;
  margin-top: 20px;
  margin-left: 0px;
  font-size: 15px;
  font-weight: 300;
}

.buttons{
  font-size: 15px;
  font-weight: 300;

}

.glyph_squares{
  float: left;
  margin-right: 10px;
  margin-top: 0px;
}

#submit_genes_button{
  width:141px;
  margin-bottom: 10px;
}

/*clear button*/
#clear_input_button{
  opacity: 0.90;
}

/*return to previous enrichrgram button*/
#return_prev_button{
  width: 140px;
  margin-top: 40px;
  opacity: 0.9;
}

/*modify enrichrgram*/
#modify_enrichrgram_button{
  opacity: 1.0;
}

.form-group{
  margin-bottom: 10px;
}

/*footer - shift it with the sidebar*/
#footer_div{
  margin-left: 0px;
}

/*title for the visualization*/
#viz_website_title{
  font-size: 33px;
}

/*container for gene search box*/
#gene_search_container{
  margin-top: 10px;
  /*needed for some reason*/
  margin-left: 0px;
  display: none:;
}

/*gene search box */
#gene_search_box{
  width:120px;
  /*float:left;*/
  /*display: none;*/
}

/*gene search button*/
#gene_search_button{
  /*width:50px;*/
  /*float:left;*/
  margin-top:-2px;
  /*display: none;*/
}

/*clear float left*/
#clear{
  clear:both;
}

/*fizing jquery autocomplete*/
.ui-menu-item{
  float:left;
  clear: left;
}

/*move down submit button*/
#gene_search_submit{
  margin-top:5px;
}

#toggle_order{
  margin-top: 10px;
}
		
	
	</style>
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
			
			
			<%@include file="footer.html"%>
		</div>
	</body>
</html>