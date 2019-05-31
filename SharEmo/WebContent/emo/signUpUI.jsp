<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>회원가입</title>

<style>
th {
	text-align: center;
	font-family: Georgia, 'Times New Roman', Times, serif;
	font-size: 20px;
	margin-top: 20px;
	padding-top: 30px;
}

body {
	background-image: url('emo/images/trash/cheap_diagonal_fabric.png');
}
</style>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
	border: 3px solid skyblue;
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
</style>

<script type="text/javascript">
	// 회원가입 화면의 입력값들을 검사한다.
	function writecheck() {
		var form = document.userInfo;

		if (!form.id.value) {
			alert("아이디를 입력하세요.");
			form.id.focus();
			return false;
		}

		if (form.idcheck.value != "idCheck") {
			alert("아이디 중복체크를 해주세요.");
			form.idcheck.focus();
			return false;
		}

		if (!form.password.value) {
			alert("비밀번호를 입력하세요.");
			form.password.focus();
			return false;
		}

		// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if (form.password.value != form.passwordcheck.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}

		if (!form.name.value) {
			alert("이름을 적어주세요");
			form.name.focus();
			return false;
		}
		if (!form.nickname.value) {
			alert("닉네임을 적어주세요");
			form.nickname.focus();
			return false;
		}
		
		if (!form.email.value) {
			alert("이메일을 적어주세요");
			form.email.focus();
			return false;
		}
	}

	//아이디 중복확인
	function confirmId() {
		if (document.userInfo.id.value == "") {
			alert("ID를 입력하세요");
			return;
		}

		url = "emo/confirmId.jsp?id=" + document.userInfo.id.value;
		open(
				url,
				"confirm",
				"toolbar=no,location=no,status=no,menubar=no, scrollbars=no, resizable=no, width=300, height=200");
	}
	function inputIdChk() {
		document.userInfo.idcheck.value = "idUncheck";
		document.userInfo.confirm_id.value = "중복확인";
	}
</script>
<style>
th {
	text-align: center;
	font-family: Georgia, 'Times New Roman', Times, serif;
	font-size: 20px;
	margin-top: 20px;
	padding-top: 30px;
}

body {
	background-image: url('emo/images/trash/cheap_diagonal_fabric.png');
}
</style>

</head>
<body>
	<br />
	<div>
		<a href="main.do"> <img src="emo/images/sharEmo_logo_2.png">
		</a>
	</div>
	<br>
	<br>
	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	<form method="post" action="signUp.do" name="userInfo"
		onsubmit="return writecheck()">
		<table class="table table-striped table-bordered table-hover"
			style="text-align: center; height: 800px; width: 600px; margin: auto; background-color: whitesmoke">

			<tr>
				<th style="text-align: center; padding-top: 30px" colspan="2">
					<h2 align="center">signUp</h2>
				</th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">ID</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="text" name="id" id="userId" maxlength="50"
					onkeydown="inputIdChk()"> <input type="button" value="중복확인"
					name="confirm_id" onclick="confirmId(this.form)"> <input
					type="hidden" name="idcheck" value="idUncheck"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">PASSWORD</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="password" name="password"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">PASSWORD
					Confirm</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="password" name="passwordcheck"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">NAME</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="text" name="name"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">NICKNAME</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="text" name="nickname"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">PHONE</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="tel" name="phone"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px">EMAIL</th>
				<th style="text-align: center; padding-top: 30px"><input
					type="email" name="email"></th>
			</tr>
			<tr>
				<th style="text-align: center; padding-top: 30px;" colspan="2">
					<input
					style="background-image: url(emo/images/trash/halftone-yellow.png); border: 0px; color: black"
					type=submit class="btn btn-success" value="등록"> <input
					style="background-image: url(emo/images/trash/halftone-yellow.png); border: 0px; color: black"
					type=button class="btn btn-secondary" value="취소"
					OnClick="window.location='main.do'">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>