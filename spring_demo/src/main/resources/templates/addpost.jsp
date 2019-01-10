<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Post</title>
    </head>
    <body>
        <h1>Add Post</h1>

        <form th:action="@{/user/savepost}" th:object="${post}" method="post">
            	<p>Title: <input type="text" th:field="*{heading}" /></p>
                <p><textarea th:field="*{body}"></textarea></p>
                <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
        </form>
    </body>
</html>