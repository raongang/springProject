<%@ page pageEncoding="utf-8" session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- 회원가입폼 -->
<title>SignUp FORM</title>
</head>

<div class="jumbotron"><h1>SignUP</h1></div>

<div>

	<c:url var="signUpPath" value="/signup" />

	<form:form modelAttribute="user" action="${signUpPath}" method="POST">
		<div class="form-group form-group-lg">
			<div class="form-group">
				<label>Email</label>
				<%--???? input path, errors path는 뭐지 --%>
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" element="div" cssClass="alert text-danger" />
			</div>
		</div>
		
		<div class="form-group">
            <label>Password</label>
            <form:password path="password" cssClass="form-control" placeholder="Password"/>
            <form:errors path="password" element="div" cssClass="alert text-danger"/>			
		</div>
		
		<div class="form-action">
			<input type="submit" class="btn btn-primary btn-lg" Value="Sign In">
		</div>	
	</form:form>
</div>
</html>