<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.challenge.list.label.title" path="title" width="40%"/>
	<acme:list-column code="administrator.challenge.list.label.deadline" path="deadline" width="30%"/>
	<acme:list-column code="administrator.challenge.list.label.premio-rookie" path="premioRookie" width="10%"/>
	<acme:list-column code="administrator.challenge.list.label.premio-average" path="premioAverage" width="10%"/>
	<acme:list-column code="administrator.challenge.list.label.premio-expert" path="premioExpert" width="10%"/>
</acme:list>

