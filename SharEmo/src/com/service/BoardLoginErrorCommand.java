package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardLoginErrorCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
			return "emo/loginerror.jsp";
	}
}
