<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html ng-app="qcmUsersApp">
<head>
    <title><spring:message code="drivers.list"/> </title>

    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body ng-controller="UserListController">

    <div class="container">

        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>

        <h1><spring:message code="drivers.list" /> </h1>


        <br />

        <table class="table">
            <thead>
            <tr>
                <td>Name</td>
                <td>First name</td>
                <td>Email</td>
                <td>Type</td>
                <td></td>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>
                            ${user.lastName}
                </td>
                <td>${user.firstName}</td>
                <td>${user.email}</td>
                <td>${user.roles[0].name}</td>
            </tr>
            </tbody>
        </table>


    </div>

</body>
</html>
