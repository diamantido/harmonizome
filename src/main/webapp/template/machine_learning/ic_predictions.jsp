<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions"%>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant"%>

<t:wrapper title="${title}" navType="withSearch">
	<div class="machine-learning-page">
		<h1>
			Ion channel predictions <span class="note"><c:out
					value="${note}" /></span>
		</h1>
		<p>For this case study, we trained a Random Forest classifier to
			distinguish between ion channels and other transmembrane proteins,
			and then applied the classifier to predict if any uncharacterized
			transmembrane proteins might be ion channels. The classifier
			performed very well in cross-validation with an area under the
			receiver operating characteristic curve of 0.99, F1-score of 0.922,
			and Matthew’s correlation coefficient of 0.918.</p>
		<p>Each row in the results table provides the gene symbol of a
			transmembrane protein (Gene column), provides the predicted
			probability that the protein is an ion channel (Probability column),
			indicates whether the protein is a known ion channel (Known column),
			indicates whether the protein was used as an example for training the
			classifier (Training column), provides the predicted false discovery
			rate if all predictions with better or equivalent probability were
			tested (FDR column), and lists up to three ion channels that the
			classifier thinks are most similar to the protein (Similar IC
			columns).</p>
		<p>${commonDownloadText}
			<a href="${downloadUrl}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a>
		</p>
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
						<td><a
							href="${prediction.gene.endpoint}/${prediction.gene.urlEncodedValue}">
								<c:out value="${prediction.gene.symbol}" />
						</a></td>
						<td><c:out value="${prediction.probability}" /></td>
						<td><c:out value="${prediction.knownIonChannel}" /></td>
						<td><c:out value="${prediction.inTrainingSet}" /></td>
						<td><c:out value="${prediction.falseDiscoveryRate}" /></td>
						<td><c:set var="parts"
								value="${fn:split(prediction.similarIonChannel1, ',')}" /> <c:set
								var="geneSymbol" value="${parts[0]}" /> <a
							href="${prediction.gene.endpoint}/${cfn:urlEncode(geneSymbol)}">
								<c:out value="${geneSymbol}" />
						</a> <c:if test="${not empty parts[0]}"> ,</c:if> <c:out
								value="${parts[1]}" /></td>
						<td><c:set var="parts"
								value="${fn:split(prediction.similarIonChannel2, ',')}" /> <c:set
								var="geneSymbol" value="${parts[0]}" /> <a
							href="${prediction.gene.endpoint}/${cfn:urlEncode(geneSymbol)}">
								<c:out value="${geneSymbol}" />
						</a> <c:if test="${not empty parts[0]}"> ,</c:if> <c:out
								value="${parts[1]}" /></td>
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
