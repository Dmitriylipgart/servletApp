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

<div class="form">

    <div class="content">
        <a href="jsp/upload.jsp" class="button">Загрузить картинку</a>
    </div>

</div>
<div class="container">
    <div class="grid">

    <c:forEach var="record" items="${records}">
        <figure style="background-image: url('${record.getPathForPage()}')">
            <form action="delete" method="post" enctype="multipart/form-data">
                <input type="text" name="file_id"  value="${record.file_id}" style="display: none">
                <input type="text" name="fileName"  value="${record.fileName}" style="display: none">
                <input type="submit" id="deleteButton + ${record.file_id}" style="display: none">
                <label for="deleteButton + ${record.file_id}"><i class="fas fa-times-circle"></i></label>
            </form>
            <figcaption>
                <h2>${record.fileName}</h2>
                <a href="${record.getPathForPage()}" download><i class="fas fa-arrow-alt-circle-down"></i></a>
                <p>${record.description}</p>
            </figcaption>
        </figure>
    </c:forEach>
    </div>
        </div>
</body>
</html>
