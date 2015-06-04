<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html"%>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<div class="content container">
				<h3>${symbol} <span class="note">${note}</span></h3>
				<table class="table">
					<tr>
						<td class="col-sm-3">Name</td>
						<td class="col-sm-9">${name}</td>
					</tr>
					<tr>
						<td class="col-sm-3">Uniprot URL</td>
						<td class="col-sm-9"><a href="${uniprotUrl}" target="_blank">${uniprotUrl}</a></td>
					</tr>
					<tr>
						<td class="col-sm-3">Gene</td>
						<td class="col-sm-9"><a href="gene/${gene}">${gene}</a></td>
					</tr>
				</table>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>