package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;

public class BoardWriteUICommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "emo/write.jsp";
	}
}
