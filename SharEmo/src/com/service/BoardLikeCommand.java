package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.entity.BoardDTO;
import com.entity.Emoticon;
import com.entity.User;

public class BoardLikeCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		String num = request.getParameter("num");
		String userid = request.getParameter("userid");
		BoardDAO dao = new BoardDAO();
		int likes=dao.setLikes(num,userid);
		
		PrintWriter out = response.getWriter();
		out.println(likes);
		if(user!=null) {
			boolean isLike=dao.isLike(num,user.getId());
			out.println(isLike);
		}
		out.close();
		
		return null;
	}
}
