<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />


	
<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<p>
	The goal of the study is to increase user engagement in the CSIRO Data Access Portal (DAP) by recommending similar datasets. 
	Please rate the following randomized list of similar datasets of the dataset-of-interest below. Please use the links to check the metadata of the datasets when you rate them. 
	</p>
	<p>

		<label> Dataset-of-interest : </label>&nbsp;<a
			href="https://data.csiro.au/dap/landingpage?pid=<c:out value="${dataset.fedoraId}"/>" target="_blank"><c:out
				value="${dataset.title}" /></a>. </p>

	<spring:url value="/add/${dataset.fedoraId}" var="userActionUrl" />

	<form name='evaluate' method="post" action="${userActionUrl}">
		<label class="col-sm-10 control-label">Email</label>
		<div class="col-sm-5">
			<input class="form-control" type="email" id="email" name="email"
				size="30" required="required"> 
		</div>
		
<%-- 	<c:set var="related_datasets" value=<c:out value="${related}"/> /> --%>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th align="left">#ID</th>
					<th align="left">Title</th>
					<th align="left">Very Similar</th>
					<th align="left">Similar</th>
					<th align="left">Less Similar</th>
					<th align="left">Dissimilar</th>
				</tr>
			</thead>
			
			<c:forEach var="rel" items="${related}">
				<c:set var="count" value="0" scope="page" />
				<tr>
					<!--  td>${rel[0]}</td-->
					<td>${rel[1]}</td>
					<td width="60%"><a
						target="_blank" href="https://data.csiro.au/dap/landingpage?pid=<c:out value="${rel[1]}"/>"><c:out
								value="${rel[2]}" /></a></td>
					<td align="left"><input type="radio" name="${rel[0]}" value="VS" required></td>
						<td align="left"> <input type="radio" name="${rel[0]}" value="S" required></td>
<%-- 						<td align="left"> <input type="radio" name="${rel[0]}" value="SR"
						required> </td> --%>
						<td align="left"> <input type="radio" name="${rel[0]}" value="LS"
						required> </td>
						<td align="left"> <input type="radio"
						name="${rel[0]}" value="D" required> </td>
				</tr>
			</c:forEach>
		</table>

		
			<div class="col-sm-5">
			<textarea class="form-control" type="text" id="comment" name="comment"  rows="4" cols="50" placeholder="Please add other comments, observations, suggestions related to your evaluation here (if any)"></textarea>
		</div>
		
		<div style="float: right;">
		<input type="submit" class="btn btn-primary mb1 bg-black" value="Submit" />&nbsp;&nbsp;
		<input type="reset" class="btn btn-primary mb1 bg-black" value="Clear" />

						</div>
	</form>



</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>