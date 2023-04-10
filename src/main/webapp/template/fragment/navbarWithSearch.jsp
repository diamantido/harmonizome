<div id="navbar-wrapper" class="navbar-static-top">
	<div class="container">
		<h1 class="pull-left">
			<a href="">
				<img src="image/logo_30x26.png">
				<span class="project-title">Harmonizome 3.0</span>
			</a>
		</h1>

		<jsp:include page="/template/fragment/searchBar.jsp">
			<jsp:param name="location" value="in-navbar"/>
            <jsp:param name="userSearch" value="${param.userSearch}"/>
		</jsp:include>

		<!-- This is the dropdown button for the mobile version of the site -->
		<div id="menu-dropdown" class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu" aria-expanded="false" aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>

		<%@include file="/template/fragment/menu.jsp" %>
	</div>
</div>