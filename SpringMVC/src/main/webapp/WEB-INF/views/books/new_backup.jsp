<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Books NEW</title>
<script>
	<%-- 로딩 gif이미지 없이 사용하는 로딩바(스핀어) jquery Plugin인 introLoader 사용 예--%> 
	$(document).ready(function(){
		//$(".btn-primary").on("click",function(e){
		    $("#element").introLoader({
		        animation: {
		            name: 'simpleLoader',
		            options: {
		                exitFx:'slideUp',
		                ease: "easeInOutCirc",
		                style: 'dark',
		            }
		        },    
		        spinJs: {
		            lines: 13, // The number of lines to draw
		            length: 13, // The length of each line
		            width: 10, // The line thickness
		            radius: 20, // The radius of the inner circle
		            corners: 1, // Corner roundness (0..1)
		        }
			});
		//});
	});
</script>
</head>

<body>
	<div id="element" class="introLoading"></div>
    
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