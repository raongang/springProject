<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
  </head>
  <body class="container">
  
    <div class="jumbotron">
    	<h1>Books INDEX</h1>
		<p>views/books/index.jsp</p>
    </div>

	<div class="row">
	    <c:forEach var="book" items="${bookVO}" varStatus="status">
	        <div class="col-md-4">
	            <div class="thumbnail">
	                <img src="${ book.image }" />
	                <div class="caption">
	                    <h3>${ book.title } <small>${ book.author }</small></h3>
	                    <!-- URI형태로  -->
	                    <a href="<c:url value='/bookCon/books/edit/${ book.id }' />" class="btn btn-lg btn-info">수정</a>
	                </div>
	            </div>
	        </div>
	    </c:forEach>
	</div>   
	
	<br/>
	
	<div class="row">
		<c:url value="/bookCon/books_new" var="url" />
		<a href="${url}" class="btn btn-lg btn-primary">Register</a>	    
	</div>

    <!-- 
    <a href="<c:url value="/books/new" />" class="btn btn-lg btn-primary">register</a>
     -->
         
	    
  </body>
</html>