<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form  method="POST" action="add" commandName="addTruckForm" class="form-signin form" >

    <form:errors path="" />

    <div class="form-group">
        <form:label path="identificationNumber"/>
        <form:input path="identificationNumber" class="input-block-level form-control" placeholder="Identification number"/>
        <form:errors path="identificationNumber" cssClass="error" />
    </div>

    <button class="btn btn-large btn-primary" type="submit"><spring:message code="truck.add.title"/></button>
</form:form>