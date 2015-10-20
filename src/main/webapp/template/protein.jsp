<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="${protein.symbol}" navType="withSearch">
    <div class="protein-page">
        <h1><c:out value="${protein.symbol}"/> <span class="note">Protein</span></h1>
        <table class="table">
            <tr>
                <td class="col-md-2">Name</td>
                <td class="col-md-10">
                    <c:out value="${protein.name}"/>
                </td>
            </tr>
            <tr>
                <td class="col-md-2">Description</td>
                <td class="col-md-10">
                    <c:out value="${protein.description}"/>
                </td>
            </tr>
            <tr>
                <td class="col-md-2">UniProt ID</td>
                <td class="col-md-10">
                    <a href="${protein.uniprotUrl}" target="_blank">
                        <c:out value="${protein.uniprotId}"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td class="col-md-2">Gene</td>
                <td class="col-md-10">
                    <a href="${protein.gene.endpoint}/${protein.gene.urlEncodedValue}">
                        <c:out value="${protein.gene.symbol}"/>
                    </a>
                </td>
            </tr>
        </table>
    </div>
</t:wrapper>