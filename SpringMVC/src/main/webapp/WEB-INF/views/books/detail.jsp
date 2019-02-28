<%@ page pageEncoding="utf-8" session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">

<div class="jumbotron">
	<h1>${bookVO.title}</h1>
	<p>${bookVO.author}저</p>
</div>

<!--
    img-thumbnail : 테두리가 표시된 thumbnail
    text-center : 가운데 정렬
 -->
<div class="img-thumbnail text-center">
	<img src="${bookVO.image}">
</div>

<div class="page-header">
	<h2>Review</h2>	
</div>

<%--
	<form:form> 태그
	
	1. model.addAttribute("review",review);
	form:form 에서의 modelAttribute는 중요한 역할을 하는데 Model 오브젝트에 저장된 오브젝트를 지정한다.
	현재 model에는 위와 같이 review가 modelAttrbitue로 지정되어 있으므로 form:form 이용시
	review라는 오브젝트의 프로퍼티에 엑세스 할 수 있게 된다.
	
	2. form 태그에는 기본값으로 method="POST"가 설정되어 있음. 
	
	3. form 태그에는 action을 설정할 수가 있고, 여기서 폼 데이터를 전송할 URL을 지정할 수 있다.
	   └ 단, 이 예제는 폼 데이터를 전송할 주소가 현재 입력 화면을 표시하는 URL인 /customer/{customerId}/entry와 같으므로 생략가능.
	   
	4. action은 <c:url을 못쓰고 아래와 같이 사용한다.
		<form:form action="${pageContext.request.contextPath}/bookCon/review""> ... </form> 또는
		<c:url value="/bookCon/review" var="review" />
		<form:form action="${review}"> ... </form>
 --%>

<form:form modelAttribute="review" action="/ReviewCon/review" method="post">
	<c:forEach var="error" items="${fieldErrors }">
		<div class="alert alert-warning">
			<strong>${error.getField() }</strong>
			  :  ${error.getDefaultMessage()}
		</div>
	</c:forEach>
	
	<form:textarea path="text" cssClass="form-control" rows="5"/>
	
	<form:hidden   path="bookId" />
    <form:hidden   path="userId" /> <!-- sequence값 -->
    <button class="btn btn-block btn-primary" type="submit">Review 등록</button>	
</form:form>

<table class="table table-stripped">
	<thead>
		<tr>
			<th>User</th>
			<th>Text</th>		
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="review" items="${reviewList}">
			<tr>
				<td>익명</td>
				<td>${review.text}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<br/>

