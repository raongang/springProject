<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/include/header.jsp" %>



<html>
    <body>
       <div class="container">
           <tiles:insertAttribute name="header" />
           <tiles:insertAttribute name="content" />
           <tiles:insertAttribute name="footer" />
       </div>
    </body>
</html>

