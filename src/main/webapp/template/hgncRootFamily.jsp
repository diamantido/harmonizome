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
		</table>
		<h2>Genes</h2>
        <div class="table-responsive">
            <table class="table data-table">
                <thead>
                    <th>Symbol</th>
                    <th>Name</th>
                </thead>
                <c:forEach var="gene" items="${hgncRootFamily.genes}">
                    <tr>
                        <td>
                            <a href="${gene.endpoint}/${gene.urlEncodedValue}"><c:out value="${gene.symbol}"/></a>
                        </td>
                        <td>
                            <c:out value="${gene.name}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
	</div>
</t:wrapper>