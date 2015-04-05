<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="driver.info"/> </title>
</head>
<body>

<h1><spring:message code="driver.info"/></h1>
${driver.email}

${driver.firstName} -
${driver.lastName}
</body>
</html>
