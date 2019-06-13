<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dao.UserDAO"%>

<%
	String id = request.getParameter("id");
	UserDAO dao = new UserDAO();
	boolean ch = dao.IdCheck(id);
	request.setAttribute("idcheck", ch);
	if (ch) {
%>
<center>
	<br />
	<br />
	<h4>이미 사용중인 ID 입니다</h4>

</center>
<%
	} else {
%>
<center>
	<br />
	<br />
	<h4>
		입력하신
		<%=id%>는 사용하실 수 있는 ID입니다
	</h4>
	<script>
		opener.document.userInfo.idcheck.value = "idCheck";
		opener.document.userInfo.confirm_id.value = "사용가능";
	</script>
</center>
<%}
	%>