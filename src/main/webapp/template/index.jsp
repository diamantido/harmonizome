<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>

<t:wrapper title="Harmonizome">
	<jsp:attribute name="jsonld">
		{
			"@context": "http://schema.org",
			"@type": "WebSite",
			"url": "${Constant.ORIGIN()}/${Constant.HARMONIZOME_PREFIX()}/",
			"potentialAction": [
				{
					"@type": "SearchAction",
					"target": "${Constant.ORIGIN()}/${Constant.HARMONIZOME_PREFIX()}/${Constant.SEARCH_URL}?t=all&q={query}",
					"query-input": "required name=query"
				}
			]
		}
	</jsp:attribute>
	<jsp:body>
		<div class="landing-page">
			<div class="row">
				<div class="col-md-12 text-center">
					<img src="image/logo_100x87.png">
					<h1 class="project-title">Harmonizome 3.0</h1>
					<p class="instruction">Search for genes or proteins and their functional terms extracted and organized from over a hundred publicly available resources. <a href="about#resources"><strong>Learn more</strong></a>.</p>
				</div>
			</div>
			<jsp:include page="fragment/searchBar.jsp">
				<jsp:param value="on-index-page" name="location"/>
			</jsp:include>
			<div class="row top-buffer text-center">
				<div class="col-md-12">
					<strong>Example searches</strong>
					<ul class="list-inline">
						<li>
							<a href="${Constant.SEARCH_URL}?q=achilles&m=example">achilles</a>
						</li>
						<li>
							<a href="${Constant.SEARCH_URL}?q=STAT3&m=example">STAT3</a>
						</li>
						<li>
							<a href="${Constant.SEARCH_URL}?q=breast+cancer&m=example">breast cancer</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<p class="whatsNew">Check out what's new in Harmonizome 3.0! <a href="whatsNew"><strong>Learn more</strong></a>.</p>
				</div>
			</div>
		</div>
	</jsp:body>
</t:wrapper>
