<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>CSIRO DAP Recommender Service (Evaluation)</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlAddUser" />

<nav style="background-color: #0d0c0e" class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<h3 style="color:white;">CSIRO DAP Similar Dataset Evaluation Form</h3>
		</div>
	</div>
</nav> 
