<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>


<body>
    <c:url var = "home" value = "/Artportfolio">
        <c:param name = "page" value = "homefeed"/>
    </c:url>

    <c:forEach var = "Feed" items = "${inset}">
        <p><img src = "data:image/png;base64,${Feed.convertedPicture}"></p>
        <p>${Feed.artName}</p>
        <p>${Feed.artistName}</p>
    </c:forEach>
    <%@ include file="footer.jsp" %>
</body>
</html>