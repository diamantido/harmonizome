<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html"%>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h3>${symbol} <span class="note">${redirectMessage}</span></h3>
				<table class="table">
					<tr>
						<td class="col-sm-3">Name</td>
						<td class="col-sm-9">${name}</td>
					</tr>
					<tr>
						<td class="col-sm-3">Description</td>
						<td class="col-sm-9">${description}</td>
					</tr>
					<tr>
						<td class="col-sm-3">Synonyms</td>
						<td class="col-sm-9">${synonyms}</td>
					</tr>
					<tr>
						<td class="col-sm-3">NCBI Entrez Gene URL</td>
						<td class="col-sm-9"><a href="${ncbiEntrezGeneUrl}" target="_blank">${ncbiEntrezGeneUrl}</a></td>
					</tr>
					<tr>
						<td class="col-sm-3">Proteins</td>
						<td class="col-sm-9">${proteins}</td>
					</tr>
					<tr>
						<td class="col-sm-3">IDG Family</td>
						<td class="col-sm-9">${idgFamily}</td>
					</tr>
					<tr>
						<td class="col-sm-3">IDG TDL Class</td>
						<td class="col-sm-9">${idgTdlClass}</td>
					</tr>
					<tr>
						<td class="col-sm-3">HGNC Root Families</td>
						<td class="col-sm-9">${hgncRootFamilies}</td>
					</tr>
					<tr>
						<td class="col-sm-3">HGNC Terminal Families</td>
						<td class="col-sm-9">${hgncTerminalFamilies}</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="footer.html"%>
	</body>
</html>