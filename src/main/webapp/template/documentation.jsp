<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Documentation">

    <link rel="stylesheet" href="lib/highlight/highlight.css">
    <link rel="stylesheet" type="text/css" href="/Harmonizome/style/css/documentation.css">
    <script src="lib/highlight/highlight.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

    <div class="documentation-page">
        <h1>REST API documentation</h1>
        <div class="row">
            <div class="col-md-12">
                <section>
                    <h2>Introduction</h2>
                    <p>This document describes the REST APIs provided by the Harmonizome. These APIs are for developers who want to integrate the Harmonizome's data into their own applications or who want to run batch scripts against the data.</p>
                    <p>If you're comfortable with Python, you can use an API wrapper written in Python. See <a href="documentation#quick-start">Quick Start in Python</a>.</p>
                </section>
                <section>
                    <h2>Traversing the URLs</h2>
                    <p>These APIs provide direct access to the data via URL paths and were designed to be used without any knowledge beyond the base URL. The base URL returns a list of the available data entities:</p>
<pre><code class="json">GET /Harmonizome/api/1.0

{
    "version" :1.0,
    "entities":[
        {
            "entity": "attribute",
            "href": "/api/1.0/attribute"
        },
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
        ...
    ]
}</code></pre>
                    <p>Any enitity's <span class="mono">href</span> property can be requested for more information:</p>
    <pre><code class="json">GET /Harmonizome/api/1.0/gene

{
    "count": 56720,
    "selection": [0, 100],
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
}</code></pre>
                </section>
                <section>
                    <h2>The Cursor</h2>
                    <p>In order to minimize database queries and request times, this API uses a technique called "cursoring" to paginate large result sets. Add a query parameter <span class="mono">cursor</span> to the <span class="mono">GET</span> request to see the selection of data starting at the cursor:</p>
<pre><code class="bash">GET /Harmonizome/api/1.0/gene?cursor=3141</code></pre>
                    <p>If no cursor is provided, the API defaults to 0. The maximum result set size is 100, and the <span class="mono">next</span> property will be returned with a link to the next selection of data.</p>
                </section>
                <section>
                    <h2>Entities</h2>
                    <p>Entities provide actual data and have no <span class="mono">href</span> property. For example</p>
<pre><code class="bash">GET /Harmonizome/api/1.0/gene/nanog</code></pre>
                    <p>Will return</p>
<pre><code class="json">{
    "symbol": "NANOG",
    "name": "Nanog homeobox",
    "ncbiEntrezGeneId": 79923,
    "ncbiEntrezGeneUrl": "http://www.ncbi.nlm.nih.gov/gene/79923",
    ...
}
</code></pre>
                    <p>Note that the <span class="mono">geneSets</span> property is an array of entities; therefore, each gene set has its own <span class="mono">href</span> property which can be traversed.</p>
                </section>
                <section id="quick-start">
                    <h2>Quick Start in Python</h2>
                    <ul>
                        <li>Download this Python module: <span class="mono"><a href="static/harmonizomeapi.py" target="_blank">harmonizomeapi.py</a></span>.</li>
                        <li>Call <span class="mono">fetch</span> with a supported <span class="mono">Entity</span>. For example:</li>
                    </ul>
<pre><code class="python">&gt;&gt;&gt; from harmonizomeapi import HarmonizomeAPI, Entity
&gt;&gt;&gt; pid_dataset = HarmonizomeAPI.fetch(Entity.DATASET, name='PID pathways')</code></pre>
                    <ul>
                        <li>To get a list of the entities, omit the <span class="mono">name</span>.</li>
                    </ul>
<pre><code class="python">&gt;&gt;&gt; entity_list = HarmonizomeAPI.fetch(Entity.GENE)</code></pre>
                    <ul>
                        <li>To get more of the same entity, pass in the response object to the <span class="mono">next</span> function.</li>
                    </ul>
<pre><code class="python">&gt;&gt;&gt; more = HarmonizomeAPI.next(entity_list)</code></pre>
                </section>
            </div>
        </div>
    </div>
</t:wrapper>