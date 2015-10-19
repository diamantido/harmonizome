<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    	<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    </head>
    <body>
		<%@include file="navbar.jsp" %>
		<div class="wrapper">
			<div class="content container-full mobile-page">
				<div class="container">
					<div class="row">
						<h1 class="col-md-12">Mobile</h1>
					</div>
					<div class="row">
						<div class="col-md-6">
							<ul class="list-unstyled list-inline icons">
								<li><i class="fa fa-laptop fa-3x"></i></li>
								<li><i class="fa fa-tablet fa-3x"></i></li>
								<li><i class="fa fa-mobile fa-3x"></i></li>
							</ul>
							<h4>Take the Harmonizome with you</h4>
							<p>
								With your free Harmonizome app, you can explore genes, proteins, and functional terms from anywhere.
							</p>
							<ul class="list-unstyled app-store-links">
								<li>
									<i class="fa fa-android fa-2x"></i>
									<a href="https://itunes.apple.com/us/app/harmonizome/id1046990905" target="_blank">Free for iPhone</a>
								</li>
								<li>
									<i class="fa fa-apple fa-2x"></i>
									<a href="https://play.google.com/store/apps/details?id=com.maayanlab.harmonizome&hl=en" target="_blank">Free for Android</a>
								</li>
							</ul>
						</div>
						<div class="col-md-6">
							<img src="image/mobile_screenshot.png">
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>