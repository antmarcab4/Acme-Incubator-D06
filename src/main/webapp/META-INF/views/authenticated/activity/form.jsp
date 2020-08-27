<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.activity.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.activity.form.label.start" path="start"/>
	<acme:form-moment code="authenticated.activity.form.label.end" path="end"/>
	<acme:form-money code="authenticated.activity.form.label.budget" path="budget"/>
	
	
	<acme:form-return code="authenticated.activity.form.button.return"/>
</acme:form>
