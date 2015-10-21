<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Gene Family" navType="withSearch">
	<div class="gene-family-page">
		<h1><c:out value="${hgncRootFamily.name}"/> <span class="note">Gene Family</span></h1>
		<table class="table">
			<tr>
				<td class="col-md-2">HGNC Family ID</td>
				<td class="col-md-10">
					<a href="${hgncRootFamily.url}" target="_blank">
						<c:out value="${hgncRootFamily.familyId}"/>
					</a>
				</td>
			</tr>
			<tr>
				<td class="col-md-2">Genes</td>
				<td class="col-md-10">
					<c:forEach var="gene" items="${hgncRootFamily.genes}" varStatus="loop">
						<a href="${gene.endpoint}/${gene.urlEncodedValue}"><c:out value="${gene.symbol}"/></a><c:if test="${!loop.last}">, </c:if>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
</t:wrapper>