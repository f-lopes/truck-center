<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form  method="POST" action="add" commandName="addDriverForm" class="form-signin form" >

    <form:errors path="" />

    <div class="form-group">
        <form:label path="email"/>
        <form:input path="email" class="input-block-level form-control" placeholder="Email address"/>
        <form:errors path="email" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="firstName"/>
        <form:input path="firstName" class="input-block-level form-control" placeholder="First name"/>
        <form:errors path="firstName" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="lastName"/>
        <form:input path="lastName" class="input-block-level form-control" placeholder="Last name"/>
        <form:errors path="lastName" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="password"/>
        <form:password path="password" class="input-block-level form-control" placeholder="Password"/>
        <form:errors path="password" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="truck"/>
        <form:select path="truck" id="truck"  itemLabel="identificationNumber" itemValue="id" items="${trucks}"  class="form-control"/>
    </div>

    <button class="btn btn-large btn-primary" type="submit"><spring:message code="driver.add.title"/></button>
</form:form>