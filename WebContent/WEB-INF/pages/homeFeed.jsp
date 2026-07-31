<!-- I am just going to leave a comment -- Juan Amado Cleto
    We dont need this homeFeed -- we already have home
    But -- and this is a big butt -- I am going to reuse the foreach loop and
    transfer it to the home.jsp (the actual homefeed)

-->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>


<body>
    <c:url var = "home" value = "/Artportfolio">
        <c:param name = "page" value = "homefeed"/>
    </c:url>

    <c:forEach var = "Feed" items = "${insert}">
        <p><img src = "data:image/png;base64,${Feed.convertedPicture}"></p>
        <p>${Feed.artName}</p>
        <p>${Feed.artistName}</p>
    </c:forEach>
    <%@ include file="footer.jsp" %>
</body>
</html>