<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
    <title>Incident by driver</title>
</head>
<body>
<div class="container">
    Incident by driver
    <br/><br/>

    <c:if test="${historicProcessInstance != null}">
        <span class="label label-success">Status : resolved</span>
        <br/><br/>
        Finished at :
        <fmt:formatDate value="${historicProcessInstance.endTime}" pattern="dd/MM/yyyy hh:mm" /><br/><br/>
    </c:if>

    <c:if test="${activeTask != null}">
        <b>Created at :</b>
        <fmt:formatDate value="${activeTask.createTime}" pattern="dd/MM/yyyy hh:mm" /><br/><br/>

        <b>Driver : </b><br/>
        ${alert.driver.id}<br/>
        ${alert.driver.firstName}<br/>
        ${alert.driver.lastName}<br/>
        ${alert.driver.email}<br/>

        <b>Drived truck :</b>
        ${alert.driver.truck.id}
        ${alert.driver.truck.identificationNumber}

        <br/><br/>
        <span class="label label-info"> Current status : </span>${activeTask.name} <br/>

        <br/><br/>
        <b>Comments for this incident :</b> <br/>

        ${comment}
        <br/><br/>


        <c:url var="completeTaskURL" value="/dashboard/complete-task"/>

        <form action="${completeTaskURL}" method="post">
            <input type="hidden" name="taskId" value="${activeTask.id}"/>
            <input type="hidden" name="alertId" value="${alert.id}"/>

            <!-- TODO Display comment in case of process finished ? -->
            <c:if test="${activeTask.name == 'Call driver'}">
                Driver responded ?
                <input name="driverResponded" type="radio" value="true" /> Yes
                <input name="driverResponded" type="radio" value="false" /> No

                <br/><br/>
            </c:if>

            <label for="comment">Add comment</label>
            <textarea id="comment" name="comment"></textarea>

            <br/><br/>
            <input type="submit" value="Set this task as complete" />
        </form>
    </c:if>

</div>

</body>
</html>