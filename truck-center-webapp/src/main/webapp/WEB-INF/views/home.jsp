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

	<form action="login-as-teacher" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit"
			value="login as teacher" />
	</form>

	<form action="login-as-student" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit"
			value="login as student" />
	</form>

	<form action="${logoutURL}" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" value="Déconnexion" />
	</form>

</body>
</html>