<!-- Okay so I reviewed the code and this code needs some cleaning  - Juan Amado Cleto
    1. Removed the page value retrieve - we dont need it anymore since we are refactoring
    2. Removed unusualAction since we dont need that because refactoring
-->

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
    <form  method = "post">
        <label>Type In Artist Name: </label>
        <input type = "text" name = "artistName" required/>
        <button type ="submit" id ="getFromDatabase">Submit</button>
    </form>

<!--
<c:set var = "show" value="${not empty postAspects}" />
<div id = "goThroughArtWork" style = "${show ? 'display:block' : 'display:none'}">
   <c:set var = "seenNames" value = "," />
    <c:forEach var = "item" items = "${postAspects}">
    <c:if test = "${not fn:contains(seenNames, ','.concat(item.artistName).concat(','))}">
    <c:set var = "seenNames" value = "${seenNames}${item.artistName},"/>
     <p>
        <c:url var = "artLink" value = "/Artportfolio">
            <c:param name = "page" value = "retrieve"/>
            <c:param name = "Artistname" value = "${item.artistName}"/>
        </c:url>

        <a href = "${artLink}"><c:out value = "${item.artistName}"/></a>
     </p>
     </c:if>
    </c:forEach>
</div>
-->
</body>
</html>