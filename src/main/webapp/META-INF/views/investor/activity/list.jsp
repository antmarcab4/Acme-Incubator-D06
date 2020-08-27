<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="investor.activity.list.label.title" path="title" width="20%"/>
	<acme:list-column code="investor.activity.list.label.start" path="start" width="30%"/>
	<acme:list-column code="investor.activity.list.label.end" path="end" width="30%"/>
	<acme:list-column code="investor.activity.list.label.budget" path="budget" width="20%"/>
	
</acme:list>