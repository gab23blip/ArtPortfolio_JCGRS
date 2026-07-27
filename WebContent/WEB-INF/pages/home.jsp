<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- Include this next time for jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>

    <div class="nav-header win95-box">
        <h2>HOME FEED</h2>
        <div style="margin-left: auto;">
            <a href="${pageContext.request.contextPath}/profile/user-activity"><button class="win95-button" type="button">User Activity</button></a>
            <a href="${pageContext.request.contextPath}/profile"><button class="win95-button" type="button">Profile</button></a>
            <a href="${pageContext.request.contextPath}/settings"><button class="win95-button" type="button">Settings</button></a>
            <a href="${pageContext.request.contextPath}/logout"><button class="win95-button" type="button">Logout</button></a>
        </div>
    </div>

    <div class="feed-container">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>System_Status.exe</span>
            </div>
            <div style="padding: 15px;">
                <p>Welcome, <strong>${sessionScope.user.username}</strong>!</p>
            </div>
        </div>

        <div class="win95-box artwork-card">
            <div class="win95-header">
                <span>Artwork_Viewer.exe</span>
                <span>X</span>
            </div>

            <!-- dummy artwork I got from the internet -->
            <img src="https://thumbs.dreamstime.com/b/cute-girl-long-pink-hair-hair-coloring-beautiful-woman-pink-background-white-dress-colored-hair-perfect-cute-girl-195568566.jpg" alt="Placeholder Art">

            <!-- dummy artwork info  -->
            <div class="artwork-info">
                <div class="artist-name">Artist: @SambaTheGreat</div>
                <div class="artwork-title">Title: "Retro Dreams"</div>
                <p>Posted: Oct 24, 1995</p>
            </div>

        </div>
        <!--
        <c:forEach var="Feed" items = "${insert}">
            <img src = "data:image/png;base64,${Feed.convertedPicture}">
            <p>${Feed.artName}</p>
            <p>${Feed.artistName}</p>
        </c:forEach> -->

        <c:forEach var="Feed" items = "${insert}">
        <div class="win95-box artwork-card">
            <div class="win95-header">
                <span>Artwork_Viewer.exe</span>
                <span>X</span>
            </div>

            <!-- dummy artwork I got from the internet -->
            <img src = "data:image/png;base64,${Feed.convertedPicture}">

            <!-- dummy artwork info  -->
            <!-- next time do not have div tag for texts -->
            <div class="artwork-info">
                <div class="artist-name">Artist: @${Feed.artistName}</div>
                <div class="artwork-title">Title: "${Feed.artName}"</div>
                <p>Posted: Oct 24, 1995</p>
                <div class="likeCount">Number of likes: ${Feed.likeCount}</div>
            </div>
            <form action="like" method="post">
                <input type="hidden" name="artistId" value="${Feed.artistId}">
                <button type="submit">Like</button>
            </form>
        </div>
        </c:forEach>

    </div>

</body>
</html>