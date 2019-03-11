<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>LOGIN FORM</title>

<script>
var loadingBar = document.getElementById("loadingBar");
var divLoadBody = document.getElementById("divLoadBody");
loadingBar.style.display = "none";
divLoadBody.style.display = "";
</script>

</head>
<body>
<!-- Loding Image -->
<div id="loadingBar" style="position:absolute; top:0; left:0; width:100%; height:100%; text-align:center; margin:0 auto; z-index:100000;">
<table width="100%" height="100%" border="0" bgcolor="#000000">
<tr><td align="center">
  <div style="text-align:center;"><img src='/resources/img/loadingbar.gif'/></div>
  <div style="margin-top:20px; color:#FFF; text-align:center; font-weight:bold;">L o a d i n g . . .</div>
</td></tr>
</table>
</div>
<!-- Loading Image -->
<div id="divLoadBody" style="display:none;">
테스트합니다
</div>
</body>
</html>