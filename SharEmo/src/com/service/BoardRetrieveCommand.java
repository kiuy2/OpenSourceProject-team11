package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.entity.BoardDTO;
import com.entity.Emoticon;
import com.entity.PageTO;
import com.entity.User;

public class BoardRetrieveCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		String num = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.retrieve(num);
		ArrayList<Emoticon> ticon =dao.getEmoticon();
		
		String follow=data.getUserid();
		int followernum=dao.getFollow(follow);
		if(user!=null) {
			boolean isFollow=dao.isFollow(follow,user.getId());
			boolean isLike=dao.isLike(num,user.getId());
			request.setAttribute("isFollow", isFollow);
			request.setAttribute("isLike", isLike);
		}
		
		//이모티콘 이미지 저장
		request.setAttribute("followernum", followernum);
		request.setAttribute("ticon", ticon);
		request.setAttribute("retrieve", data);
		return "emo/retrieve.jsp";
	}
}
