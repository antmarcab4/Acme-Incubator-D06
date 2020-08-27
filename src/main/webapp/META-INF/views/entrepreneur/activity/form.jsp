<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
	<acme:form-moment code="entrepreneur.activity.form.label.start" path="start"/>
	<acme:form-moment code="entrepreneur.activity.form.label.end" path="end"/>
	<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget"/>
	
	<acme:form-hidden path="investmentRoundId"/>
	
	<acme:form-submit test="${command == 'show'}"
  		code="entrepreneur.activity.form.button.update"
  		action="/entrepreneur/activity/update"/>
  		
  	<acme:form-submit test="${command == 'show'}"
  		code="entrepreneur.activity.form.button.delete"
  		action="/entrepreneur/activity/delete"/>
  		
  	<acme:form-submit method="post" test="${command == 'create'}"
  		code="entrepreneur.activity.form.button.create"
  		action="/entrepreneur/activity/create"/>
  		
  	<acme:form-submit test="${command == 'update'}"
  		code="entrepreneur.activity.form.button.update"
  		action="/entrepreneur/activity/update"/>
  		
  	<acme:form-submit test="${command == 'delete'}"
  		code="entrepreneur.activity.form.button.delete"
  		action="/entrepreneur/activity/delete"/>
	
	
	<acme:form-return code="entrepreneur.activity.form.button.return"/>
</acme:form>
