<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.investmentRound.list.label.title" path="title" readonly="true"/>
	<jstl:if test="${command != 'show'}">
	<acme:form-select code="authenticated.investmentRound.list.label.round" path="round" readonly="true">
		<acme:form-option code="SEED" value="SEED" selected="true"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES_A" value="SERIES A"/>
		<acme:form-option code="SERIES_B" value="SERIES B"/>
		<acme:form-option code="SERIES_C" value="SERIES C"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="authenticated.investmentRound.list.label.round" path="round" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="authenticated.investmentRound.list.label.description" path="description" readonly="true"/>
	<acme:form-moment code="authenticated.investmentRound.list.label.creation" path="creation" readonly="true"/>
	<acme:form-money code="authenticated.investmentRound.list.label.amount" path="amount" readonly="true"/>
	<acme:form-textbox code="authenticated.investmentRound.list.label.ticker" path="ticker" readonly="true"/>
	<acme:form-url code="authenticated.investmentRound.list.label.optionalLink" path="optionalLink" readonly="true"/>
	
	<acme:form-submit method="get" code="authenticated.investmentRound.form.label.activities" action="/authenticated/activity/list-from-investment?investmentRoundId=${id}"/> 
	
	<acme:form-return code="authenticated.investmentRound.button.return"/>
</acme:form>