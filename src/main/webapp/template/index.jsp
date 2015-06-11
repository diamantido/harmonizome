<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="common_includes.html" %>
        <link rel="stylesheet" type="text/css" href="/Harmonizome/style/css/index.css">
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<%--<%@include file="search_bar.html" %>--%>
			<div class="content container-full">
				<div class="container">
					<div class="row">
						<h3 class="col-sm-12">
							Search a collection of processed data about genes and proteins from over 100 datasets provided by over 50 online resources.
						</h3>
					</div>
					<div id="intro" class="row">
					    <div class="col-sm-6">
					        <div class="portal pull-left">
					            <h3>Browse datasets</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="browse">
					                <button>Browse</button>
					            </a>
					        </div>
					    </div>
					    <div class="col-sm-6">
					        <div class="portal pull-right">
					            <h3>Search data</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="search">
					                <button>Search</button>
					            </a>
					        </div>
					    </div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>