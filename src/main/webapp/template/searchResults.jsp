<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet" %>

<t:wrapper title="${query}" navType="withSearch" pageWidth="full" userSearch="${query}">
    <div class="search-results-page">
        <div class="metadata container-full">
            <div class="container">
                <c:choose>
                    <c:when test="${isFilteredPage}">
                        <ul class="list-inline">
                            <li>
                                <span class="badge all">
                                    <a href="${Constant.SEARCH_URL}?q=${query}">Clear Filter</a>
                                </span>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <strong class="pull-left filters-text">Filters:</strong>
                        <ul class="list-inline pull-left filters">
                            <c:if test="${fn:length(datasets) != 0}">
                                <li>
                                    <span class="badge dataset">
                                        <a href="${Constant.SEARCH_URL}?q=${query}&t=dataset">Dataset</a>
                                    </span>
                                </li>
                            </c:if>
                            <c:if test="${fn:length(genes) != 0}">
                                <li>
                                    <span class="badge gene">
                                        <a href="${Constant.SEARCH_URL}?q=${query}&t=gene">Gene</a>
                                    </span>
                                </li>
                            </c:if>
                            <c:if test="${fn:length(geneSets) != 0}">
                                <li>
                                    <span class="badge gene-set">
                                        <a href="${Constant.SEARCH_URL}?q=${query}&t=geneSet">Gene Set</a>
                                    </span>
                                </li>
                            </c:if>
                        </ul>
                        <div class="clear"></div>
                    </c:otherwise>
                </c:choose>
                <p class="instruction"><c:out value="${summary}"/></p>
            </div>
        </div>
        <div class="container">
            <table class="table data-table">
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dataset" items="${datasets}">
                        <tr>
                            <td>
                                <h3>
                                    <a href="${dataset.endpoint}/${dataset.urlEncodedValue}">${dataset.name}</a> <span class="note dataset">Dataset</span>
                                </h3>
                                <div class="description">
                                    <p>
                                        From <a href="${dataset.resource.endpoint}/${dataset.resource.urlEncodedValue}"><c:out value="${dataset.resource.name}"/></a>
                                    </p>
                                    <p>${dataset.description} (${dataset.datasetGroup.name})</p>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:forEach var="gene" items="${genes}">
                        <tr>
                            <td>
                                <h3>
                                    <a href="${gene.endpoint}/${gene.urlEncodedValue}">${gene.symbol}</a> <span class="note gene">Gene</span>
                                </h3>
                                <div class="description">
                                    <c:if test="${gene.name != null}">
                                        <p>
                                            <em><c:out value="${gene.name}"/></em>
                                        </p>
                                    </c:if>
                                    <c:if test="${gene.description != null}">
                                        <p><c:out value="${gene.description}"/></p>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:forEach var="geneSet" items="${geneSets}">
                        <tr>
                            <td>
                                <h3>
                                    <a href="${GeneSet.ENDPOINT}/${geneSet.urlEncodedValue}">${geneSet.nameFromDataset}</a> <span class="note gene-set">Gene Set</span>
                                </h3>
                                <div class="description">
                                    <p><em>From <a href="${geneSet.dataset.endpoint}/${geneSet.dataset.urlEncodedValue}"><c:out value="${geneSet.dataset.name}"/></a></em></p>
                                    <p>${fn:replace(geneSet.dataset.geneSetDescription, "{0}", geneSet.nameFromDataset)}</p>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:wrapper>