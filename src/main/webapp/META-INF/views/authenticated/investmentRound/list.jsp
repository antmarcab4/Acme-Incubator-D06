<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.investmentRound.list.label.title" path="title" width="30%"/>
	<acme:list-column code="authenticated.investmentRound.list.label.round" path="round" width="20%"/>
	<acme:list-column code="authenticated.investmentRound.list.label.amount" path="amount" width="20%"/>
	<acme:list-column code="authenticated.investmentRound.list.label.creation" path="creation" width="30%"/>
</acme:list>
