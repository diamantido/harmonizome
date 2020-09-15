<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Visualize">

	<script src="script/visualization.js"></script>
    <iframe src="${Constant.HARMONOGRAM_URL()}/"></iframe>

</t:wrapper>
