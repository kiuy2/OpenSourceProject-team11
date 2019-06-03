<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<! DOCTYPE html>
<html>

<head>

<link href='https://fonts.googleapis.com/css?family=Dekko'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Didact Gothic'
	rel='stylesheet'>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="emo/assets/css/recieve_style.css">

<title>SharEmo - Free Emoticon Share Website</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$(".nav-mid-item>a")
								.hover(
										function() {
											$(this).css("color", "grey");
											$(this).parent().find(
													".nav-mid-item-drop")
													.slideDown('normal').show();
											$(this)
													.parent()
													.find(
															".nav-mid-item-drop li")
													.hover(
															function() {
																$(this)
																		.css(
																				"background-color",
																				"rgba(255, 255, 255, 0.3)");

																$(this)
																		.mouseleave(
																				function() {
																					$(
																							this)
																							.css(
																									"background-color",
																									"");
																				});
															});

											$(this)
													.parent()
													.mouseleave(
															function() {
																$(this)
																		.find(
																				">a")
																		.css(
																				"color",
																				"white");
																$(this)
																		.find(
																				".nav-mid-item-drop")
																		.slideUp(
																				'fast');
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
				<c:choose>
					<c:when test="${user != null}">
						<li class="nav-top-item"><a href='mypage.do'>${user.id}님</a></li>
						<li class="nav-top-item"><a href='logout.do'> LogOut </a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-top-item"><a href='loginUI.do'> Login </a></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-top-item"><a href="signUpUI.do">Sign up</a></li>
			</ul>
		</div>
	</section>

	<nav id="navbar-mid">
		<form action="" method="">
			<input type="search" name="q"
				placeholder="Search for emoticons e.g. happy, sad, angry...">
			<button type="submit">
				<img src="emo/images/musica-searcher.png" width="20px" height="20px">
			</button>
		</form>
		<ul>
			<li class="nav-mid-item"><a href="#">Home</a></li>
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
		<section id="content">
			<div id="content-wrapper">
				<p>view : ${retrieve.readcnt}</p>
				<h2>${retrieve.title}</h2>
				<div id="detail-wrapper">
					<div id="detail-image">
						<img src="emo/images/emoticon_pack/도라에몽/1.png"><br />
						<button id="likes" type="button" onclick="">
							<img src="emo/images/likes_white.png">
						</button>
						<button id="follow" type="button" onclick="">
							<img src="emo/images/follow_white.png">
						</button>
					</div>
					<fieldset id="detail">
						<legend>detail</legend>
						<p>
							<b>ARTIST :</b> ${retrieve.author}
						</p>
						<p>
							<b>LIKES : </b>15
						</p>
						<p>
							<b>FOLLOW : </b>9
						</p>
						<p>
							<b>DESCRIPTION</b>
						</p>
						<textarea rows="6" cols="50">${retrieve.content}</textarea>
					</fieldset>
				</div>
				<div id="tags">
					<p>tags</p>
					<ul>
						<li><a href="#">#animation</a></li>
						<li><a href="#">#cat</a></li>
						<li><a href="#">#japan</a></li>
						<li><a href="#">#cute</a></li>
						<li><a href="#">#doraemon</a></li>
					</ul>
				</div>
				<div id="emoticon-package">
					<table>
						<tr>
							<td><img src="emo/images/emoticon_pack/도라에몽/1.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/2.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/3.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/4.png"></td>
						</tr>
						<tr>
							<td><img src="emo/images/emoticon_pack/도라에몽/5.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/6.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/7.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/8.png"></td>
						</tr>
						<tr>
							<td><img src="emo/images/emoticon_pack/도라에몽/9.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/10.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/11.png"></td>
							<td><img src="emo/images/emoticon_pack/도라에몽/12.png"></td>
						</tr>
					</table>
					<button type="button" onclick="">DOWNLOAD</button>
				</div>
			</div>
		</section>
		<div id="ad">
			<a href="https://www.idowell.co.kr/home/" target="_blank"><img
				src="emo/images/ad/winnerstel.png"></a> <a
				href="https://www.duo.co.kr/html/love_test/main.asp?u_div=agency1_DA5_2019&utm_medium=double&utm_source=kakao_banner&utm_campaign=DT_%EB%93%80%EC%98%A4pc&utm_term=%EB%A6%AC%ED%83%80%EA%B2%9F"
				target="_blank"><img src="emo/images/ad/duo.jpg"></a>
		</div>
	</section>

	<section id="footer">
		<img src="emo/images/cbnu_white.png" width="221" height="67">
		<p>2019, 오픈소스 전문 프로젝트, TEAM 11, 조 ??</p>
		<p>윤송희 ~ 전준호, 정희주, 장형규</p>
		<p>주소 : 충북 청주시 서원구 충대로 1, 충북대학교 / TEL : 043)261-2114</p>
	</section>

</body>

</html>