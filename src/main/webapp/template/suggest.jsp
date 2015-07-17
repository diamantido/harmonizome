<%@ page import="java.util.List" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.net.URLCodec" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<% 
@SuppressWarnings("unchecked")
List<String> datasetSuggestions = (List<String>) request.getAttribute("datasetSuggestions");
@SuppressWarnings("unchecked")
List<String> geneSuggestions = (List<String>) request.getAttribute("geneSuggestions");
@SuppressWarnings("unchecked")
List<String> attributeSuggestions = (List<String>) request.getAttribute("attributeSuggestions");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
        <%@include file="commonIncludes.html" %>
        <script>
			$(function() {
				HMZ();
			});
		</script>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full">
				<div id="suggest-page" class="container">
					<h1>Suggestions</h1>
					<p class="note">Did you mean...</p>
					<ul class="list-inline">
					<% for (String name : datasetSuggestions) { %>
						<li>
							<span class="badge dataset">
								<a href="<%= Constant.SEARCH_URL %>?q=<%= URLCodec.encode(name) %>"><%= name %></a>
							</span>
						</li>
					<% } %>
					<% for (String symbol : geneSuggestions) { %>
						<li>
							<span class="badge gene">
								<a href="<%= Constant.SEARCH_URL %>?q=<%= URLCodec.encode(symbol) %>"><%= symbol %></a>
							</span>
						</li>
					<% } %>
					<% for (String name : attributeSuggestions) { %>
						<li>
							<span class="badge attribute">
								<a href="<%= Constant.SEARCH_URL %>?q=<%= URLCodec.encode(name) %>"><%= name %></a>
							</span>
						</li>
					<% } %>
					</ul>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>