<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <script> 

function writeCheck()
  {
   var form = document.writeform;
 
  if( !form.id.value )
   {
    alert( "ID을 적어주세요" );
    form.id.focus();
    return;
   }
  if( !form.password.value )
  {
   alert( "비밀번호를 적어주세요" );
   form.password.focus();
   return;
  }
  if( !form.name.value )
  {
   alert( "이름을 적어주세요" );
   form.name.focus();
   return;
  }
  if( !form.email.value )
  {
   alert( "이메일을 적어주세요" );
   form.email.focus();
   return;
  }
 
  form.submit();
  }
 </script>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>회원가입</title>

<style>
    th{
        text-align: center;
        font-family: Georgia, 'Times New Roman', Times, serif;
        font-size: 20px;
        margin-top:20px;
        
        padding-top: 30px;
    }
  
    body{
        background-image: url('emo/images/trash/cheap_diagonal_fabric.png');
    }

</style>

</head>
<body>
<br />
<div>
    <img src="emo/images/sharEmo_logo_2.png">
</div>
<br />
<br />
<br />
<form action ="signUp.do" method="post" >
   
    <table class="table table-striped table-bordered table-hover" style="text-align:center; height: 800px; width:600px; margin:auto; background-color:whitesmoke ">

            <tr>
                    <th style="text-align:center;padding-top: 30px" colspan="2" >
                            <h2 align="center" > signUp </h2> 
                    </th>
                </tr>
	<tr>
		<th style="text-align:center;padding-top: 30px">ID</th>
		<th style="text-align:center;padding-top: 30px"><input type="text" name="id"></th>
	</tr>
	<tr>	
		<th style="text-align:center;padding-top: 30px">PASSWORD</th>
		<th style="text-align:center;padding-top: 30px"><input type="password" name="password"></th>
	</tr>
	<tr>	
		<th style="text-align:center;padding-top: 30px">NAME</th>
		<th style="text-align:center;padding-top: 30px"><input type="text" name="name"></th>
	</tr>
	<tr>	
		<th style="text-align:center;padding-top: 30px">NICKNAME</th>
		<th style="text-align:center;padding-top: 30px"><input type="text" name="nickname"></th>
	</tr>
	<tr>	
		<th style="text-align:center;padding-top: 30px">PHONE</th>
		<th style="text-align:center;padding-top: 30px"><input type="tel" name="phone"></th>
	</tr>
	<tr>	
		<th style="text-align:center;padding-top: 30px">EMAIL</th>
		<th style="text-align:center;padding-top: 30px"><input type="email" name="email"></th>
	</tr>	
	<tr>
		<th style="text-align:center;padding-top:30px ; " colspan="2" >
			<input  style="background-image:url(emo/images/trash/halftone-yellow.png); border: 0px; color: black " type=submit class="btn btn-success" value="등록" Onclick="javascript:writeCheck();">
			<input  style="background-image:url(emo/images/trash/halftone-yellow.png); border: 0px; color: black " type=button class="btn btn-secondary" value="취소" OnClick="window.location='loginUI.do'">
		</th>
	</tr>
    </table>


</form>
</body>
</html>