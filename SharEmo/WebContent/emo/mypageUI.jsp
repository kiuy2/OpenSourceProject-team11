<!DOCTYPE html>
<html>

<head>

    <link href='https://fonts.googleapis.com/css?family=Dekko' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Didact Gothic' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  <link type="text/css" rel="stylesheet"
	href="emo/assets/css/write_style.css">

    <title>SharEmo - Free Emoticon Share Website</title>

    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="emo/assets/js/dropbox2.js">
    </script>
    <script type="text/javascript" src="emo/assets/js/preview.js">
    </script>
    <script>
        $('.fun-btn').on('click', function (event) {
            $(this).toggleClass('start-fun');
            var $page = $('.page');
            $page.toggleClass('color-bg-start')
                .toggleClass('bg-animate-color');


            $(this).hasClass('start-fun') ?
                $(this).text('stop the fun') :
                $(this).text('start the fun');

        });
    </script>
    <style>
        .wrap {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        button {
            width: 140px;
            height: 45px;
            font-family: 'Roboto', sans-serif;
            font-size: 11px;
            text-transform: uppercase;
            letter-spacing: 2.5px;
            font-weight: 500;
            color: #000;
            background-color: #fff;
            border: none;
            border-radius: 45px;
            box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease 0s;
            cursor: pointer;
            outline: none;
        }

        button:hover {
            background-color: #2EE59D;
            box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
            color: #fff;
            transform: translateY(-7px);
        }
    </style>
</head>

<body>

    <section id="header">
        <div id="navbar-top">
            <a href="main.do"> <img src="emo/images/sharEmo_logo_2.png">
            </a>
            <ul id="navbar-top-right">
                <c:choose>
                    <c:when test="${user != null}">
                        <li class="nav-top-item">
                            <img src="${user.mascot}">
                            <a href='mypage.do'>${user.id}님</a>
                        </li>
                        <li class="nav-top-item"><a href='logout.do'>Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-top-item"><a href='loginUI.do'>Login</a></li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-top-item"><a href="signUpUI.do">Sign up</a></li>
            </ul>
        </div>
    </section>

    <nav id="navbar-mid">
        <form action="search.do" method="post">
            <input type="search" name="q" placeholder="Search for emoticons e.g. happy, sad, angry...">
            <button type="submit">
                <img src="emo/images/musica-searcher.png" width="20px" height="20px">
            </button>
        </form>
        <ul>
            <li class="nav-mid-item"><a href="main.do">Home</a></li>
            <li class="nav-mid-item"><a href="listPage.do?method=1">Emotion</a>
                <div class="nav-mid-item-drop">
                    <ul>
                        <a href="listPage.do?method=1">
                            <li><span>New</span></li>
                        </a>
                        <a href="listPage.do?method=2">
                            <li><span>Popular</span></li>
                        </a>
                        <a href="listPage.do?method=3">
                            <li><span>Hot</span></li>
                        </a>
                    </ul>
                </div>
            </li>
            <li class="nav-mid-item"><a href="#">Artist</a>
                <div class="nav-mid-item-drop">
                    <ul>
                        <a href="#">
                            <li><span>New</span></li>
                        </a>
                        <a href="#">
                            <li><span>Popular</span></li>
                        </a>
                        <a href="#">
                            <li><span>Most<br />followed</span></li>
                        </a>
                    </ul>
                </div>
            </li>
            <li class="nav-mid-item"><a href="mypage.do">MyGallery</a>
                <div class="nav-mid-item-drop">
                    <ul>
                        <c:if test="${user!=null}">
                            <a href="#">
                                <li><span>Like</span></li>
                            </a>
                            <a href="#">
                                <li><span>Follow</span></li>
                            </a>
                            <a href="writeui.do">
                                <li><span>Upload</span></li>
                            </a>
                            <a href="mypage.do">
                                <li><span>My Gallery</span></li>
                            </a>
                        </c:if>
                        <c:if test="${user==null}">
                            <a href="loginUI.do">
                                <li><span>Like</span></li>
                            </a>
                            <a href="loginUI.do">
                                <li><span>Follow</span></li>
                            </a>
                            <a href="loginUI.do">
                                <li><span>Upload</span></li>
                            </a>
                            <a href="loginUI.do">
                                <li><span>My Gallery</span></li>
                            </a>
                        </c:if>
                    </ul>
                </div>
            </li>
        </ul>
    </nav>
    <br />
    <br />
    <section id="container_mypage">
        <section id="content_mypage">
            <div>
                <h1 style="text-align: center">(JANGTIST)님의 MY PAGE</h1>
                <img src="에펠탑뺴꼼3.jpg" width="500" , height="500" />

                <p>Nickname</p>
                <p>Name</p>
                <p>Phone</p>
                <p>Email</p>

                <hr />
                <table>
                    <tr>
                        <td style="width: 80%">
                            <p style="text-align: left; padding-left:20px">업로드한 게시글</p>
                        </td>
                        <td>
                            <div class="wrap"><button>Browse</button></div>
                        </td>
                    </tr>
                
                </table>
                <hr />
                <table>
                        <tr>
                            <td style="width: 80%">
                                <p style="text-align: left; padding-left:20px">업로드한 게시글</p>
                            </td>
                            <td>
                                <div class="wrap"><button>Browse</button></div>
                            </td>
                        </tr>
                      
                    </table>


                <hr />
                <table>
                        <tr>
                            <td style="width: 80%">
                                <p style="text-align: left; padding-left:20px">업로드한 게시글</p>
                            </td>
                            <td>
                                <div class="wrap"><button>Browse</button></div>
                            </td>
                        </tr>
                        
                    </table>
            </div>
        </section>
    </section>
<br />
<br />
<br />
    <section id="footer">
        <img src="emo/images/cbnu_white.png" width="221" height="67">
        <p>2019, 오픈소스 전문 프로젝트, TEAM 11, 조 ??</p>
        <p>윤송희 ~ 전준호, 정희주, 장형규</p>
        <p>주소 : 충북 청주시 서원구 충대로 1, 충북대학교 / TEL : 043)261-2114</p>
    </section>

</body>

</html>