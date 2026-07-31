<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to ArtPortfolio</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    
    <div class="feed-container" style="margin-top: 10vh;">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>Welcome.exe</span>
                <span>X</span>
            </div>
            
            <div style="padding: 30px; text-align: center;">
                <h1 style="margin-top: 0;">Welcome to ArtPortfolio</h1>
                <p>Please log in or register to manage your portfolio and preferences.</p>

                <div style="margin-top: 30px; display: flex; justify-content: center; gap: 20px;">
                    <a href="${pageContext.request.contextPath}/login">
                        <button class="win95-button" type="button">Login</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/register">
                        <button class="win95-button" type="button">Register</button>
                    </a>
                </div>
            </div>
        </div>

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>