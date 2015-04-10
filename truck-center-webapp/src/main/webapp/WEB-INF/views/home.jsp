<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
<meta charset="UTF-8">
<title></title>
</head>
<body>

	Home page

	<form action="login-as-admin" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" value="login as admin" />
	</form>

<%--	<form action="login-as-driver" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit"
			value="login as driver" />
	</form>--%>

	<c:url var="logoutURL" value="/logout" />
	<form action="${logoutURL}" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" value="logout" />
	</form>

</body>
</html>