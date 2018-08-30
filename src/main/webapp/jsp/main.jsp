<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
</head>

<body>
<header>
    <h1>Файловый менеджер</h1>
</header>

<div class="wrapper">
    <c:set var="dbSize" scope="session" value="${size}"/>

    <c:if test="${dbSize == 0}">
        <h2>База данных пуста. Загрузите файл.</h2>
    </c:if>

</div>

<div class="login-form">

    <div class="content">
        <a href="jsp/upload.jsp" class="button">Загрузить картинку</a>
    </div>

</div>
<div class="container">
    <div class="grid">

    <c:forEach var="record" items="${records}">
        <figure class="effect-zoe">
            <img src="${record.getPathForPage()}" alt="${record.fileName}"/>
            <figcaption>
                <h2>${record.fileName}</h2>
                <span class="icon-heart"></span>
                <span class="icon-eye"></span>
                <span class="icon-paper-clip"></span>
                <p>${record.description}</p>
                <a href="${record.getPathForPage()}">View more</a>
            </figcaption>
        </figure>
    </c:forEach>
    </div>
        </div>
</body>
</html>
