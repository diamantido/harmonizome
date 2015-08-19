<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    	<link rel="stylesheet" type="text/css" href="/Harmonizome/style/css/documentation.css">
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full">
				<div class="container">
					<h1>REST API documentation</h1>
					<div class="row">
						<div class="col-md-12">
							<section>
								<h2>Introduction</h2>
								<p>This document describes the REST APIs provided by the Harmonizome. These APIs are for developers who want to integrate the Harmonizome's data into their own applications or who want to run batch scripts against the data.</p>
							</section>
							<section>
								<h2>Traversing the URLs</h2>
								<p>These APIs provide direct access to the data via URL paths and were designed to be used without any knowledge beyond the base URL. The base URL returns a list of the available data entities:</p>
<pre>
GET /Harmonizome/api/1.0

{
    "version" :1.0,
    "entities":[
        {
            "entity": "dataset",
            "href": "/api/1.0/dataset"
        },
        {
            "entity": "gene",
            "href": "/api/1.0/gene"
        },
        {
            "entity": "gene set",
            "href": "/api/1.0/gene_set"
        },
        {
            "entity": "attribute",
            "href": "/api/1.0/attribute"
        }
    ]
}</pre>
								<p>Any enitity's <span class="mono">href</span> property can be requested for more information:</p>
<pre>
GET /Harmonizome/api/1.0/gene

{
    "next": "/api/1.0/gene?cursor=100",
    "entities": [
        {
            "symbol": "LOC105377913",
            "href": "/api/1.0/gene/LOC105377913"
         },
         {
             "symbol": "LOC105377912",
             "href": "/api/1.0/gene/LOC105377912"
         },
         {
             "symbol":"LOC105377911",
             "href":"/api/1.0/gene/LOC105377911"
         },
         ...
     ]
}
								</pre>
							</section>
							<section>
								<h2>The Cursor</h2>
								<p>In order to minimize database queries and request times, this API uses a technique called "cursoring" to paginate large result sets. Add a query parameter <span class="mono">cursor</span> to the <span class="mono">GET</span> request to see the selection of data starting at the cursor:</p>
<pre>
GET /Harmonizome/api/1.0/gene?cursor=3141
</pre>
								<p>If no cursor is provided, the API defaults to 0. The maximum result set size is 100, and the <span class="mono">next</span> property will be returned with a link to the next selection of data.</p>
							</section>
							<section>
								<h2>Entities</h2>
								<p>Entities provide actual data and have no <span class="mono">href</span> property. For example</p>
<pre>
GET /Harmonizome/api/1.0/gene/nanog
</pre>
								<p>Will return</p>
<pre>
{
    "symbol": "NANOG",
    "name": "Nanog homeobox",
    "ncbiEntrezGeneId": 79923,
    "ncbiEntrezGeneUrl": "http://www.ncbi.nlm.nih.gov/gene/79923",
    "geneSets": [
        {
            "attribute": "urinary tract",
            "dataset": "TISSUES Curated Tissue Protein Expression Evidence Scores",
            "href" :"/api/1.0/gene_set/urinary+tract/TISSUES+Curated+Tissue+Protein+Expression+Evidence+Scores"
        },
        ...
    ]
}
</pre>
								<p>Note that the <span class="mono">geneSets</span> property is an array of entities; therefore, each gene set has its own <span class="mono">href</span> property which can be traversed.</p>
							</section>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>