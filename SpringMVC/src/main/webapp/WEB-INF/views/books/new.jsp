<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Books NEW</title>
</head>

<script>
	<%--로딩바 관련 - 추후 검토 필요 
	    원래는 ajax이용해서 쓰는게 제일 좋음.
	--%>
	$(document).ready(function(){
		$('#loader').hide();
		$(".btn-primary").on("click",function(e){
			$('#loader').show();
		});
	});
</script>

<body>
	<%-- 로딩바 관련 - 추후 검토 필요, 가운데 정렬해야함. --%>
	<div id="loader" style="position: fixed; top:0; left:0; width:100%; height: 100%;">
		<img src='/resources/img/loadingbar.gif'/>
	</div>
    
    <div class="container">
        <div class="jumbotron">
            <h1>Books NEW</h1>
            <p>views/books/new.jsp</p>
        </div>
        
         <!-- <form action="<c:url value="/books" />" method="post">-->
         <%-- c:url안쓰고 
         	<form role="form" ...>
	  		- html5에서 새롭게 추가된 태그
	  	    - 컴퓨터의 리더기(시각장애인)를 이용해서 웹 페이지를 읽을때, 해당 부분이 form이라는 것을 알려준다.
         --%>
         
         <form role="form" action="/bookCon/register" method="post">
            <div class="form-group form-group-lg">
                <label class="control-label">도서 제목</label>
                <input name="title" type="text" class="form-control">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">저자</label>
                <input name="author" type="text" class="form-control">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">이미지</label>
                <input name="image" type="text" class="form-control">
            </div>
            <button type="submit" class="btn btn-lg btn-primary">전송</button>
        </form>
    </div>
</body>
</html>