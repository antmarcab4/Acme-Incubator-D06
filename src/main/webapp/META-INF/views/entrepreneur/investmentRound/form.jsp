<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.investmentRound.list.label.title" path="title"/>
	
	<jstl:if test="${command == 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="SERIES C" value="SERIES_C"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${round == 'SEED' && command != 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="SERIES C" value="SERIES_C"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${round == 'ANGEL' && command != 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="SERIES C" value="SERIES_C"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${round == 'SERIES_A' && command != 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="SERIES C" value="SERIES_C"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${round == 'SERIES_B' && command != 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SERIES C" value="SERIES_C"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${round == 'SERIES_C' && command != 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="SERIES C" value="SERIES_C"/>
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${round == 'BRIDGE' && command != 'create'}">
	<acme:form-select code="entrepreneur.investmentRound.list.label.round" path="round">
		<acme:form-option code="BRIDGE" value="BRIDGE"/>
		<acme:form-option code="SEED" value="SEED"/>
		<acme:form-option code="ANGEL" value="ANGEL"/>
		<acme:form-option code="SERIES A" value="SERIES_A"/>
		<acme:form-option code="SERIES B" value="SERIES_B"/>
		<acme:form-option code="SERIES C" value="SERIES_C"/>
	</acme:form-select>
	</jstl:if>
	
	<acme:form-textarea code="entrepreneur.investmentRound.list.label.description" path="description"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="entrepreneur.investmentRound.list.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-money code="entrepreneur.investmentRound.list.label.amount" path="amount"/>
	<acme:form-textbox code="entrepreneur.investmentRound.list.label.ticker" path="ticker"/>
	<jstl:if test="${command != 'show'}">
	<acme:form-url code="entrepreneur.investmentRound.list.label.optionalLink" path="optionalLink"/>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="entrepreneur.investmentRound.list.label.optionalLink" path="optionalLink"/>
	</jstl:if>
	<jstl:if test="${command != 'create' && finalMode == 'false' || command == 'update' && finalMode == 'true'}">
    <acme:form-select code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode">
    	<acme:form-option code="entrepreneur.investmentRound.form.label.false" value="false"/>
    	<acme:form-option code="entrepreneur.investmentRound.form.label.true" value="true"/>
    </acme:form-select>
    
	</jstl:if>    
    <acme:form-submit test="${command == 'show'  && finalMode == 'false'}"
  		code="entrepreneur.investmentRound.form.button.update"
  		action="/entrepreneur/investment-round/update"/>
  		
  	<acme:form-submit test="${command == 'show'}"
  		code="entrepreneur.investmentRound.form.button.delete"
  		action="/entrepreneur/investment-round/delete"/>
  		
  	<acme:form-submit method="post" test="${command == 'create'}"
  		code="entrepreneur.investmentRound.form.button.create"
  		action="/entrepreneur/investment-round/create"/>
  		
  	<acme:form-submit test="${command == 'update'}"
  		code="entrepreneur.investmentRound.form.button.update"
  		action="/entrepreneur/investment-round/update"/>
  		
  	<acme:form-submit test="${command == 'delete'}"
  		code="entrepreneur.investmentRound.form.button.delete"
  		action="/entrepreneur/investment-round/delete"/>
  		
  	<jstl:if test="${command != 'create' && finalMode == 'false'}">
	<acme:form-submit method="get" code="entrepreneur.investmentRound.form.label.createActivities" action="/entrepreneur/activity/create?investmentRoundId=${id}"/>
	</jstl:if>
  		
	<jstl:if test="${command != 'create' }">
	<acme:form-submit method="get" code="entrepreneur.investmentRound.form.label.activities" action="/entrepreneur/activity/list-from-investment?investmentRoundId=${id}"/>
	</jstl:if>
	
	<acme:form-return code="entrepreneur.investmentRound.button.return"/>
</acme:form>