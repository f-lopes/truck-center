<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

Alerts

<a href="<c:url value='/dashboard?resolved=true'/>">Resolved alerts</a>

<table class="table table-striped">
    <thead>
    <tr>
        <td>Id</td>
        <td>Date</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${alerts}" var="alert">
        <tr>
            <td><a href="<c:url value='/dashboard/incident-status-by-alert/${alert.id}'/>">${alert.id}</a></td>
            <td><fmt:formatDate value="${alert.date}" pattern="dd/MM/yyyy hh:mm" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>