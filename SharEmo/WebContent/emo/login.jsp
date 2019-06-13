<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.entity.UserTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<script>
<%UserTO user=(UserTO)session.getAttribute("user");
	if(user!=null){
		out.println("self.window.alert('"+user.getId()+"님 환영합니다');");
		out.println("location.href='main.do';"); 
	}
	else{
		out.println("self.window.alert('로그인 실패');");
		out.println("location.href='loginUI.do';"); 
	}%>
	

</script>
</body>
</html>