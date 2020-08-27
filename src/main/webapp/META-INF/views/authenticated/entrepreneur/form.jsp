<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="authenticated.entrepreneur.form.label.startupName" path="startupName"/>
	<acme:form-select code="authenticated.entrepreneur.form.label.sector" path="sector" readonly="true">
		<acme:form-option code="TECHNOLOGY" value="TECHNOLOGY"/>
		<acme:form-option code="SCIENCE" value="SCIENCE"/>
		<acme:form-option code="ARTS" value="ARTS"/>
		<acme:form-option code="BUSINESS" value="BUSINESS"/>
		<acme:form-option code="HEALTH" value="HEALTH"/>
	</acme:form-select>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.qualificationRecord" path="qualificationRecord"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.skillsRecord" path="skillsRecord"/>

	<acme:form-submit test="${command == 'create'}" code="authenticated.entrepreneur.form.button.create" action="/authenticated/entrepreneur/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.entrepreneur.form.button.update" action="/authenticated/entrepreneur/update"/>
	<acme:form-return code="authenticated.entrepreneur.form.button.return"/>


</acme:form> 