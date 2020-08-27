<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="investor.application.list.label.ticker" path="ticker" width="30%"/>
	<acme:list-column code="investor.application.list.label.creation-moment" path="creationMoment" width="40%"/>
	<acme:list-column code="investor.application.list.label.money-offer" path="moneyOffer" width="30%"/>

</acme:list>