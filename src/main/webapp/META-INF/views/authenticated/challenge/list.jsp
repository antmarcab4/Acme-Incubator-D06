<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.challenge.list.label.title" path="title" width="30%"/>
	<acme:list-column code="authenticated.challenge.list.label.deadline" path="deadline" width="25%"/>
	<acme:list-column code="authenticated.challenge.list.label.premio-rookie" path="premioRookie" width="15%"/>
	<acme:list-column code="authenticated.challenge.list.label.premio-average" path="premioAverage" width="15%"/>
	<acme:list-column code="authenticated.challenge.list.label.premio-expert" path="premioExpert" width="15%"/>
</acme:list>