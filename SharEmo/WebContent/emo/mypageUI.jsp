<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>

<link type="text/css" rel="stylesheet" href="emo/assets/css/main_style.css" />
<link href='https://fonts.googleapis.com/css?family=Didact Gothic' rel='stylesheet' />
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet" />

<title>SharEmo - Free Emoticon Share Website</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
$(document).ready(function(){  
 

    
    $(".nav-mid-item-drop").hover(function() {
        $(this).css("color", "grey");
        $(this).parent().find(".nav-mid-item-drop").fadeIn('normal').show(); 
        $(this).parent().find(".nav-mid-item-drop li").hover(function() {
            $(this).css("background-color", "#e6e6e6");
            $(this).mouseleave(function() { 
                $(this).css("background-color", "white");
            });                    
        });

        $(this).parent().mouseleave(function() { 
            $(this).find(">a").css("color", "white");
            $(this).find(".nav-mid-item-drop").fadeOut('fast'); 
        });
        
    });  
});  
</script>
</head>


<body>

<section id="header">
		<div id="navbar-top">
			<a href="main.do"> <img src="emo/images/sharEmo_logo_2.png">
			</a>
			<ul id="navbar-top-right">
				<li class="nav-top-item">
						
							<a href='mypage.do'>${user.id}님</a></li>
				<li class="nav-top-item"><a href='logout.do'> LogOut </a>
						<a href='loginUI.do'> Login </a>
					
				<li class="nav-top-item"><a href="signUpUI.do">Sign up</a></li>
			</ul>
		</div>
	</section>

	<nav id="navbar-mid">
		<form action="" >
			<input type="search" name="q"
				placeholder="Search for emoticons e.g. happy, sad, angry...">
			<button type="submit">
				<img src="emo/images/musica-searcher.png" width="20px" height="20px">
			</button>
		</form>
		<ul>
			<li class="nav-mid-item"><a href="main.do">Home</a></li>
			<li class="nav-mid-item"><a href="#">Emotion</a>
				<div class="nav-mid-item-drop">
					<ul>
						<li><a href="#">New</a></li>
						<li><a href="#">Popular</a></li>
						<li><a href="#">Recent</a></li>
						<li><a href="#">Category</a></li>
					</ul>
				</div></li>
			<li class="nav-mid-item"><a href="#">Artist</a>
				<div class="nav-mid-item-drop">
					<ul>
						<li><a href="#">New</a></li>
						<li><a href="#">Popular</a></li>
						<li><a href="#">Recent</a></li>
						<li><a href="#">Most<br />followed
						</a></li>
					</ul>
				</div></li>
			<li class="nav-mid-item"><a href="#">MyGallery</a>
				<div class="nav-mid-item-drop">
					<ul>
						<li><a href="#">Likes</a></li>
						<li><a href="#">Following</a></li>
						<li><a href="#">Upload</a></li>
						<li><a href="#">Gallery</a></li>
					</ul>
				</div></li>
		</ul>
	</nav>

	<section id="container">
		<nav id="nav-mid-left">
			<ul>
				<li><a href="listPage.do">New Emoticon</a></li>
				<li><a href="#">Popular Emoticon</a></li>
				<li><a href="#">Recent Emoticon</a></li>
				<li><a href="#">Category</a></li>
			</ul>
		</nav>
	</section>

	<section id="footer">
		<img src="emo/images/cbnu_white.png" width="221" height="67">
		<p>2019  오픈소스 전문 프로젝트 TEAM 11</p>
		<p>윤송희  전준호  정희주  장형규</p>
		<p>주소 : 충북 청주시 서원구 충대로 1, 충북대학교 S4-1 소프트웨어학과 / TEL : 043)261-2114</p>
	</section>
</body>
</html>