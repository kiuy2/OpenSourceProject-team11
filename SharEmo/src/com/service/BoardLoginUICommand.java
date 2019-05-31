package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardLoginUICommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "emo/loginUI.jsp";
	}
}
