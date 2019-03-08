<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Books NEW</title>

<%--로딩바 가운데정렬
   추가사항 : 로딩바 가운데 뜰때 뒷배경 흐려지면서 아무 동작 못하게 해야 한다.

<style>
div.loader img {
	position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%); 
    }
</style>
--%>

<script>
	<%--주석 -  로딩바가 가운데로 나오기는 하지만, 로딩바 뒤쪽 form양식 그대로 사용이 가능한 단점발생
	$(document).ready(function(){
		$(".loader-overlay").show();
		$(".btn-primary").on("click",function(e){
			$(".loader-overlay").hide();
		});
	});
	 --%>
	
	<%-- 로딩 gif이미지 없이 사용하는 로딩바(스핀어) jquery사용--%> 
	$(document).ready(function(){
		$.LoadingOverlay("show");
		//$.LoadingOverlay("hide");
		$(".btn-primary").on("click",function(e){
			$.LoadingOverlay("hide");
		});
	});	
	
</script>
</head>

<body>
	<%-- 주석 로딩바 관련 - 로딩바가 가운데로 나오기는 하지만, 로딩바 뒤쪽 form양식 그대로 사용이 가능한 단점발생
	<div class="loader-overlay">
		<div class="spin-loader"><img src='/resources/img/loadingbar.gif'/></div>		
	</div>
	--%>
	
	 <!-- bootstrap4의 가운데 정렬 => 이걸 이용하거나, <style>의 가운데 정렬 css를 이용해도 됨.
	<div class="container mt-5">
	  <div class="row">
	    <div class="col d-flex justify-content-center">
	      <img src="/resources/img/loadingbar.gif" alt="" class="img-fluid">
	    </div>
	  </div>
	</div>
	 -->
    
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