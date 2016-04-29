<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions"%>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant"%>

<t:wrapper title="${title}" navType="withSearch">
	<div class="machine-learning-page">
		<h1>
			Kinase substrates <span class="note"><c:out value="${note}" /></span>
		</h1>
		<p>For this case study, we trained a classifier to predict
			known interactions between kinases and their substrates, and then
			predicted kinases for proteins that have phosphosites with unknown
			regulatory kinase. The classifier performed modestly in
			cross-validation with an area under the receiver operating
			characteristic curve of 0.88, F1-score of 0.23, and Matthew’s
			correlation coefficient of 0.22.</p>
		<p>Each row in the results table provides the gene symbol of a
			kinase (Kinase column), provides the gene symbol of a phosphoprotein
			(Substrate column), provides the predicted probability that the
			Kinase phosphorylates the protein (Probability column), indicates
			whether the phosphorylation reaction is known (Known column),
			indicates whether the pair was used as an example for training the
			classifier (Training column), and provides the predicted false
			discovery rate if all predictions with better or equivalent
			probability were tested (FDR column).</p>
		<p>
		<p>${commonDownloadText}
			<a href="${downloadUrl}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a>
		</p>
	</div>
	<div class="table-responsive">
		<table class="table data-table">
			<thead>
				<tr>
					<th>Kinase</th>
					<th>Substrate</th>
					<th>Probability</th>
					<th>Known</th>
					<th>Training</th>
					<th>FDR</th>
				</tr>
			</thead>
			<c:forEach var="prediction" items="${predictions}">
				<tr>
					<td><a
						href="${prediction.kinaseGene.endpoint}/${prediction.kinaseGene.urlEncodedValue}">
							<c:out value="${prediction.kinaseGene.symbol}" />
					</a></td>
					<td><a
						href="${prediction.substrateGene.endpoint}/${prediction.substrateGene.urlEncodedValue}">
							<c:out value="${prediction.substrateGene.symbol}" />
					</a></td>
					<td><c:out value="${prediction.probability}" /></td>
					<td><c:out value="${prediction.knownInteraction}" /></td>
					<td><c:out value="${prediction.inTrainingSet}" /></td>
					<td><c:out value="${prediction.falseDiscoveryRate}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</t:wrapper>

<script src="script/machine-learning-pages.js"></script>
<script>
	HARMONIZOME.setupDataTable(2);
</script>
