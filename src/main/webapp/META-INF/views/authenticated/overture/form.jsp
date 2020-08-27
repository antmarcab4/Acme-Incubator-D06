<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.overture.list.label.title" path="title" readonly="true"/>
	<acme:form-moment code="authenticated.overture.list.label.creation" path="creation" readonly="true"/>
	<acme:form-moment code="authenticated.overture.list.label.deadline" path="deadline" readonly="true"/>
	<acme:form-textarea code="authenticated.overture.list.label.description" path="description" readonly="true"/>
	<acme:form-textbox code="authenticated.overture.list.label.email" path="email" readonly="true"/>
	<acme:form-money code="authenticated.overture.list.label.minMoney" path="minMoney" readonly="true"/>
	<acme:form-money code="authenticated.overture.list.label.maxMoney" path="maxMoney" readonly="true"/>
	
	<acme:form-return code="authenticated.overture.button.return"/>
</acme:form>