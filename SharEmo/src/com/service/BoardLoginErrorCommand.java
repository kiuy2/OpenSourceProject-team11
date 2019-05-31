package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.entity.User;

public class BoardLoginErrorCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
			return "emo/loginerror.jsp";
	}
}
