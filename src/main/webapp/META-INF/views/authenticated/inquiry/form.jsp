<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.inquiry.list.label.title" path="title" readonly="true"/>
	<acme:form-moment code="authenticated.inquiry.list.label.creation" path="creation" readonly="true"/>
	<acme:form-moment code="authenticated.inquiry.list.label.deadline" path="deadline" readonly="true"/>
	<acme:form-textarea code="authenticated.inquiry.list.label.description" path="description" readonly="true"/>
	<acme:form-textbox code="authenticated.inquiry.list.label.email" path="email" readonly="true"/>
	<acme:form-money code="authenticated.inquiry.list.label.minMoney" path="minMoney" readonly="true"/>
	<acme:form-money code="authenticated.inquiry.list.label.maxMoney" path="maxMoney" readonly="true"/>
	
	<acme:form-return code="authenticated.inquiry.button.return"/>
</acme:form>