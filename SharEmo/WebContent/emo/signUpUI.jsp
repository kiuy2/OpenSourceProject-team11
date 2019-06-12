<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>회원가입</title>

<style>
body {
	background-image: url('emo/images/trash/cheap_diagonal_fabric.png');
}
#header {
	padding: 10px;
}
#mainform {
	padding: 30px 200px;
}
#mainform form {
	min-width: 700px;
}
.table {
	margin: auto;
	border: 3px solid skyblue;
	background-color: whitesmoke;
}
.table-bordered>tbody>tr>th {
	width: 40%;
	text-align: center;	
	font-family: Georgia, 'Times New Roman', Times, serif;
	font-size: 20px;
	vertical-align: middle;
}
.table-bordered>tbody>tr>td {
	text-align: center;
	padding: 1.5em 4em; 
}
#mascotImg {
	width: 60px;
	height: 60px;
	margin-right: 20px;
}
#footer {
    background-color: #151113;
    padding: 2em;
    text-align: right;
    font-family: 'Noto Sans KR';
    color: white;
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
		document.userInfo.confirm_id.value = "Confirm";
	}
	
	$(document).ready(function(){
        $("#mascot").change(function() {
            var source = "emo/images/mascot/" + this.value + ".png";
        	$("#mascotImg").attr("src", source);
        });  
    });
	
</script>

</head>
<body>

	<section id="header">
		<a href="main.do"> <img src="emo/images/sharEmo_logo_2.png">
		</a>
	</section>
	
	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	<section id="mainform">
		<form method="post" action="signUp.do" name="userInfo"
			onsubmit="return writecheck()">
			<table class="table table-striped table-bordered table-hover">

				<tr>
					<th colspan="2">
						<h2>signUp</h2>
					</th>
				</tr>
				<tr>
					<th>ID</th>
					<td>
						<div class="input-group">
							<input type="text" name="id" id="userId" class="form-control"
								placeholder="Enter Id" maxlength="50" onkeydown="inputIdChk()">
							<div class="input-group-btn">
								<input class="btn btn-default" type="button" value="Confirm"
									name="confirm_id" onclick="confirmId(this.form)">
								<input type="hidden" name="idcheck" value="idUncheck">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>PASSWORD</th>
					<td><input class="form-control" type="password"
						name="password" placeholder="Enter password"></td>
				</tr>
				<tr>
					<th>PASSWORD Confirm</th>
					<td><input class="form-control" type="password"
						name="passwordcheck" placeholder="Enter password confirm"></td>
				</tr>
				<tr>
					<th>NAME</th>
					<td><input class="form-control" type="text" name="name"
						placeholder="Enter your name"></td>
				</tr>
				<tr>
					<th>NICKNAME</th>
					<td><input class="form-control" type="text" name="nickname"
						placeholder="Enter your nickname"></td>
				</tr>
				<tr>
					<th>MASCOT</th>
					<td>
						<img id="mascotImg" src="emo/images/mascot/no mascot.png">
						<select class="form-inline" id="mascot" name="mascot">
							<option>no mascot</option>
							<option>Abigail</option>
							<option>Bentley</option>
							<option>Camila</option>
							<option>Daisy</option>
							<option>Eric</option>
							<option>Eva</option>
							<option>Jayce</option>
							<option>Kate</option>
							<option>Luis</option>
							<option>Monica</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>PHONE</th>
					<td><input class="form-control" type="tel" name="phone"
						placeholder="Enter your phone number"></td>
				</tr>
				<tr>
					<th>EMAIL</th>
					<td><input class="form-control" type="email" name="email"
						placeholder="Enter your email"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type=submit class="btn btn-success" value="등록">
						<input type=button class="btn btn-secondary" value="취소"
						OnClick="window.location='main.do'">
					</td>
				</tr>
			</table>
		</form>
	</section>
	
	<section id="footer">
		<img src="emo/images/cbnu_white.png" width="221" height="67">
		<p>2019, 오픈소스 전문 프로젝트, TEAM 11, 조 ??</p>
		<p>윤송희 ~ 전준호, 정희주, 장형규</p>
		<p>주소 : 충북 청주시 서원구 충대로 1, 충북대학교 / TEL : 043)261-2114</p>
	</section>
	
</body>
</html>