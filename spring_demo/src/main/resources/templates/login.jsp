<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form th:action="@{login}" method="post">
                <div th:if="${param.error}">
                    Invalid username and/or password.
                </div>
                <div th:if="${param.logout}">
                    You have been logged out.
                </div>
            	<p>Email ID: <input type="text" name="username"/></p>
                <p>Password: <input type="password" name="password"/></p>
                <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
        </form>
    </body>
</html>