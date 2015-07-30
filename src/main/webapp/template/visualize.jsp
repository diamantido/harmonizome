<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
		<%--<link rel="stylesheet" href="style/lib/bootstrap-switch.css">
		<link rel="stylesheet" href="style/lib/dc.css">
		<link rel="stylesheet" href="style/lib/jquery.accordion.css">
		<link rel="stylesheet" href="style/lib/custom.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<link rel="stylesheet" href="style/css/visualize.css">--%>
		<style>
			/* Inline as a temporary fix. */
			iframe {
				border: none;
				height: 100%;
				width: 100%;
			}
			.content {
				height: 100%;
			}
			.footer {
				bottom: -50px;
			}
		</style>
	</head>
<body>
	<body>
		<%@include file="navbar.html"%>
		<div class="content container-full">
			<iframe src="http://cleopatra.1425mad.mssm.edu:31001/harmonogram/"></iframe>
		</div>
		<%@include file="footer.html"%>

			<%--
			<!-- Sidebar -->
			<div class="col-sm-2">
				<p class="instruction">
					The clustergram visualizes gene appearance in Harmonizome resources. Genes are shown as rows and resources as columns. The gene occurance value is normalized relative to the occurance of other genes in the resource. Change the clustergram ordering by using the toggle switch or by clicking row or column labels.
				</p>
				
				<!-- Buttons for groups -->	          	
	          	<div id='class_buttons' class="btn-group-vertical text-center" data-toggle="buttons">
	          		<label class="btn btn-primary active prot_class" onclick="load_class_clustergram('KIN');">
	          			<input type="radio" name="options" id="clust_button" autocomplete="off" checked>Kinases
	          		</label>
                    <label class="btn btn-primary  prot_class" onclick="load_class_clustergram('PP');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Phosphatases
                    </label>
                    <label class="btn btn-primary  prot_class" onclick="load_class_clustergram('GPCR');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">GPCRs
                    </label>
                    <label class="btn btn-primary prot_class" onclick="load_class_clustergram('IC');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Ion Channels
                    </label>
                    <label class="btn btn-primary prot_class" onclick="load_class_clustergram('TF');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Transcription Factors
                    </label>
                    <label class="btn btn-primary prot_class" onclick="load_class_clustergram('ACT');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Acetyltransferases
                    </label>
                    <label class="btn btn-primary prot_class" onclick="load_class_clustergram('DACT');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Deacetylases
                    </label>
                    <label class="btn btn-primary prot_class" onclick="load_class_clustergram('MET');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Methyltransferases
                    </label>
                    <label class="btn btn-primary prot_class" onclick="load_class_clustergram('DMET');">
                        <input type="radio" name="options" id="rank_button" autocomplete="off">Demethylases
                    </label>
                </div>
      
                <!-- resource key -->
                <div id='res_color_key_title' class='title_viz_instructs'>Resource Groups</div>          
                <div id='res_color_key_div'></div>
                
                <!-- input box for gene search -->
                <div id='gene_search_container'>
                	<input id='gene_search_box' type="text" class="form-control" placeholder="Input Gene" aria-describedby="sizing-addon2">
                	<div id='gene_search_button' class="btn-group" data-toggle="buttons">
                		<label id='gene_search_submit' class="btn btn-primary active ">
                			<input type="radio" name="options" id="" autocomplete="off" checked > Search
                        </label>
                    </div>
            	</div>
            	
            	<!-- button container -->
            	<div id='button_container'>
            		<div id='toggle_order' class="btn-group" data-toggle="buttons">
            			<label class="btn btn-primary active prot_class" onclick="reorder_clust_rank('clust');">
            				<input type="radio" name="options" id="clust_button" autocomplete="off" checked > Cluster
            			</label>
            			<label class="btn btn-primary prot_class" onclick="reorder_clust_rank('rank');">
                        	<input type="radio" name="options" id="rank_button" autocomplete="off"> Rank
                      	</label>
					</div>
				</div>
	    	</div>

    		<!-- Visualization -->
    		<div id="visualization" class="col-sm-10">
    			<!--<div id='col_title'>Resources</div>
    			<div id='clust_and_row_container'>
	    			<div id='row_title'>Genes</div>
	    			<div id='svg_div'></div>
	    		</div>-->
    		</div>
    	</div>
		
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
		<script src="script/lib/helper.js"></script>
		<script src="script/clustergram/Svg.js"></script>
		<script src="script/clustergram/Viz.js"></script>
		<script src="script/clustergram/Labels.js"></script>
		<script src="script/clustergram/Matrix.js"></script>
		<script src="script/clustergram/Orders.js"></script>
		<script src="script/clustergram/Margins.js"></script>
		<script src="script/clustergram/Legend.js"></script>
		<script src="script/clustergram/FontScale.js"></script>
		<script src="script/clustergram/Frame.js"></script>
	  	<script src="script/visualize.js"></script>--%>
	</body>
</html>