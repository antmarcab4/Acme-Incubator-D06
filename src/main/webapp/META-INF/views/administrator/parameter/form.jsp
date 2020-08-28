<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-double code="administrator.parameter.form.label.spamthreshold" path="spamthreshold"/>
	<acme:form-textarea code="administrator.parameter.form.label.spamwords" path="spamwords"/>
	<acme:form-textarea code="administrator.parameter.form.label.activitySector" path="activitySector"/>
    
    <acme:form-submit test="${command == 'show'}" code= "administrator.parameter.form.button.update" action= "/administrator/parameter/update"/>
  	<acme:form-submit test="${command == 'update'}" code= "administrator.parameter.form.button.update" action= "/administrator/parameter/update"/>
  
  	<acme:form-return code="administrator.parameter.form.button.return"/>
</acme:form>