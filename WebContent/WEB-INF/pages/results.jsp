<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
    <c:url var = "search" value = "/Artportfolio">
        <c:param name = "page" value = "result"/>
    </c:url>




    <c:set var = "show" value="${not empty postAspects}" />
    <div id = "goThroughArtWork" style = "${show ? 'display:block' : 'display:none'}">
        <c:forEach var = "item" items = "${postAspects}">
         <p><img src = "data:image/png;base64,${item.convertedPicture}"></p>
         <p>${item.artistName}</p>
         <p>${item.artName}</p>
        </c:forEach>
    </div>


    <%@ include file="footer.jsp" %>
</body>
</html>