<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:wrapper title="${resource.name}" navType="withSearch">
    <div class="resource-page">
        <h1><c:out value="${resource.name}"/> <span class="note">Resource</span></h1>
        <table class="table">
            <tr>
                <td class="col-md-2">Description</td>
                <td class="col-md-10">
                    <c:out value="${resource.longDescription}"/>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td class="col-md-2">Datasets</td>--%>
                <%--<td class="col-md-10">--%>
                    <%--<ul>--%>
                        <%--<c:forEach var="dataset" items="${resource.datasets}">--%>
                            <%--<li>--%>
                                <%--<a href="dataset/${dataset.urlEncodedValue}">--%>
                                    <%--<c:out value="${dataset.nameWithoutResource}"/>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <c:if test="${fn:length(publications) > 0}">
                <tr>
                    <td class="col-md-2">Citation(s)</td>
                    <td class="col-md-10">
                        <ul>
                            <c:forEach var="publication" items="${publications}">
                                <li>
                                    <a href="${publication.pubmedUrl}" target="_blank">
                                        <c:out value="${publication.longCitation}"/>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:if>
            <c:if test="${not empty resource.acronym}">
                <tr>
                    <td class="col-md-2">Acronym</td>
                    <td class="col-md-10">
                        <c:out value="${resource.acronym}"/>
                    </td>
                </tr>
            </c:if>
            <c:if test="${not empty resource.url}">
                <tr>
                    <td class="col-md-2">External Link</td>
                    <td class="col-md-10">
                        <a href="${resource.url}" target="_blank">
                            <c:out value="${resource.url}"/>
                        </a>
                    </td>
                </tr>
            </c:if>
        </table>
        <h2>Datasets</h2>
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>Dataset</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Attribute</th>
                        <th>Views</th>
                    </tr>
                </thead>
                <c:forEach var="dataset" items="${resource.datasets}">
                    <tr>
                        <td>${dataset.nameWithoutResource}</td>
                        <td>${dataset.description}</td>
                        <td>${dataset.datasetGroup.name}</td>
                        <td>${dataset.attributeType.name}</td>
                        <td>${dataset.numPageViews}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</t:wrapper>