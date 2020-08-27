<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="investor.application.form.label.creation-moment" path="creationMoment" readonly="true"/>
		<acme:form-textarea code="investor.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN"/>
	<acme:form-textarea  code="investor.application.form.label.statement" path="statement"/>
	<acme:form-money  code="investor.application.form.label.money-offer" path="moneyOffer"/>
	<acme:form-hidden path="iRoundId"/>
	
	<acme:form-submit test= "${command== 'show' }"
		method="get" code="investor.application.form.label.investmentRounds" action="/investor/investment-round/show_mine?applicationId=${id}"/>
	
	<acme:form-submit test= "${command== 'create' }"
		code="investor.application.form.button.create" action="/investor/application/create"/>


	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>
