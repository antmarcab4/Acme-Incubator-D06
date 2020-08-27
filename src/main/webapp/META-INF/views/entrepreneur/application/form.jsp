
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.creation-moment" path="creationMoment" readonly="true"/>
	<acme:form-textarea  code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-money  code="entrepreneur.application.form.label.money-offer" path="moneyOffer" readonly="true"/>
	<acme:form-hidden path="iRoundId"/>
	
	
	<jstl:if test="${status == 'pending' && command == 'show'}">
    <acme:form-select code="entrepreneur.application.form.label.status" path="status">
    <acme:form-option code="entrepreneur.application.form.label.status.pending" value="pending"/>
    <acme:form-option code="entrepreneur.application.form.label.status.accepted" value="accepted"/>
    <acme:form-option code="entrepreneur.application.form.label.status.rejected" value="rejected"/>
    </acme:form-select>
    </jstl:if>
    


    <jstl:if test="${status == 'pending' && command == 'update'}">
    <acme:form-select code="entrepreneur.application.form.label.status" path="status">
    <acme:form-option code="entrepreneur.application.form.label.status.pending" value="pending"/>
    <acme:form-option code="entrepreneur.application.form.label.status.rejected" value="rejected"/>
    <acme:form-option code="entrepreneur.application.form.label.status.accepted" value="accepted"/>
    </acme:form-select>
    </jstl:if>
    
	<jstl:if test="${status == 'accepted' && command == 'update'}">
    <acme:form-select code="entrepreneur.application.form.label.status" path="status">
    <acme:form-option code="entrepreneur.application.form.label.status.accepted" value="accepted"/>
    <acme:form-option code="entrepreneur.application.form.label.status.rejected" value="rejected"/>
    <acme:form-option code="entrepreneur.application.form.label.status.pending" value="pending"/>
    </acme:form-select>
    </jstl:if>

    <jstl:if test="${status == 'rejected' && command == 'update'}">
    <acme:form-select code="entrepreneur.application.form.label.status" path="status">
    <acme:form-option code="entrepreneur.application.form.label.status.rejected" value="rejected"/>
    <acme:form-option code="entrepreneur.application.form.label.status.pending" value="pending"/>
    <acme:form-option code="entrepreneur.application.form.label.status.accepted" value="accepted"/>
    </acme:form-select>
    </jstl:if>

    <jstl:if test="${status != 'pending' && command == 'show'}">
        <acme:form-textarea code="entrepreneur.application.form.label.status" path="status" readonly="true"/>
    </jstl:if>
    

    <acme:form-textarea code="entrepreneur.application.form.label.justification" path="justification"/>


		
	
	<acme:form-submit test= "${command== 'show' }"
		method="get" code="entrepreneur.application.form.label.investmentRounds" action="/entrepreneur/investment-round/show_mine?applicationId=${id}"/>

    <acme:form-submit test ="${command == 'show' && status == 'pending'}" code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
    <acme:form-submit test ="${command == 'update'}" code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
    <acme:form-return code="entrepreneur.application.form.button.return"/>
	
	
	
	
	
</acme:form>
