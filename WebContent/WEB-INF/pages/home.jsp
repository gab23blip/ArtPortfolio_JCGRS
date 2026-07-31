<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div style="margin-left: auto; display: flex; gap: 10px; align-items: center;">
            <a href="${pageContext.request.contextPath}/profile/user-activity"><button class="win95-button" type="button">User Activity</button></a>
            <a href="${pageContext.request.contextPath}/profile"><button class="win95-button" type="button">Profile</button></a>
            <a href="${pageContext.request.contextPath}/settings"><button class="win95-button" type="button">Settings</button></a>
            <a href="${pageContext.request.contextPath}/logout"><button class="win95-button" type="button">Logout</button></a>
        </div>
    </div>
    
    <div class="feed-container">
        
        <div class="win95-box welcome-card">
            <div class="win95-header">
                <span>System_Status.exe</span>
                <span class="status-chip">LIVE</span>
            </div>
            <div class="welcome-panel">
                <p>Welcome back, <strong>${sessionScope.user.username}</strong>!</p>
                <p>Browse the latest artist posts and discover new work in your feed.</p>
            </div>
        </div>

        <c:choose>
            <c:when test="${not empty feedPosts}">
                <c:set var="heroPost" value="${feedPosts[0]}" />
                <div class="win95-box artwork-card">
                    <div class="win95-header">
                        <span>Artwork_Viewer.exe</span>
                        <span>X</span>
                    </div>
                    <img src="data:image/png;base64,${heroPost.convertedPicture}" alt="${heroPost.artName}">
                    <div class="artwork-info">
                        <div class="artist-name">Artist: ${heroPost.artistName}</div>
                        <div class="artwork-title">Title: "${heroPost.artName}"</div>
                        <p>Latest upload from the community</p>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="win95-box artwork-card">
                    <div class="win95-header">
                        <span>Artwork_Viewer.exe</span>
                        <span>X</span>
                    </div>
                    <img src="https://via.placeholder.com/900x500" alt="Placeholder Art">
                    <div class="artwork-info">
                        <div class="artist-name">Artist: @SambaTheGreat</div>
                        <div class="artwork-title">Title: "Retro Dreams"</div>
                        <p>Posted: Oct 24, 1995</p>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <div class="feed-grid">
            <c:forEach var="post" items="${feedPosts}" varStatus="status">
                <c:if test="${status.index > 0}">
                    <div class="win95-box feed-card">
                        <div class="win95-header">
                            <span>Feed Item</span>
                            <span>X</span>
                        </div>
                        <img src="data:image/png;base64,${post.convertedPicture}" alt="${post.artName}">
                        <div class="artwork-info">
                            <div class="artist-name">Artist: ${post.artistName}</div>
                            <div class="artwork-title">Title: "${post.artName}"</div>
                            <p>${post.artistName}'s latest upload</p>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <c:if test="${empty feedPosts}">
                <div class="win95-box feed-card">
                    <div class="win95-header">
                        <span>Recent Post</span>
                        <span>X</span>
                    </div>
                    <img src="https://via.placeholder.com/900x420" alt="Galaxy Glow Art">
                    <div class="artwork-info">
                        <div class="artist-name">Artist: @NeonNova</div>
                        <div class="artwork-title">Title: "Galaxy Glide"</div>
                        <p>New submission · 12 min ago</p>
                    </div>
                </div>
                <div class="win95-box feed-card">
                    <div class="win95-header">
                        <span>Featured Artist</span>
                        <span>X</span>
                    </div>
                    <img src="https://via.placeholder.com/900x420" alt="Dream Frame Art">
                    <div class="artwork-info">
                        <div class="artist-name">Artist: @PixelPixie</div>
                        <div class="artwork-title">Title: "Dream Frame"</div>
                        <p>Featured in today’s spotlight</p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

</body>
</html>