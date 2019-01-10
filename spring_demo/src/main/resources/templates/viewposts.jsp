<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posts</title>
<style>
td {
	text-align: center;
}
</style>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<br>
	<div class="container">
		<h1>All Posts Are :</h1>
	</div>
	<br>
	<center>
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<td><h2>Heading</h2>
					<td><h2>Contents</h2></td>
					<td><h2>Posted By</h2></td>
					<td><h2>Time</h2></td>
				</tr>
			</thead>
			<tbody>
				<tr th:each="post : ${posts}">
					<td th:text="${post.heading}" />
					<td th:text="${post.body}" />
					<td th:text="${post.userEmailId}" />
					<td th:text="${post.timestamp}" />
				</tr>
			</tbody>
		</table>
	</center>
</body>
</html>