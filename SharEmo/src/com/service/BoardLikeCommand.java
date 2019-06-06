package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;
import com.entity.Emoticon;

public class BoardLikeCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String num = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		int likes=dao.setLikes(num);
		BoardDTO data = dao.retrieve(num);
		ArrayList<Emoticon> ticon =dao.getEmoticon();
		//이모티콘 이미지 저장
		request.setAttribute("ticon", ticon);
		request.setAttribute("retrieve", data);
		
		PrintWriter out = response.getWriter();
		out.println(likes);
		out.close();
		
		return null;
	}
}
