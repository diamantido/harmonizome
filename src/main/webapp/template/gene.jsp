<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Gene - ${gene.symbol}" navType="withSearch" extraKeywords="${gene.symbol}">
    <div class="gene-page">
        <h1 class="initial">${gene.symbol} <span class="note gene">${note}</span></h1>
        <section>
            <table class="table">
                <c:if test="${fn:length(gene.hgncRootFamilies) > 0 }">
                    <tr>
                        <td class="col-md-2">HGNC Family</td>
                        <td class="col-md-10">
                            <c:forEach var="family" items="${gene.hgncRootFamilies}" varStatus="loop">
                                <a href="${family.endpoint}/${family.urlEncodedValue}">${family.name}</a><c:if test="${!loop.last}">, </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td class="col-md-2">Name</td>
                    <td class="col-md-10 initial">${gene.name}</td>
                </tr>
                <c:if test="${not empty gene.description}">
                    <tr>
                        <td class="col-md-2">Description</td>
                        <td class="col-md-10">${gene.description}</td>
                    </tr>
                </c:if>
                <c:if test="${fn:length(gene.synonyms) > 0 }">
                    <tr>
                        <td class="col-md-2">Synonyms</td>
                        <td class="col-md-10">
                            <c:forEach var="synonym" items="${gene.synonyms}" varStatus="loop">
                                ${synonym.symbol}<c:if test="${!loop.last}">, </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${fn:length(gene.proteins) > 0 }">
                    <tr>
                        <td class="col-md-2">Proteins</td>
                        <td class="col-md-10">
                            <c:forEach var="protein" items="${gene.proteins}" varStatus="loop">
                                <a href="${protein.endpoint}/${protein.urlEncodedValue}">${protein.symbol}</a><c:if test="${!loop.last}">, </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td class="col-md-2">NCBI Gene ID</td>
                    <td class="col-md-10"><a href="${gene.ncbiEntrezGeneUrl}" target="_blank">${gene.ncbiEntrezGeneId}</a></td>
                </tr>
                <tr>
                    <td class="col-md-2">API</td>
                    <td class="col-md-10">
                        <a href="${Constant.API_URL}/${gene.endpoint}/${gene.urlEncodedValue}" target="_blank">
                            <button class="btn btn-default glyphicon glyphicon-cloud-download" data-toggle="tooltip" data-placement="right" title="Access ${gene.symbol} programmatically."></button>
                        </a>
                    </td>
                </tr>
            </table>
        </section>
        <section>
            <h2>Functional Associations</h2>
            <p class="instruction">
                <c:out value="${allAssociationsSummary}"/>
            </p>
            <p class="instruction">Click the + buttons to view associations for <c:out value="${gene.symbol}"></c:out> from the datasets below.</p>
            <table class="table entities-by-dataset genes">
                <thead>
                    <tr>
                        <th></th>
                        <th>Dataset</th>
                        <th>Summary</th>
                    </tr>
                </thead>
                <c:forEach var="dataset" items="${datasetsByGene}">
                    <tr class="dataset-row ${dataset.cssSelectorName}" >
                        <td class="col-md-1"
                            data-gene-list-css-selector="${dataset.cssSelectorName}"
                            data-gene-list-more-url="${Constant.API_URL}/${GeneSet.ENDPOINT}?gene=${gene.urlEncodedValue}&dataset=${dataset.urlEncodedValue}"
                        >
                            <button class="btn btn-default glyphicon glyphicon-plus cursor-pointer" aria-hidden="true"></button>
                            <button class="btn btn-default glyphicon glyphicon-minus hidden cursor-pointer" aria-hidden="true"></button>
                        </td>
                        <td class="col-md-3">
                            <a href="${dataset.endpoint}/${dataset.urlEncodedValue}">
                                <c:out value="${dataset.name}"/>
                            </a>
                        </td>
                        <td class="col-md-8 initial">
                            <c:set var="attributeSetDescription" value="${fn:replace(dataset.attributeSetDescription, '{0}', gene.symbol)}"/>
                            <c:out value="${attributeSetDescription}"/>
                        </td>
                    </tr>
                    <tr class="list attribute-list ${dataset.cssSelectorName}"></tr>
                </c:forEach>
            </table>
        </section>
    </div>
</t:wrapper>