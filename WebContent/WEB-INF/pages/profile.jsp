<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - ArtPortfolio</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>

    <div class="nav-header win95-box">
        <h2>USER PROFILE</h2>
        <div style="margin-left: auto;">
            <a href="${pageContext.request.contextPath}/profile/user-activity"><button class="win95-button" type="button">User Activity</button></a>
            <a href="${pageContext.request.contextPath}/home"><button class="win95-button" type="button">Back to Home</button></a>
        </div>
    </div>

    <div class="feed-container">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>Profile_Data.exe</span>
                <span>X</span>
            </div>
            
            <div style="padding: 20px;">
                
                <c:if test="${param.updated eq 'true'}">
                    <p style="color: #00FF33; font-weight: bold; background: #000; padding: 5px; border: 1px solid #00FF33;">
                        > SYSTEM UPDATE: Profile settings updated successfully!
                    </p>
                </c:if>

                <p><strong>User ID:</strong> ${sessionScope.user.userId}</p>
                <p><strong>Username:</strong> ${sessionScope.user.username}</p>
                <p><strong>Email:</strong> ${sessionScope.user.email}</p>
                
                <hr style="border-top: 2px dashed var(--border-dark); margin: 20px 0;">
                
                <a href="${pageContext.request.contextPath}/settings">
                    <button class="win95-button" type="button">Edit Settings</button>
                </a>
            </div>
        </div>

        <div class="win95-box">
            <div class="win95-header">
                <span>My_Artworks.folder</span>
                <span>-</span>
            </div>
            <div style="padding: 20px; text-align: center;">
                <p><em>[Your uploaded artworks will display here]</em></p>
                </div>
        </div>

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>