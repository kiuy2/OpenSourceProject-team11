<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<! DOCTYPE html>
<html>

<head>

<link type="text/css" rel="stylesheet" href="emo/assets/css/main_style.css">
<link href='https://fonts.googleapis.com/css?family=Didact Gothic'
	rel='stylesheet'>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Dekko'
	rel='stylesheet'>

<title>SharEmo - Free Emoticon Share Website</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	    $(document).ready(function(){  
	        $(".nav-mid-item>a").hover(function() {
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

	<nav id="navbar-top">
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
	</nav>

	<section id="header">
		<div id="header-content">
			<img src="emo/images/sharEmo_logo_3.png">
			<p>Find your emoticon whatever you want!!</p>
			<form action="search.do" method="post">
				<input type="search" name="searchValue"
					placeholder="Search for emoticons e.g. happy, sad, angry...">
				<button type="submit">
					<img src="emo/images/musica-searcher.png" width="20px" height="20px">
				</button>
			</form>
		</div>
	</section>

	<nav id="navbar-mid">
		<ul>
			<li class="nav-mid-item"><a href="#">Home</a></li>
			<li class="nav-mid-item"><a href="#">Emotion</a>
				<div class="nav-mid-item-drop">
					<img src="emo/images/말꼬리.png" width="180px" height="40px">
					<ul>
						<li><a href="listPage.do">New Emoticon</a></li>
						<li><a href="listPage.do">Popular Emoticon</a></li>
						<li><a href="listPage.do">Recent Emoticon</a></li>
						<li><a href="listPage.do">Category</a></li>
					</ul>
				</div></li>
			<li class="nav-mid-item"><a href="#">Artist</a>
				<div class="nav-mid-item-drop">
					<img src="emo/images/말꼬리.png" width="180px" height="40px">
					<ul>
						<li><a href="#">New Artist</a></li>
						<li><a href="#">Popular Artist</a></li>
						<li><a href="#">Recent Emoticon</a></li>
						<li><a href="#">Most followed</a></li>
					</ul>
				</div></li>
			<li class="nav-mid-item"><a href="#">MyGallery</a>
				<div class="nav-mid-item-drop">
					<img src="emo/images/말꼬리.png" width="180px" height="40px">
					<ul>
						<li><a href="#">Liked Emoticon</a></li>
						<li><a href="#">Following Artist</a></li>
						<li><a href="#">Upload Emoticon</a></li>
						<li><a href="#">My Gallery</a></li>
					</ul>
				</div></li>
		</ul>
	</nav>

	<section class="emoticon-container">
		<div class="container-header">
			<h2>New Emoticon</h2>
			<button type="button" onclick="location.href='listPage.do'">→ Browse</button>
		</div>
		<div class="container-main">
			<c:forEach var="dto" items="${list}" begin="0" end="3">
				<div class="emoticon-package">
					<a href="retrieve.do?num=${dto.num}"> <img
						src="emo/images/thumbnail/애용!김애용!티콘_thumbnail.png">
					</a>
					<p class="title">
						Title: <a href="retrieve.do?num=${dto.num}">${dto.title}</a>
					</p>
					<p class="artist">Artist: ${dto.author}</p>
					<p class="artist">Retrieve: ${dto.readcnt}</p>
				</div>
			</c:forEach>
		</div>
	</section>

	<section class="emoticon-container">
		<div class="container-header">
			<h2>Popular Emoticon</h2>
			<button type="button">→ Browse</button>
		</div>
		<div class="container-main">
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
		</div>
	</section>

	<section class="emoticon-container">
		<div class="container-header">
			<h2>Recent Emoticon</h2>
			<button type="button">→ Browse</button>
		</div>
		<div class="container-main">
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
			<div class="emoticon-package">
				<a href=""> <img src="emo/images/thumbnail/.png">
				</a>
				<p class="title">Title: abc</p>
				<p class="artist">Artist: 1234</p>
			</div>
		</div>
	</section>

	<section id="tags-container">
		<div class="container-header">
			<h2>Styles</h2>
			<button type="button">→ Browse</button>
		</div>
		<div id="styles">
			<div class="tags">
				<ul>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
				</ul>
			</div>
			<div class="tags">
				<ul>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
				</ul>
			</div>
			<div class="tags">
				<ul>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
				</ul>
			</div>
			<div class="tags">
				<ul>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
					<li><a href="">abcd</a></li>
				</ul>
			</div>
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