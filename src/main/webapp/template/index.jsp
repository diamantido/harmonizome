<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Harmonizome</title>
        <%@include file="common_includes.html" %>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="/Harmonizome/style/css/index.css">
        <link rel="shortcut icon" href="image/favicon.png">
    </head>
    <body>
		<%@include file="navbar.html" %>
        <div id="wrapper">
            <div id="content" class="container">
                <div class="row">
				    <h2 class="col-sm-12 text-center">Harmonizome is a collection of processed data about genes and proteins from over 100 datasets provided by over 50 online resources.</h2>
				</div>
				<div id="intro" class="row">
				    <div class="col-sm-6">
				        <div class="portal left">
				            <h3>Datasets</h3>
				            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
				            <a href="dataset/">
				                <button>Explore datasets</button>
				            </a>
				        </div>
				    </div>
				    <div class="col-sm-6">
				        <div class="portal right">
				            <h3>Genes</h3>
				            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
				            <a href="gene/">
				                <button>Explore genes</button>
				            </a>
				        </div>
				    </div>
				</div>
            </div>
        </div>
        <%@include file="footer.html" %>
    </body>
</html>