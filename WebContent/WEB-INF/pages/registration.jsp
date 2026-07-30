<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration - ArtPortfolio</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>

    <div class="feed-container" style="margin-top: 5vh;">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>Register_New_User.exe</span>
                <span>X</span>
            </div>
            
            <div style="padding: 20px;">
                
                <% if (request.getAttribute("error") != null) { %>
                    <p style="color: #FF0000; font-weight: bold; background: #000; padding: 5px; border: 1px solid #FF0000;">
                        > ERROR: <%= request.getAttribute("error") %>
                    </p>
                <% } %>

                <form action="${pageContext.request.contextPath}/register" method="post">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Username:</p>
                    <input type="text" id="username" name="username" required style="width: 90%; margin-bottom: 15px; font-family: inherit; padding: 5px;">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Email:</p>
                    <input type="email" id="email" name="email" required style="width: 90%; margin-bottom: 15px; font-family: inherit; padding: 5px;">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Password:</p>
                    <input type="password" id="password" name="password" required style="width: 90%; margin-bottom: 20px; font-family: inherit; padding: 5px;">
                    
                    <br>
                    <button type="submit" class="win95-button">Register</button>
                </form>

                <hr style="border-top: 2px dashed var(--border-dark); margin: 20px 0;">
                
                <p>Already have an account? <a href="${pageContext.request.contextPath}/login" style="color: var(--text-main); font-weight: bold;">Login here</a>.</p>
            </div>
        </div>

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>