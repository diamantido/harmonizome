<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cfn" uri="http://amp.pharm.mssm.edu/functions"%>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant"%>

<t:wrapper title="Machine Learning" navType="withSearch">
	<div class="machine-learning-page">
		<h1>Machine Learning Case Studies</h1>
		<p>
			While the Harmonizome website provides a valuable interface for
			searching and browsing gene-biological entity associations collected
			from over 100 datasets, there is also enormous potential for
			biological discovery by using Harmonizome data to build computational
			models to predict novel properties of genes or proteins, such as
			molecular interactions or disease associations. We have made each
			processed dataset available for <a href="download">download</a> in
			several convenient formats to facilitate use of Harmonizome data for
			computational analysis.
		</p>
		<p>To demonstrate the value of Harmonizome data for
			computationally-driven hypothesis generation, we developed four
			supervised machine learning case studies. Our approach was similar
			for each case study. First we organized gene-biological entity
			associations from many Harmonizome datasets into a large feature
			matrix with genes labelling the rows and biological entities
			(features) labelling the columns. Then we trained a classifier to use
			the features to distinguish between genes (or pairs of genes) known
			to have a property of interest and genes (or pairs of genes) unlikely
			to have that property. Finally, we applied the classifier to make
			predictions about genes (or pairs of genes) for which knowledge is
			missing.</p>
		<p>
			Methods and results for the machine learning case studies are
			described in detail in the <a data-toggle="tooltip"
				data-placement="right" title="${pubWarning}">Harmonizome
				publication</a>. Here, we provide brief descriptions of the case
			studies, interactive tables for browsing the top predictions of the
			classifiers, and text files that contain the full results.
		</p>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>Case Study</th>
				<th>View Table <span class="glyphicon glyphicon-question-sign"
					data-toggle="tooltip" data-placement="right"
					title="View as HTML page."></span>
				</th>
				<th>Download Table <span
					class="glyphicon glyphicon-question-sign" data-toggle="tooltip"
					data-placement="right" title="Download as gzipped text file."></span></th>
			</tr>
		</thead>
		<tr>
			<td>Ion Channel Predictions</td>
			<td><a href="${Constant.ML_URL}/ic_predictions"
				class="btn btn-default glyphicon glyphicon-th-list"></a></td>
			<td><a href="${Constant.ML_DATA_DIR()}/ic_predictions/${filename}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a></td>
		</tr>
		<tr>
			<td>Mouse Phenotype Predictions</td>
			<td><a href="${Constant.ML_URL}/mp_predictions"
				class="btn btn-default glyphicon glyphicon-th-list"></a></td>
			<td><a href="${Constant.ML_DATA_DIR()}/mp_predictions/${filename}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a></td>
		</tr>
		<tr>
			<td>GPCR-Ligand Interaction Predictions</td>
			<td><a href="${Constant.ML_URL}/gl_predictions"
				class="btn btn-default glyphicon glyphicon-th-list"></a></td>
			<td><a href="${Constant.ML_DATA_DIR()}/gl_predictions/${filename}"
				class="btn btn-default glyphicon glyphicon-download-alt"></a></td>
		</tr>
		<tr>
			<td>Kinase-Substrate Interaction Predictions</td>
			<td><a href="${Constant.ML_URL}/ks_predictions"
				class="btn btn-default glyphicon glyphicon-th-list"></a></td>
			<td><a href="${Constant.ML_DATA_DIR()}/ks_predictions/${filename}"
				class="btn btn-default glyphicon glyphicon-download-alt""></a></td>
		</tr>
	</table>
</t:wrapper>
