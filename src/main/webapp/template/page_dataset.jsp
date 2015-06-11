<section class="row">
	<h3><%= request.getAttribute("datasetName") %></h3>
    <section>
        <h4>Resource</h4>
        <p><%= request.getAttribute("datasetResource") %></p>
    </section>
    <section>
        <h4>Data Description</h4>
        <p><%= request.getAttribute("datasetDescription") %></p>
    </section>
    <section>
        <h4>Association</h4>
        <p><%= request.getAttribute("datasetAssociation") %></p>
    </section>
    <section>
        <h4>Attribute type</h4>
        <p><%= request.getAttribute("datasetAttribute") %></p>
    </section>
</section>