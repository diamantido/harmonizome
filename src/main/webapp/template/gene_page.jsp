<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html" %>
		<base href="/Harmonizome/" />
		<link rel="stylesheet" type="text/css" href="style/css/gene-page.css">
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="container gene-page">
			<h1>${symbol}</h1>
			<dl>
				<dt>Name</dt><dd>${name}</dd>
				<dt>Description</dt><dd>${description}</dd>
				<dt>Synonyms</dt><dd></dd>
			</dl>
		</div>
	</body>
</html>