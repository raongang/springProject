<%@ page pageEncoding="utf-8" session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">


<!--
<div class="jumbotron">
	<h1>${bookVO.title}</h1>
	<p>${bookVO.author}저</p>
</div>
-->

<!-- 
    img-thumbnail : 테두리가 표시된 thumbnail
    text-center : 가운데 정렬

<div class="img-thumbnail text-center">
	<img src="${bookVO.image}">
</div>
 -->
 
 <div class="jumbotron">
 	<h1 class="display-4">Show Book</h1>
 	<p class="lead">views/books/detailReview.jsp</p>
 	<hr>
 	<p>책 상세 페이지</p>
 </div>
 
 <!--  div class row is bootstrap gird system 
 	      한 행을 12개의 컬럼 기준으로 크기를 지정한다.
 -->
<div class="row">
	<div class="col-md-4">
		<img class="card-img-top" src="${bookVO.image }" alt="Card image cap">
	</div>
	
	<div class="col-md-8">
		<h3>${bookVO.title}</h3>
		<p>저자 :  ${bookVO.author}</p>
		<%--
		<p>가격 : ${bookVO.price }원</p>
		 --%>
		 <%--여기서는 테스트로  등록하고 보는 거기때문에 하드코딩으로 가격을 정한다.
		          아니면 등록시 가격도 작성할 수 있게 해주면 된다.--%>
		 <p>가격 : 24000원</p>
		<h4 class="my-4"></h4>
		<c:url var="bookAddCartPath" value="/orderCon/cartAdd"/>
        <form action="${bookAddCartPath }" method="post">
            <div class="form-group">
                <label>수량</label>
                <input name="amount" class="form-control" type="number" value="1" />
            </div>
            <input name="book_id" type="hidden" value="${ bookVO.id }">
            <button type="submit" class="btn btn-primary">장바구니에 담기</button>
        </form>
	</div>
</div> 

<!-- 리뷰 작성 -->
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
			<strong>${error.getField() }</strong> :  ${error.getDefaultMessage()}
		</div>
	</c:forEach>
	
	<form:textarea path="text" cssClass="form-control" rows="5"/>
	
	<!-- 리뷰 평점 선택창  -->
	<form:label path="rating">리뷰 평점:</form:label>
	<form:select path="rating">
		<form:options items="${ratingOptions }" />
	</form:select>
	
	<form:hidden  path="bookId" />
    <form:hidden   path="userId" /> <!-- sequence값 -->
    <button class="btn btn-block btn-primary" type="submit">Review 등록</button>	
</form:form>

<!-- 리뷰 테이블 -->
<table class="table table-stripped">
	<thead>
		<tr>
			<th>Rating</th>
			<th>User</th>
			<th>Text</th>		
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="review" items="${reviewList}">
			<tr>
				<!-- for문으로 rating된 횟수만큼 돌면서 별표를 표시 -->
				<td><c:forEach var="rating" items="${ ratingOptions }" varStatus="status" begin="1" end="${ review.rating }">★</c:forEach></td>
				<td>익명</td>
				<td>${review.text}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<br/>

