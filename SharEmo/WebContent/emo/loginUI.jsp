<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>로그인 페이지</title>
<script type="text/javascript">
	function login(){
		document.form.submit();
	}
	$(document).ready(function(){
	    $("form").keydown(function (key) {
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	        	login();
	        }
	    });
	});
	
</script>
<style>
th {
	text-align: center;
	font-family: Georgia, 'Times New Roman', Times, serif;
	font-size: 25px;
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
	<br />
	<br />
	<br />
	<form action="login.do" method="post" name="form">
		<table class="table table-striped table-bordered table-hover"
			style="text-align: center; height: 300px; width: 600px; margin: auto; background-color: whitesmoke">

			<tr>
				<th colspan="2" style="text-align: center; padding-top: 20px;">LOGIN</th>

			</tr>
			<tr>
				<th style="text-align: center; font-size: 22px; padding-top: 20px;">ID</th>
				<th style="text-align: center; font-size: 22px; padding-top: 20px;"><input
					type="text" name="id" /></th>
			</tr>
			<tr>
				<th style="text-align: center; font-size: 22px; padding-top: 20px;">PW</th>
				<th style="text-align: center; font-size: 22px; padding-top: 20px;"><input
					type="password" name="password" /></th>
			</tr>
			<tr>

				<th style="text-align: center; padding-top: 15px;" colspan="2">
					<input style="border: 0px; color: black" type="button"
					class="btn btn-primary" value="Login" onClick="login()" /> <input
					style="border: 0px; color: black" type="button"
					class="btn btn-secondary" value="Cancel"
					onClick="window.location='main.do'" /> <input
					style="border: 0px; color: black" type="button"
					class="btn btn-success" value="Signup"
					onClick="window.location='signUpUI.do'" />
				</th>
			</tr>
		</table>
	</form>

</body>
</html>