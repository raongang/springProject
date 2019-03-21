<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>LOGIN FORM</title>
</head>

<div class="jumbotron"><h1>Login</h1></div>

<div>

	<c:url var="loginPath" value="/user/login" />
	<c:url var="signUpPath" value="/signup" />

	<form id="loginForm" action="${loginPath}" method="POST">
		<div class="form-group form-group-lg">
			<div class="form-group">
				<label>Email</label>
				<input type="text" name="email" class="form-control" placeholder="Email.." />
			</div>
			
			<div class="form-group">
				<label>Password</label>
				<input type="password" name="password" class="form-control" placeholder="Password.." />
			</div>
			
			<div class="form-group">
			</div>

			<!-- ID/PW 저장여부체크박스도 사용 -->
			<div class="row">
				<div class="col-xs-8">
					<div class="checkbox icheck">
						<label><input type="checkbox" name="useCookie"> Remember Me</label></div>
				</div>
			
				<div class="form-action">
					<input type="submit" class="btn btn-primary btn-lg" Value="Sign In">
					<!-- 
					<button type="submit" class="btn btn-primary btn-block btn-flat btn-login">Sign In</button>
					 -->					
					<a href="${signUpPath}" class="btn btn-info btn-lg">Register</a>

				</div>			
			</div>			

		</div>
	</form>
	
	<a href="#">I forgot my password</a><br>
	<a href="register.html" class="text-center">Register a new User</a>
</div>
</html>