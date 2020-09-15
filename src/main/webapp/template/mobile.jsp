<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper title="Mobile">

	<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

	<div class="mobile-page">
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
				<p>With your free Harmonizome app, you can explore genes,
					proteins, and functional terms from anywhere.</p>
				<ul class="list-unstyled app-store-links">
					<li><i class="fa fa-apple fa-2x"></i> <a
						href="https://itunes.apple.com/us/app/harmonizome/id1046990905"
						target="_blank">Free for iPhone</a></li>
					<li><i class="fa fa-android fa-2x"></i> <a
						href="https://play.google.com/store/apps/details?id=com.maayanlab.harmonizome&hl=en"
						target="_blank">Free for Android</a></li>
					<li class="last"><i class="fa fa-video-camera fa-2x"></i> <a
						href="https://www.youtube.com/watch?v=dkYcD51pnfY" target="_blank">Watch
							the tutorial</a></li>
				</ul>
				<h4>Privacy policy</h4>
				<p>
					The Harmonizome mobile application does not save or use sensitive
					user data. Queries and uploaded data from users are anonymously
					stored and not linked to any user information. If you have any
					questions regarding our privacy policy please <a
						href="${Constant.ORIGIN()}/${Constant.HARMONIZOME_PREFIX()}/contact">contact
						us</a>.
				</p>
			</div>
			<div class="col-md-6">
				<img src="image/mobile_screenshot.png">
			</div>
		</div>
	</div>
</t:wrapper>
