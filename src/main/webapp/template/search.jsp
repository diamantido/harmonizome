<!DOCTYPE HTML>
<html>
	<head>
		<%@include file="commonIncludes.html" %>
	</head>
	<body>
		<%@include file="navbar.html"%>
		<div class="wrapper">
			<%--<%@include file="search_bar.html" %>--%>
			<div class="content container-full">
				<div class="container">
					<h1 class="text-center">Search the Harmonizome</h1>
					<div id="intro" class="row">
					    <div class="col-sm-4">
					        <div class="portal">
					            <h3>Datasets</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="dataset/">
					                <button>Browse datasets</button>
					            </a>
					        </div>
					    </div>
					    <div class="col-sm-4">
					        <div class="portal">
					            <h3>Genes</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="gene/">
					                <button>Search genes</button>
					            </a>
					        </div>
					    </div>
					    <div class="col-sm-4">
					        <div class="portal">
					            <h3>Attributes</h3>
					            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
					            <a href="attribute/">
					                <button>Browse attribute categories</button>
					            </a>
					        </div>
					    </div>
					</div>
				</div>
			</div>
			<%@include file="footer.html"%>
		</div>
	</body>
</html>