<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<br/>
<style>
    .nav a {
        color: white;
    }
</style>

<nav class="navbar navbar-default" style="font-weight: bold; background-color: white;">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" style="color:black;" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/"/>"><spring:message code="application.home"/></a></li>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <%@include file="../menu/adminMenu.jsp" %>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_DRIVER')">
                    <%@include file="../menu/driverMenu.jsp" %>
                </sec:authorize>
            </ul>
            <c:url var="logoutURL" value="/logout" />
            <form action="${logoutURL}" method="POST" class="navbar-form navbar-right">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="<spring:message code="logout"/>" class="btn btn-default"/>
            </form>
        </div>
    </div>
</nav>

<!-- Flash -->
<div style="width:1000px; margin:auto;" class="alert alert-${flash.status}">${flash.text}</div>
