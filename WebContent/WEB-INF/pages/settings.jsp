<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings - ArtPortfolio</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>

    <div class="nav-header win95-box">
        <h2>SETTINGS</h2>
        <div style="margin-left: auto;">
            <a href="${pageContext.request.contextPath}/profile"><button class="win95-button" type="button">Cancel</button></a>
        </div>
    </div>

    <div class="feed-container">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>Config_Panel.exe</span>
                <span>X</span>
            </div>
            
            <div style="padding: 20px;">
                
                <% if (request.getAttribute("error") != null) { %>
                    <p style="color: #FF0000; font-weight: bold; background: #000; padding: 5px; border: 1px solid #FF0000;">
                        > ERROR: <%= request.getAttribute("error") %>
                    </p>
                <% } %>

                <form action="${pageContext.request.contextPath}/settings" method="post">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Change Username:</p>
                    <input type="text" id="username" name="username" placeholder="${sessionScope.user.username}" style="width: 90%; margin-bottom: 15px; font-family: inherit; padding: 5px;">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Change Email:</p>
                    <input type="email" id="email" name="email" placeholder="${sessionScope.user.email}" style="width: 90%; margin-bottom: 15px; font-family: inherit; padding: 5px;">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Change Password:</p>
                    <input type="password" id="password" name="password" placeholder="Leave blank to keep current" style="width: 90%; margin-bottom: 15px; font-family: inherit; padding: 5px;">
                    
                    <p style="margin-bottom: 5px; font-weight: bold;">Theme Preference (System & Account):</p>
                    <div style="margin-bottom: 20px; font-family: inherit;">
                        <input type="radio" id="light" name="theme" value="light" ${cookie.theme.value != 'dark' ? 'checked' : ''} onclick="setTheme('light')">
                        <label for="light">Psychedelic Mode</label><br>
                        
                        <input type="radio" id="dark" name="theme" value="dark" ${cookie.theme.value == 'dark' ? 'checked' : ''} onclick="setTheme('dark')">
                        <label for="dark">Dark Mode</label><br>
                        
                        <input type="radio" id="contrast" name="theme" value="contrast" ${cookie.theme.value == 'contrast' ? 'checked' : ''} onclick="setTheme('contrast')">
                        <label for="contrast">Contrast Mode</label>
                    </div>

                    <hr style="border-top: 2px dashed var(--border-dark); margin: 20px 0;">
                    
                    <div style="display: flex; gap: 10px; flex-wrap: wrap;">
                        <button type="submit" class="win95-button">Save Settings</button>
                        <a href="${pageContext.request.contextPath}/settings?action=delete">
                            <button type="button" class="win95-button" style="background-color: #FF0000; color: #FFF;">Delete Account</button>
                        </a>
                    </div>
                </form>

            </div>
        </div>

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>