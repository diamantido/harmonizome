<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
		<style>
			/* Inline as a temporary fix. */
			iframe {
				border: none;
				height: 100%;
				width: 100%;
			}
			.content {
				height: 100%;
			}
			.footer {
				bottom: -50px;
			}
		</style>
	</head>
<body>
	<body>
		<%@include file="navbar.html"%>
		<div class="content container-full">
			<iframe src="http://amp.pharm.mssm.edu/harmonogram/"></iframe>
		</div>
		<%@include file="footer.html"%>
	</body>
</html>