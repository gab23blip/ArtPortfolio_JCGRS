<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>


<body>

    <!--
    <c:url var = "Insert" value = "/Artportfolio">
        <c:param name = "page" value = "insert"/>
    </c:url> -->
    <h4>Art Details And Work</h4>
    <form method = "post" enctype="multipart/form-data">
        <label>Artist Name: </label>
        <input type = "text" name ="artist"/>
        <label>Art Piece Name: </label>
        <input type = "text" name ="artName"/>
        <label>Insert Picture Of Art Piece: </label>
        <input type="file" name="Picture"/>
        <!-- Okay so we are removing unusual action
            1. We dont need it anymore since we are splitting the original MyServlet now InsertServlet into many pieces
        -->
        <!--<input hidden type = "text" name ="unusualAction" value ="picture"/>-->
        <button type = "submit">Submit Details</button>
    </form>
    <%@ include file="footer.jsp" %>
</body>
</html>