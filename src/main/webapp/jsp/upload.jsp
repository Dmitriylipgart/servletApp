
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>


    <header>
        <h1>Загрузите файл</h1>

    </header>

    <form action="../uploadFile" method="post" enctype="multipart/form-data" class="form">

        <div class="content">
            <input type="text" name="description" class="input username" placeholder="Описание файла">
            <input type="file" name="file" style="display: none" id="fileUploadButton">
            <label for="fileUploadButton" class="button">Выбрать</label>
            <input type="submit" class="button" value="Загрузить">
        </div>

    </form>

</body>
</html>
