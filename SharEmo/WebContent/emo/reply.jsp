<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>답변글 쓰기 화면</title>
  
<%
	String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="<%=ctx %>/SE2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir2", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "<%=ctx %>/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              //oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir2"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm2").submit();
      });    
});
 
</script>
</head>
<body>

  <h1>답변글 쓰기 화면</h1>
  <form id="frm2" action="reply.do" method="post">
  
	<!-- 답변글 필요 -->
	<input type="hidden" name="num" value="${retrieve.num}">
	<input type="hidden" name="repRoot" value="${replyui.repRoot}">
	<input type="hidden" name="repStep" value="${replyui.repStep}">
	<input type="hidden" name="repIndent" value="${replyui.repIndent}">
	
	원래글번호:${replyui.num}&nbsp;&nbsp;&nbsp;&nbsp;
	조회수:${replyui.readcnt}<br>
	타이틀<input type="text" name="title" required value="${replyui.title}"><br>
	작성자<input type="text" name="author" required><br>
	내용<textarea id="ir2" name="content" rows="10" cols="50">${replyui.content}</textarea><br>
	<input type="submit" id="save" value="답변달기">
  </form>
  
  <a href="list.do">목록 보기</a>

</body>
</html>