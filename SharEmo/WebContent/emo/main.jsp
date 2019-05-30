<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.PageTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<link type="text/css" rel="stylesheet" href="emo/assets/css/main_style.css">

<title>SharEmo - Free Emoticon Share Website</title>

</head>

<body>

	<nav id="navbar-top"> <a href=""> <img
		src="emo/images/sharEmo_logo_2.png">
	</a>
	<ul id="navbar-top-right">
		<li class="nav-top-item"><a href="loginUI.do">Login</a></li>
		<li class="nav-top-item"><a href="signUpUI.do">Sign up</a></li>
	</ul>
	</nav>

	<section id="header">
	<div id="header-content">
		<img src="emo/images/sharEmo_logo_3.png">
		<p>Find your emoticon whatever you want!!</p>
		<form action="search.do" method="">
			<input type="search" name="q"
				placeholder="Search for emoticons e.g. happy, sad, angry...">
			<button type="submit">
				<img src="emo/images/musica-searcher.png" width="20px" height="20px">
			</button>
		</form>
	</div>
	</section>

	<nav id="navbar-mid">
	<ul>
		<li class="nav-mid-item"><a href="">Home</a></li>
		<li class="nav-mid-item"><a href="">Emotion</a></li>
		<li class="nav-mid-item"><a href="">Artist</a></li>
		<li class="nav-mid-item"><a href="">MyGallery</a></li>
	</ul>
	</nav>

	<section class="emoticon-container">
	<div class="container-header">
		<h2>New Emoticon</h2>
		<button type="button">→ Browse</button>
	</div>
	<div class="container-main">
		<c:forEach var="dto" items="${list}">
			<div class="emoticon-package">
				<a href="retrieve.do?num=${dto.num}"> <img
					src="emo/images/thumbnail/애용!김애용!티콘_thumbnail.png">
				</a>
				<p class="title">Title: <a href="retrieve.do?num=${dto.num}">${dto.title}</a></p>
				<p class="artist">Artist: ${dto.author}</p>
				<p class="">Retrieve: ${dto.readcnt}</p>
			</div>
		</c:forEach>
		<div class="emoticon-package">
			<a href=""> <img src="emo/images/thumbnail/옴팡지게앙증해옴팡이_thumbnail.png">
			</a>
			<p class="title">Title: 옴팡지게 앙증해 옴팡이</p>
			<p class="artist">Artist: 애소</p>
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

	<section id="tags-container">
	<div class="container-header">
		<h2>Categories</h2>
		<button type="button">→ Browse</button>
	</div>
	<div id="categories">
		<div class="tags">
			<ul>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
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
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
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
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
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
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
				<li><a href="">abcd</a></li>
			</ul>
		</div>
	</div>
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

	<section id="footer"> </section>

</body>

</html>