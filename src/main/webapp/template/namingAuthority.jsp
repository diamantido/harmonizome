<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="Naming Authority - ${namingAuthority.name}" navType="withSearch">
	<h1><c:out value="${namingAuthority.name}"/> <span class="note">Naming Authority</span></h1>
	<section>
		<table class="table">
			<tr>
				<td class="col-md-2">Acronym</td>
				<td class="initial col-md-10">
					<c:out value="${namingAuthority.acronym}"/>
				</td>
			</tr>
			<tr>
				<td class="col-md-2">Description</td>
				<td class="initial col-md-10">
					<c:out value="${namingAuthority.description}"/>
				</td>
			</tr>

			<tr>
				<td class="col-md-2">External Link</td>
				<td class="col-md-10">
					<a href="${namingAuthority.url}" target="_blank">${namingAuthority.url}</a>
				</td>
			</tr>
			<tr>
				<td class="col-md-2">Citation</td>
				<td class="initial col-md-10">
					<a href="${namingAuthority.publication.pubmedUrl}" target="_blank">
						<c:out value="${namingAuthority.publication.longCitation}"></c:out>
					</a>
				</td>
			</tr>
		</table>
	</section>
</t:wrapper>