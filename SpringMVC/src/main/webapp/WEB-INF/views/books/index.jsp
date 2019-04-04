<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
</head>
  <script>  	
	$(document).ready(function(){
		<%-- ajax 방식
		$(".btn_delete").on("click",function(e){
			$("#confirmModal").modal('show');//modal confirm 실행.
			//id값 가져오기
			var id = $("#book_id").val();
			console.log("bookid >> "+id);
			
			$(".modal_delete").on("click",function(){
				$.ajax({
					type : "post",
					url : '/bookCon/books/delete/'+id,
					success:function(result){
						$("#getBody").html("delete success!");
						$("#resultModal").modal('show');
						
						self.location="/bookCon/books/"; //list로 페이지전환
					},
					error:function(result){
						$("#getBody").html("delete error!");
						$("#resultModal").modal('show');
					}	
				});//end ajax
			});//end modal_delete
			$("#confirmModal").modal("hide");
		});
		--%>
		
		//단순 modal 팝업창만 보여주고 get으로 통신.
		$(".btn_delete").on("click",function(e){
			//foreach에서 삭제를 클릭한 id를 가져와야한다. 
			var id = $(this).data("book_id");
			console.log("===============================");
			console.log(id);
			$("#confirmModal").modal('show');//modal confirm 실행.
			$(".modal_delete").on("click",function(e){ //modald 클릭시 삭제
				self.location="/bookCon/books/delete/"+id;
			}); //end .modal_delete
		}); //end .btn_delete

		//상세페이지
		$(".image_title").on("click",function(e){
			var id= $(this).data("book_id");
			self.location="/bookCon/books/detailReview/"+id;
		});
		
	}); //end document
  </script>
  
  <body class="container">
  
  	<!-- MAIN FORM -->
    <div class="jumbotron">
    	<h1>Books LIST</h1>
    	<%--
		<p>views/books/index.jsp</p>
		 --%>
    </div>
    
    <!-- Searching FORM -->
    <div class="search">
    	<form role="form" action="/bookCon/search" method="post">
    		<div class="row">
    			<div class="col-md-10">
    				<input name="title" type="text" class="form-control input-lg" placeholder="Search For Book Name..">
    			</div>
    			<div class="col-md-2">
    				<input type="submit" class="btn btn-primary btn-sg" Value="검색">
				</div>
    		</div>
    	</form>
    </div>

    
	<div class="row">
	    <c:forEach var="book" items="${bookVO}" varStatus="status">
	        <div class="col-md-4">
	            <div class="img-thumbnail">
	                <!-- img 손 모양 -->
	                <img src="${ book.image }" alt="bookImage" style="cursor:pointer;width:100%;" class="image_title" data-book_id="${book.id}"/>
	                <div class="caption">
	                    <h3>${ book.title } <small>${ book.author }</small></h3>
	                    <input name="book_id" type="hidden" id="book_id" value="${book.id}"><!-- ajax에서쓰기위해 -->
	                    <!--<a href="<c:url value='/bookCon/books/delete/${book.id}' />" class="">삭제
	                    </a>  modal confirm ajax로 변경 -->
	                    
	                    <!-- rest 이용하듯이 URI형태로 전달 -->
	                    <a href="<c:url value='/bookCon/books/update/${book.id}'/>" class="btn btn-lg btn-info btn_update">수정</a>
	                    <button type="submit" class="btn btn-lg btn-danger btn_delete" data-book_id="${book.id}">삭제</button> 
	                </div>
	            </div>
	        </div>
	    </c:forEach>
	</div>   
	<br/>
	
<%@ include file="/WEB-INF/include/modal.jsp" %>
	<div class="row">
		<c:url value="/bookCon/books_new" var="url" />
		<a href="${url}" class="btn btn-lg btn-primary btn_register">Register</a>
		&nbsp;&nbsp;&nbsp;
		<a href="/user/logout" class="btn btn-lg btn-info ">logout</a>	    
	</div>
		
  </body>
</html>


