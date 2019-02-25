<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head></head>
<body>
<h1>Exception Error Page</h1>

<dl>
	<dt>예외 메세지</dt>
	<dd>${exceptionMsg}</dd>
</dl>
</body>
</html>