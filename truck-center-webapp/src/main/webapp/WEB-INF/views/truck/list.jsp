<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html ng-app="truckUsersApp">
<head>
    <title><spring:message code="trucks.list" /></title>

    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body >

<div class="container">

    <sec:authorize access="isAuthenticated()" >
        <%@include file="../menu/menuByRole.jsp"%>
    </sec:authorize>
    <h1><spring:message code="trucks.list"/></h1>

    <p><a href="<c:url value="/trucks/create"/>" class="btn btn-success"><spring:message code="truck.create"/></a></p>

    <c:choose>
        <c:when test="${fn:length(truckList) gt 0}">

            <table class="table table-striped">
                <thead>
                <tr>
                    <td><spring:message code="truck.identificationNumber"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${truckList}" var="truck">
                    <tr>
                        <td><a href="<c:url value='/truck/${truck.identificationNumber}'/>">${truck.identificationNumber}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </c:when>

        <c:otherwise>
            <spring:message code="no.truck.found"/>
        </c:otherwise>

    </c:choose>
</div>

</body>
</html>
