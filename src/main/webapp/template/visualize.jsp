<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="commonIncludes.html" %>
		<link href="style/lib/simple-sidebar.css" rel="stylesheet">	
		<link rel="stylesheet" href="style/lib/bootstrap-switch.css">
		<link rel="stylesheet" href="style/lib/dc.css">
		<link rel="stylesheet" href="style/lib/jquery.accordion.css">
		<link rel="stylesheet" href="style/lib/custom.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
		
		<link rel="stylesheet" href="style/css/visualize.css">
	</head>

<body>
	<body>
		<%@include file="navbar.html"%>
		<div class="container-full">
		
		
		
				<div id="wrapper" class='toggled'>

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
      <ul class="sidebar-nav">

        <div id='title_input_gene_list'>
            Input Gene List
        </div>

        <div >
          <form role='form' action="/harmonogram/" id="searchForm">

            <div class="form-group">
              <textarea  name="genes" placeholder='input genes' id='gene_input_box' class='clearable'></textarea>
              <div id='input_genes_button_group' class='text-center' ></div>
            </div>

            <div class='text-center'>
              <button id='submit_genes_button' type="submit" class='btn btn-primary buttons' >Submit</button>
              <div class="btn-group">
                <button type="button" class='btn btn-primary buttons' onClick="$('#gene_input_box').val(example_gene_list_50.join('\n'));">Example</button>
                <button id='clear_input_button' type="button" class='btn btn-danger buttons' onClick="$('#gene_input_box').val('');" >Clear</button>
              </div>
              
            </div>

          </form>

          <div id='return_prev_enrichrgram' class='text-center' style='display:none;'>
            <button id='return_prev_button' class='btn btn-danger buttons' onClick='return_prev_enrichrgram();' >Return to Previous <br> Enrichrgram</button>
          </div>

        </div>
      </ul>
    </div>
    <!-- end sidebar -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <!-- Initial Content --> 
      <div id='main_container'>

        <!-- this row is required, -fluid causes problems --> 
        <div class="row" > 

          <div class="col-lg-12">

            <!-- initial screen -->
            <div id='initial_container'>
              <h1 id='website_title'> Enrichrgram
              </h1>
              <p class='initial_paragraph'>Enrichrgram performs enrichment analysis on your set of genes using Enrichr and displays a clustergram of the results. Choose up to three gene set libraries for enrichment analysis. </p>


              <div class='row'>
                <!-- GMT menu -->              
                <div class='col-xs-7' >
                  <section id="gmt_menu" data-accordion-group>
                    <div id='title_gmt_menu'>Choose Libraries</div>
                  </section>
                  <!-- <h2>gmt menu</h2> -->
                </div>

                <!-- Selected GMTs --> 
                <div id='container_gmt_labels' class='col-xs-5'>

                  <div id='selected_gmts_group'>
                   
                   <div id='title_selected_libraries'>
                     Selected Libraries 
                   </div> 

                    <!-- selected gmt group --> 
                    <div class='selected_gmts  ' id='current_gmt'></div>
                    <!-- end gmt group --> 

                  </div>

                  <div id='add_new_gmt' onclick="plus_new_gmt();"> + </div>

                </div>
                

            </div>
            <!-- end initial screen -->

            <!-- visualization -->
            <div id='viz_container'>

              <div class='row'>
                <div id='clust_instruct_container' >
                  <h1 id='viz_website_title' >Harmonogram</h1>

                  <div id='viz_gmt_labels'></div>

                  <div class='title_viz_instructs'>About:</div>
                  <div class='viz_instruct_text'> The clustergram visualizes gene appearance in Harmonizome resources. Genes are shown as rows and resources as columns. The gene occurance value is normalized relative to the occurance of other genes in the resource. Change the clustergram ordering by using the toggle switch or by clicking row or column labels. </div>

                  <!-- button container -->
                  <div id='button_container'>
                    
                    <!-- choose protein class  -->
                    <div id='class_buttons' class="btn-group-vertical text-center" data-toggle="buttons" >

                      <!-- <label class="btn btn-primary active" onclick="reorder_clust_rank('clust');"> -->
                      <label class="btn btn-primary active prot_class" onclick="load_class_clustergram('KIN');">
                        <input type="radio" name="options" id="clust_button" autocomplete="off" checked > Kinases
                      </label>

                      <label class="btn btn-primary  prot_class" onclick="load_class_clustergram('PP');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Phosphatases
                      </label>

                      <label class="btn btn-primary  prot_class" onclick="load_class_clustergram('GPCR');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > GPCRs
                      </label>

                      <label class="btn btn-primary prot_class" onclick="load_class_clustergram('IC');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Ion Channels
                      </label>

                      <label class="btn btn-primary prot_class" onclick="load_class_clustergram('TF');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Transcription Factors
                      </label>

                      <label class="btn btn-primary prot_class" onclick="load_class_clustergram('ACT');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Acetyltransferases
                      </label>

                      <label class="btn btn-primary prot_class" onclick="load_class_clustergram('DACT');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Deacetylases
                      </label>

                      <label class="btn btn-primary prot_class" onclick="load_class_clustergram('MET');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Methyltransferases
                      </label>

                      <label class="btn btn-primary prot_class" onclick="load_class_clustergram('DMET');" >
                        <input type="radio" name="options" id="rank_button" autocomplete="off" > Demethylases
                      </label>

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

                  <!-- resource key -->
                  <div id='res_color_key_title' class='title_viz_instructs'>Resource Groups:</div>
                  <div id='res_color_key_div'></div>

                  <!-- input box for gene search -->
                  <div id='gene_search_container' class='row'>
                      <input id='gene_search_box' type="text" class="form-control" placeholder="Input Gene" aria-describedby="sizing-addon2">

                      <div id='gene_search_button' class="btn-group" data-toggle="buttons" >
                        <label id='gene_search_submit' class="btn btn-primary active ">
                          <input type="radio" name="options" id="" autocomplete="off" checked > Search
                        </label>
                      </div>
                  </div>



                  <!-- <div class='viz_instruct_text'>  </div> -->

                  <!-- <button id='modify_enrichrgram_button' class='btn btn-danger buttons' onClick='rerun_enrichrgram();'>Re-run Enrichrgram</button> -->

                  <!-- <div class='viz_instruct_text'>Ordering</div> -->

                  <!-- <div class="btn-group" data-toggle="buttons" >
                    <label class="btn btn-primary active" onclick="reorder_clust_rank('clust');">
                      <input type="radio" name="options" id="clust_button" autocomplete="off" checked > Cluster
                    </label>
                    <label class="btn btn-primary" onclick="reorder_clust_rank('rank');">
                      <input type="radio" name="options" id="rank_button" autocomplete="off" > Rank
                    </label>
                  </div> -->


                  <!-- <div class='viz_instruct_text'><a href="">Export data to table</a></div> -->
                  <!-- <div class='viz_instruct_text'>Save SVG</div> -->
                </div>
                <div id='clustergram_container' >
                  <div id='col_title'>Resources</div>
                  <div id='clust_and_row_container'>
                    <div id='row_title'>Genes</div>
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
    <!-- /#page-content-wrapper -->
  </div>
		
		
		
		
		</div>
		<%@include file="footer.html"%>

		<!-- Libraries -->
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<script src='script/lib/jquery.accordion.js'></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="script/lib/bootstrap-switch.min.js"></script>
		<script src="script/lib/crossfilter.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js"></script>
		<script src="script/lib/dc.js"></script>
		<script src="script/lib/queue.js"></script>
		<script src="script/lib/keen.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
		<script src="script/lib/underscore.strings.js"></script>
		<script src='script/lib/blockUI.js'></script>
		<script src='script/lib/type_ahead.js'></script>
		
		<!-- Clustergram Scripts -->
		<script src='script/lib/d3_clustergram.js'></script>
		<script src='script/lib/load_local_network.js'></script>
	  	<script src="script/visualize.js"></script>
	</body>
</html>
		
		


<!-- Modal -->
<!--<div id='modal_no_input' class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Please input genes</h4>
      </div>
      <div class="modal-body">

        <div class="container-fluid">
          <p>Input one human/mouse gene symbol per line. </p>
          <p>For instance:</p>
          <div>PER1</div>
          <div>SENP3</div>
          <div>TOP2A</div>
          <div>PTPN7</div>
          <div>...</div>

        </div>
      </div>
    </div>
  </div>
</div>-->
