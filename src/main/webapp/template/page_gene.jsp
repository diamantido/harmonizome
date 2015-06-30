<section>
	<h3><%= request.getAttribute("geneSymbol") %> <span class="note">Gene</span></h3>
    <table class="table">
    	<tr>
    		<td>Symbol</td>
    		<td><%= request.getAttribute("geneSymbol") %></td>
    	</tr>
    	<tr>
    		<td>Name</td>
    		<td><%= request.getAttribute("geneName") %></td>
    	</tr>
    	<tr>
    		<td>Description</td>
    		<td><%= request.getAttribute("geneDescription") %></td>
    	</tr>
    	<tr>
    		<td>NCBI Entrez Gene ID</td>
    		<td><%= request.getAttribute("geneNcbiEntrezGeneId") %></td>
    	</tr>
    	<tr>
    		<td>NCBI Entrez Gene URL</td>
    		<td><%= request.getAttribute("geneNcbiEntrezGeneUrl") %></td>
    	</tr>
    </table>
</section>