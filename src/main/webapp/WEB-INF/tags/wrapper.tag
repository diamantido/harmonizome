<%@ tag description="Wraps every Harmonizome page" pageEncoding="utf-8" %>
<%@ tag import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" %>
<%@ attribute name="description" %>
<%@ attribute name="extraKeywords" %>
<%@ attribute name="navType" %>
<%@ attribute name="pageWidth" %>
<%@ attribute name="userSearch" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <base href="/<%= Constant.HARMONIZOME_PREFIX %>/" />
        <%@include file="/template/fragment/globalIncludes.jsp" %>
        <c:choose>
            <c:when test="${not empty title}">
                <title><c:out value="${title}"/></title>
            </c:when>
            <c:otherwise>
                <title>Harmonizome</title>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${not empty description}">
                <meta name="description" content="${description}"/>
            </c:when>
            <c:otherwise>
                <meta name="description" content="Search for genes and functional terms extracted and organized from over a hundred publicly available resources."/>
            </c:otherwise>
        </c:choose>

        <c:set var="keywords" value="harmonizome, lincs, bd2k, dcic, bd2k-lincs dcic, data coordination and integration center,
                    biomedical, systems biology, drug discovery, gene signature,
                    genomics, transcriptomics, proteomics, metabolomics,
                    genes, proteins, cell lines, tissues, perturbations, diseases, phenotypes, drugs,
                    maayan lab, avi ma'ayan, avi maayan"/>
        <c:choose>
            <c:when test="${not empty extraKeywords}">
                <meta name="keywords" content="${extraKeywords}, ${keywords}"/>
            </c:when>
            <c:otherwise>
                <meta name="keywords" content="${keywords}"/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${pageWidth == 'full'}">
                <c:set var="bootstrapClass" value="container-full"/>
            </c:when>
            <c:otherwise>
                <c:set var="bootstrapClass" value="container"/>
            </c:otherwise>
        </c:choose>
    </head>
    <body>
        <c:choose>
            <c:when test="${navType == 'withSearch'}">
                <jsp:include page="/template/fragment/navbarWithSearch.jsp">
                    <jsp:param name="userSearch" value="${userSearch}"/>
                </jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="/template/fragment/navbar.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>
        <div class="wrapper">
            <div class="${bootstrapClass}">
                <jsp:doBody/>
            </div>
        </div>
        <%@include file="/template/fragment/footer.jsp" %>
    </body>
</html>