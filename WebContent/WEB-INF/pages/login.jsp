<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - ArtPortfolio</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>

    <div class="feed-container" style="margin-top: 5vh;">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>Login.exe</span>
                <span>X</span>
            </div>
            
            <div style="padding: 20px;">
                
                <c:if test="${param.registered eq 'true'}">
                    <p style="color: #00FF33; font-weight: bold; background: #000; padding: 5px; border: 1px solid #00FF33;">
                        > Registration successful! Please login below.
                    </p>
                </c:if>
                
                <% if (request.getAttribute("error") != null) { %>
                    <p style="color: #FF0000; font-weight: bold; background: #000; padding: 5px; border: 1px solid #FF0000;">
                        > ERROR: <%= request.getAttribute("error") %>
                    </p>
                <% } %>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <p style="margin-bottom: 5px; font-weight: bold;">Username or Email:</p>
                    <input type="text" id="usernameOrEmail" name="usernameOrEmail" required style="width: 90%; margin-bottom: 15px; font-family: inherit; padding: 5px;">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Password:</p>
                    <input type="password" id="password" name="password" required style="width: 90%; margin-bottom: 20px; font-family: inherit; padding: 5px;">
                    
                    <br>
                    <button type="submit" class="win95-button">Login</button>
                </form>

                <hr style="border-top: 2px dashed var(--border-dark); margin: 20px 0;">
                
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/register" style="color: var(--text-main); font-weight: bold;">Register here</a>.</p>
            </div>
        </div>

        <div class="win95-box">
            <div class="win95-header">
                <span>Display_Settings.exe</span>
            </div>
            <div style="padding: 15px; display: flex; justify-content: space-around; flex-wrap: wrap; gap: 10px;">
                <button class="win95-button" type="button" onclick="setTheme('light')">Psychedelic</button>
                <button class="win95-button" type="button" onclick="setTheme('dark')">Dark Mode</button>
                <button class="win95-button" type="button" onclick="setTheme('contrast')">Contrast</button>
            </div>
        </div>

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>