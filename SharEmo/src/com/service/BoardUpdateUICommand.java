package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;
import com.entity.EmoticonTO;

public class BoardUpdateUICommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.retrieve(num);
		
		ArrayList<EmoticonTO> ticon =dao.getEmoticon(num);
		//이모티콘 이미지 저장
		request.setAttribute("ticon", ticon);
		request.setAttribute("retrieve", data);
		return "emo/update.jsp";
	}
}
