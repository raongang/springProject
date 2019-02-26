<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">

<div class="jumbotron">
	<h1>${book.title }</h1>
	<p>${book.author }저</p>
</div>

<div class="thumnail">
	<img src="${book.image }">
</div>

