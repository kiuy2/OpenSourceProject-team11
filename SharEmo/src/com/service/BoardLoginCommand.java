package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.User;

//BoardWriteCommand는 글쓰기 화면에서 저장 버튼을 눌렀을때 실행되는 기능입니다.
public class BoardLoginCommand implements BoardCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//실행시 사용자가 입력한 정보를 받아와서 DAO를 통해 DB에 새로운 row를 삽입합니다.
		String id =request.getParameter("id");
		String password =request.getParameter("password");
		HttpSession session=request.getSession();
		BoardDAO dao = new BoardDAO();
		User user=dao.login(id, password);
		if(user.islogin) {
			//user 세션 데이터 저장
			session.setAttribute("user", user);
		}
	}
}
