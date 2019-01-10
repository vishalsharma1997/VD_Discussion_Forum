<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
	<h1>Sign Up</h1>
	<form th:action="@{/user/save}" th:object="${user}" method="post">
    	<p>Email ID: <input type="text" th:field="*{emailId}" /></p>
        <p>Password: <input type="text" th:field="*{password}" /></p>
        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
    </form>
</body>
</html>