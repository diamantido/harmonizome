<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>

<t:wrapper title="${title}" navType="withSearch">
    <div class="machine-learning-page">
    	<h1>Ion channel predictions <span class="note"><c:out value="${note}"/></span></h1>
    	<p><c:out value="${commonText}"/></p>
    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
   
	    <div class="table-responsive">
		    <table class="table data-table">
		    	<thead>
		    		<tr>
		    			<th>Gene</th>
		    			<th>Probability</th>
		    			<th>Known</th>
		    			<th>Training</th>
		    			<th>FDR</th>
		    			<th>1st Similar IC</th>
		    			<th>2nd Similar IC</th>
		    		</tr>
		    	</thead>
		 		<c:forEach var="prediction" items="${predictions}">
		 			<tr>
		 				<td>
			 				<a href="${prediction.gene.endpoint}/${prediction.gene.urlEncodedValue}">
		                    	<c:out value="${prediction.gene.symbol}"/>
		                    </a>
		 				</td>
		 				<td><c:out value="${prediction.probability}"/></td>
		 				<td><c:out value="${prediction.knownIonChannel}"/></td>
		 				<td><c:out value="${prediction.inTrainingSet}"/></td>
		 				<td><c:out value="${prediction.falseDiscoveryRate}"/></td>
		 				<td>
		 					<c:set var="parts" value="${fn:split(prediction.similarIonChannel1, ',')}"/>
		 					<c:set var="geneSymbol" value="${parts[0]}"/>
			 				<a href="${prediction.gene.endpoint}/${cfn:urlEncode(geneSymbol)}"><c:out value="${geneSymbol}"/></a>,
			 				<c:out value="${parts[1]}"/>
		 				</td>
		 				<td>
	 						<c:set var="parts" value="${fn:split(prediction.similarIonChannel2, ',')}"/>
		 					<c:set var="geneSymbol" value="${parts[0]}"/>
			 				<a href="${prediction.gene.endpoint}/${cfn:urlEncode(geneSymbol)}"><c:out value="${geneSymbol}"/></a>,
			 				<c:out value="${parts[1]}"/>
		 				</td>
		 			</tr>	
		        </c:forEach>
		    </table>
	    </div>
    </div>
</t:wrapper>

<script src="script/machine-learning-pages.js"></script>
<script>
	HARMONIZOME.setupDataTable(1);
</script>
