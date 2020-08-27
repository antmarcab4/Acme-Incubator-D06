<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.overture.list.label.title" path="title" width="30%"/>
	<acme:list-column code="administrator.overture.list.label.deadline" path="deadline" width="30%"/>
	<acme:list-column code="administrator.overture.list.label.minMoney" path="minMoney" width="30%"/>
	<acme:list-column code="administrator.overture.list.label.maxMoney" path="maxMoney" width="10%"/>
</acme:list>
