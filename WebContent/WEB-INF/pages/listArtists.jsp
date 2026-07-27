<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<c:forEach var="artist" items = "${sessionScope.artists}">
    <div class="win95-box artwork-card">
        <div class="win95-header">
            <span>Artwork_Viewer.exe</span>
            <span>X</span>
        </div>

        <div class="artwork-info">
            <div class="artist-name">Artist: @${artist}</div>
        </div>
    </div>
</c:forEach>


</body>