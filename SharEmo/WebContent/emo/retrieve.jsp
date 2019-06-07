<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.dao.BoardDAO, com.entity.BoardDTO"%>
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
<script type="text/javascript" src="emo/assets/js/dropbox.js"></script>

<script> 
function img_resize() { 
	var maxsize = 200; 
	var content = document.getElementById("emoticon-package"); 
	var img = content.getElementsByTagName("img"); 
	for(i=0; i<img.length; i++) { 
	if ( eval('img[' + i + '].width > maxsize') ) { 
		eval('img[' + i + '].width = maxsize');
 			}
 		} 
	} 
	window.onload = img_resize; 
</script>
<script>

$(document).ready(function changeColor(){  
	if(${isFollow}){
		$("#follow").css("background-color", "rgb(255, 81, 106)");
	}
	else{
		$("#follow").css("background-color", "gray")
	}
	
	if(${isLike}){
		$("#likes").css("background-color", "rgb(0, 131, 255)");
	}
	else{
		$("#likes").css("background-color", "gray");
	}
}); 

	function like() {
		if( ${ user != null }){
			$.ajax({
				url : "like.do",
				type : "POST",
				data : {
					num : "${retrieve.num }",
					userid : "${user.id}"
				},
				success : function(data) {
					//alert("'좋아요'가 반영되었습니다!");
					// data중 put한 것의 이름 like 
					var likes=data.split('\n');
					
					if(likes[1].indexOf("true") > -1 ){
						$("#likes").css("background-color", "rgb(0, 131, 255)");
					}
					else{
						$("#likes").css("background-color", "gray");
					}
					
					$("#likes_num").html(likes[0]);
					//id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다. 
				},
				error : function(request, status, error) {
					alert("오류");
				}
			});
		}
		else{
			alert("로그인이 필요합니다.");
		}
	}
	function follow() {
		if( ${user != null } ){
			$.ajax({
				url : "follow.do",
				type : "POST",
				data : {
					follow : "${retrieve.userid }",
					follower : "${user.id}"
				},
				success : function(data) {
					//alert("'좋아요'가 반영되었습니다!");
					// data중 put한 것의 이름 like 
					var follow=data.split('\n');
					if(follow[1].indexOf("true") > -1){
						$("#follow").css("background-color", "rgb(255, 81, 106)");
					}
					else{
						$("#follow").css("background-color", "gray")
					}
					$("#follower_num").html(follow[0]);
					//id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다. 
				},
				error : function(request, status, error) {
					alert("오류");
				}
			});
		}
		else{
			alert("로그인이 필요합니다.");
		}
	}

	
	
	
	function download() {

		url = "emo/filedown.jsp?num=" + ${retrieve.num};
		open(
				url,
				"confirm",
				"toolbar=no,location=no,status=no,menubar=no, scrollbars=no, resizable=no, width=300, height=200");
	}
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
						<c:set var="loop_flag" value="false" />
						<c:forEach var="emo" items="${ticon}">
							<c:if test="${not loop_flag }">
								<c:if test="${retrieve.num eq emo.boardnum}">
									<img class="emoticon-Thumbnail" src="emosave/${emo.boardnum}/${emo.src}"><br/>
									<c:set var="loop_flag" value="true" />
								</c:if>
							</c:if>
						</c:forEach>
						<button id="likes" type="button" onclick="return like();">
							<img src="emo/images/likes_white.png">
						</button>
						<button id="follow" type="button" onclick="return follow();">
							<img src="emo/images/follow_white.png">
						</button>
					</div>
					<fieldset id="detail">
						<legend>detail</legend>
						<p>
							<b>ARTIST :</b> ${retrieve.author}
						</p>
						<p>
							<b>LIKES : </b> <span id="likes_num">${retrieve.likes}</span>
						</p>
						<p>
							<b>FOLLOW : </b><span id="follower_num">${followernum}</span>
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
						<c:forEach var="emo" items="${ticon}" varStatus="status">
							<c:if test="${status.index % 4 eq 0}">
								<tr>
							</c:if>
							<c:if test="${retrieve.num eq emo.boardnum}">
								<td><img class="emoticon-Thumbnail"
									src="emosave/${emo.boardnum}/${emo.src}"></td>
							</c:if>
							</td>
							<c:if test="${status.count % 4 eq 0}">
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<script>imgSize("img");</script>
					
					<button type="button" onclick="download();">DOWNLOAD</button>

					<c:if test="${user.id==retrieve.userid }">
					<button type="button" onclick="location.href='updateUI.do?num=${retrieve.num}'">수정</button>
					<button type="button" onclick="location.href='delete.do?num=${retrieve.num}'">삭제</button>
					</c:if>
					<c:if test="${user.id==retrieve.userid }">
						<button type="button"
							onclick="location.href='updateUI.do?num=${retrieve.num}'">수정</button>
						<button type="button"
							onclick="location.href='delete.do?num=${retrieve.num}'">삭제</button>
					</c:if>
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

