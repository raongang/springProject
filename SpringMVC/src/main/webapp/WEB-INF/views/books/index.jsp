<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!doctype html>
<html>
  <head>
  </head>
  <body class="container">
  
    <div class="jumbotron">
    	<h1>Books INDEX</h1>
		<p>views/books/index.jsp</p>
    </div>
    <!-- 
    <a href="<c:url value="/books/new" />" class="btn btn-lg btn-primary">register</a>
     -->
     <c:forEach var="vo" items="${bookVO}" varStatus="status">
     	<div class="col-md-4">
     		<a href="#" class="thubnail">
     			<img src="${vo.image}" />
     			<h3>${vo.title}</h3>
     			<h3>${vo.author}</h3>
     		</a>
     	</div>
     </c:forEach>
     
    <c:url value="/bookCon/books_new" var="url" />
	<a href="${url}" class="btn btn-lg btn-primary">Register</a>
     
  </body>
</html>