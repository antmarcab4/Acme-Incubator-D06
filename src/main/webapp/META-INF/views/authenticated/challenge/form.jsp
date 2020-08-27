<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.challenge.form.label.objetivo-rookie" path="objetivoRookie"/>
	<acme:form-money code="authenticated.challenge.form.label.premio-rookie" path="premioRookie"/>
	<acme:form-textbox code="authenticated.challenge.form.label.objetivo-average" path="objetivoAverage"/>
	<acme:form-money code="authenticated.challenge.form.label.premio-average" path="premioAverage"/>
	<acme:form-textbox code="authenticated.challenge.form.label.objetivo-expert" path="objetivoExpert"/>
	<acme:form-money code="authenticated.challenge.form.label.premio-expert" path="premioExpert"/>
	
	
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>
