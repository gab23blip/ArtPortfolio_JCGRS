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

<div class="feed-container" style="margin-top: 5vh;">

    <div class="win95-box">
        <div class="win95-header">
            <span>UserActivity.exe</span>
            <span>X</span>
        </div>
        <div style="padding: 20px;">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/profile/user-activity/upload-art">Upload Your Work</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/profile/user-activity/search-artwork-by-artist">See other People's works</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/profile/user-activity/search-artist">Search Artist</a>
                </li>
                <li>
                    <!-- to do List artists here use a set if any duplicates within the table appears -->
                    <a>List Artist</a>
                </li>
                <li>
                    <a>Home feed</a>
                </li>
            </ul>
        </div>

    </div>

</body>
</html>