package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

public class BoardSignUpCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String mascot = request.getParameter("mascot");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		mascot = "emo/images/mascot/" + mascot + ".png";
		
		BoardDAO dao = new BoardDAO();
		dao.signUp(id, password, name, nickname, mascot, phone, email);
		return "emo/join.jsp";
	}

}
