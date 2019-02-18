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
    <c:url value="/bookCon/books_new" var="url" />
	<a href="${url}" class="btn btn-lg btn-primary">register</a>
     
  </body>
</html>