<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	
	<acme:form-url code="authenticated.notice.form.label.header" path="header"/>
	<acme:form-textbox code="authenticated.notice.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.notice.form.label.body" path="body"/>
	<acme:form-moment code="authenticated.notice.form.label.creationDateTime" path="creationDateTime"/>
	<acme:form-moment code="authenticated.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.notice.form.label.optionalLinks" path="optionalLinks"/>
	
	<acme:form-return code="authenticated.notice.button.return"/>
</acme:form>