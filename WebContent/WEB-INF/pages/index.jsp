<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <c:url var = "Insert" value = "/Artportfolio">
        <c:param name = "page" value = "insert"/>
    </c:url>


    <c:url var = "search" value = "/Artportfolio">
        <c:param name = "page" value = "search"/>
    </c:url>

    <c:url var = "home" value = "/Artportfolio">
        <c:param name = "page" value = "homefeed"/>
    </c:url>

    <a href = "${Insert}">Photo Submission</a>
    <a href = "${search}">Artist Viewing</a>
    <a href = "${home}">Home Feed</a>
    <%@ include file="footer.jsp" %>
</body>
</html>