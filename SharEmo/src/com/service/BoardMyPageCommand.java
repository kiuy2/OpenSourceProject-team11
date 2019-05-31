package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardMyPageCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "emo/mypage.jsp";
	}
}
