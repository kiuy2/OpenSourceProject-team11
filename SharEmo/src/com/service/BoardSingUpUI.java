package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//BoardWriteCommand는 글쓰기 화면에서 저장 버튼을 눌렀을때 실행되는 기능입니다.
public class BoardSingUpUI implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "emo/signUpUI.jsp";
	}
}
