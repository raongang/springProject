<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Form</title>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Books Update</h1>
            <p>views/books/update.jsp</p>
        </div>
       <!-- <form action="<c:url value="/books" />" method="post">-->
         <%-- c:url안쓰고 --%>
         <form action="/bookCon/update" method="post">
            <div class="form-group form-group-lg">
                <label class="control-label">도서 제목</label>
                <input name="title" type="text" class="form-control" value="${vo.title }">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">저자</label>
                <input name="author" type="text" class="form-control" value="${vo.author}">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">이미지</label>
                <input name="image" type="text" class="form-control" value="${vo.image }">
            </div>
            <button type="submit" class="btn btn-lg btn-primary">전송</button>
        </form>
    </div>
</body>
</html>