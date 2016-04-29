<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions"%>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant"%>

<t:wrapper title="${title}" navType="withSearch">
	<div class="machine-learning-page">
		<h1>
			GPCR-ligand predictions <span class="note"><c:out
					value="${note}" /></span>
		</h1>
		<p>For this case study, we trained a classifier to predict known
			interactions between G protein-coupled receptors (GPCRs) and
			protein-derived ligands, and then predicted ligands for orphan GPRCs.
			The classifier performed well in cross-validation with an area under
			the receiver operating characteristic curve of 0.91, F1-score of
			0.59, and Matthew’s correlation coefficient of 0.59.</p>
		<p>Each row in the results table provides the gene symbol of a
			GPCR (GPCR column), provides the gene symbol of a candidate
			protein-derived ligand (Ligand column), provides the predicted
			probability that the candidate ligand binds to the GPCR (Probability
			column), indicates whether pair is known to interact (Known column),
			indicates whether the pair was used as an example for training the
			classifier (Training column), and provides the predicted false
			discovery rate if all predictions with better or equivalent
			probability were tested (FDR column).</p>
		<p>${commonDownloadText}
			<a href="${downloadUrl}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a>
		</p>
	</div>
	<div class="table-responsive">
		<table class="table data-table">
			<thead>
				<tr>
					<th>GPCR</th>
					<th>Ligand</th>
					<th>Probability</th>
					<th>Known</th>
					<th>Training</th>
					<th>FDR</th>
				</tr>
			</thead>
			<c:forEach var="prediction" items="${predictions}">
				<tr>
					<td><a
						href="${prediction.gpcrGene.endpoint}/${prediction.gpcrGene.urlEncodedValue}">
							<c:out value="${prediction.gpcrGene.symbol}" />
					</a></td>
					<td><a
						href="${prediction.ligandGene.endpoint}/${prediction.ligandGene.urlEncodedValue}">
							<c:out value="${prediction.ligandGene.symbol}" />
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
