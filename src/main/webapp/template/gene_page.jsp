<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="common_includes.html" %>
		<%@include file="base_tag.html" %>
	</head>
	<body>
		<%@include file="navbar.html" %>
		<div class="container page">
			<h1>${symbol}</h1>
			<dl>
				<dt>Name</dt><dd>${name}</dd>
				<dt>Description</dt><dd>${description}</dd>
				<dt>Synonyms</dt><dd></dd>
			</dl>
		</div>
	</body>
</html>