<%@ page pageEncoding="utf-8" session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">

<!--  jumbotron -->

<div class="jumbotron">
	<h1 class="display-4">Index Carts</h1>
	<p class="lead">views/books/addCarts.jsp</p>
	<hr class="my-4">
	<p>장바구니 페이지</p>
</div>

<!-- 
	badge 
	Badges are used to add additional information to any content. 
	Use the .badge class together with a contextual class (like .badge-secondary) within <span> elements to create rectangular badges.
	 Note that badges scale to match the size of the parent element (if any):
 -->

<h2>장바구니<span class="badge badge-warning">쇼핑중</span></h2>
<hr>
<table class="table">
	<thead class="thead-light">
		<tr>
			<th>#</th>
			<th>도서명</th>
			<th>가격</th>
			<th>수량</th>
			<th>합계</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="item" items="${items }" varStatus="status">
			<tr>
				<th>${status.count }</th>
				<td>${item.title }</td>
				<%--
				<td>${item.price }</td>
				 --%>
				<td>24000</td>
				<td>${item.amount }</td>
				<td>${24000*item.amount }</td>
			</tr>
		</c:forEach>
	</tbody>
	
	<tfoot>
		<tr>
			<td colspan="4"></td>
			<td>${cart.totalPrice }</td>
		</tr>
	</tfoot>
	
</table>

<c:url var="bookOrderPath" value="/orderCon/orders"/>
<form action="${bookOrderPath}" method="post">
	<input name="id" type="hidden" value="${cart.id }"  />
	<button type="submit" class="btn btn-lg btn-block btn-primary">주문하기</button>
</form>




</html>