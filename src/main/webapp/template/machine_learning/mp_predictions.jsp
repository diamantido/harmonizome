<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions"%>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant"%>

<t:wrapper title="${title}" navType="withSearch">
	<div class="machine-learning-page">
		<h1>
			Mouse phenotype predictions <span class="note"><c:out
					value="${note}" /></span>
		</h1>
		<p>For this case study, we trained a classifier to predict
			phenotypes observed in mice harboring single gene knockout mutations,
			and then predicted mouse phenotypes likely to be caused by knockout
			of genes that have not yet been phenotypically characterized. The
			classifier performed modestly in cross-validation with an area under
			the receiver operating characteristic curve of 0.88, F1-score of
			0.24, and Matthew’s correlation coefficient of 0.23.</p>
		<p>Each row in the results table provides the gene symbol of a
			gene (Gene column), provides the name of a phenotype from the
			Mammalian Phenotype Ontology (Phenotype column), provides the
			predicted probability that the phenotype will be observed in mice
			lacking the gene (Probability column), indicates whether the
			gene-phenotype association is known (Known column), indicates whether
			the pair was used as an example for training the classifier (Training
			column), and provides the predicted false discovery rate if all
			predictions with better or equivalent probability were tested (FDR
			column).</p>
		<p>${commonDownloadText}
			<a href="${downloadUrl}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a>
		</p>
	</div>
	<div class="table-responsive">
		<table class="table data-table">
			<thead>
				<tr>
					<th>Gene</th>
					<th>Phenotype</th>
					<th>Probability</th>
					<th>Known</th>
					<th>Training</th>
					<th>FDR</th>
				</tr>
			</thead>
			<c:forEach var="prediction" items="${predictions}">
				<tr>
					<td><a
						href="${prediction.gene.endpoint}/${prediction.gene.urlEncodedValue}">
							<c:out value="${prediction.gene.symbol}" />
					</a></td>
					<td><a
						href="${prediction.geneSet.endpoint}/${prediction.geneSet.urlEncodedValue}">
							<c:out value="${prediction.geneSet.nameFromDataset}" />
					</a></td>
					<td><c:out value="${prediction.probability}" /></td>
					<td><c:out value="${prediction.knownAssociation}" /></td>
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
