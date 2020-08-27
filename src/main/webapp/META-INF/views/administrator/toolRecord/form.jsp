<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.toolRecord.list.label.title" path="title"/>
	<acme:form-select code="administrator.toolRecord.list.label.activitySector" path="activitySector">
		<acme:form-option code="TECHNOLOGY" value="TECHNOLOGY" selected="true"/>
		<acme:form-option code="SCIENCE" value="SCIENCE"/>
		<acme:form-option code="ARTS" value="ARTS"/>
		<acme:form-option code="BUSINESS" value="BUSINESS"/>
		<acme:form-option code="HEALTH" value="HEALTH"/>
	</acme:form-select>
	<acme:form-textbox code="administrator.toolRecord.list.label.inventor" path="inventor"/>
	<acme:form-textarea code="administrator.toolRecord.list.label.description" path="description"/>
	<acme:form-url code="administrator.toolRecord.list.label.webSite" path="webSite"/>
	<acme:form-textbox code="administrator.toolRecord.list.label.email" path="email"/>
	<acme:form-checkbox code="administrator.toolRecord.list.label.openSource" path="openSource"/>
	<acme:form-textbox code="administrator.toolRecord.list.label.stars" path="stars"/>
	
	<acme:form-submit test ="${command == 'show'}"
		code="administrator.toolRecord.form.button.update"
		action="/administrator/tool-record/update"/>
	<acme:form-submit test ="${command == 'show'}"
		code="administrator.toolRecord.form.button.delete"
		action="/administrator/tool-record/delete"/>
	<acme:form-submit test ="${command == 'create'}"
		code="administrator.toolRecord.form.button.create"
		action="/administrator/tool-record/create"/>
	<acme:form-submit test ="${command == 'update'}"
		code="administrator.toolRecord.form.button.update"
		action="/administrator/tool-record/update"/>
	<acme:form-submit test ="${command == 'delete'}"
		code="administrator.toolRecord.form.button.delete"
		action="/administrator/tool-record/delete"/>
	
	<acme:form-return code="administrator.toolRecord.button.return"/>
</acme:form>