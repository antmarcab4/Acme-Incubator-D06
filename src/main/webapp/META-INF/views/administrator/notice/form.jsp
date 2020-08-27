<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


	<acme:form>

	
	<acme:form-url code="administrator.notice.form.label.header" path="header"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.notice.form.label.creationDateTime" path="creationDateTime" readonly="true"/>
	</jstl:if>
		<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.notice.form.label.optionalLinks" path="optionalLinks"/>
	
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="administrator.notice.checkbox.confirmCreate" path="checkbox"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}"
		code="administrator.notice.form.button.create"
		action="/administrator/notice/create"/>
	
	<acme:form-return code="administrator.notice.button.return"/>
</acme:form>