<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<c:forEach var="Feed" items = "${sessionScope.artworkByArtistList}">
    <div class="win95-box artwork-card">
        <div class="win95-header">
            <span>Artwork_Viewer.exe</span>
            <span>X</span>
        </div>

        <!-- dummy artwork I got from the internet -->
        <img src = "data:image/png;base64,${Feed.convertedPicture}">

        <!-- dummy artwork info  -->
        <div class="artwork-info">
            <div class="artist-name">Artist: @${Feed.artistName}</div>
            <div class="artwork-title">Title: "${Feed.artName}"</div>
            <p>Posted: Oct 24, 1995</p>
            <form action="like" method="post">
                <input type="hidden" name="artistId" value="${Feed.artistId}">
                <button type="submit">Like</button>
            </form>
        </div>
    </div>
</c:forEach>


</body>


</html>