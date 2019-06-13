package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.UserDAO;
import com.entity.UserTO;

public class BoardLikeCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		UserTO user=(UserTO)session.getAttribute("user");
		
		String num = request.getParameter("num");
		String userid = request.getParameter("userid");
		UserDAO userdao= new UserDAO();
		int likes=userdao.setLikes(num,userid);
		
		PrintWriter out = response.getWriter();
		out.println(likes);
		if(user!=null) {
			boolean isLike=userdao.isLike(num,user.getId());
			out.println(isLike);
		}
		out.close();
		
		return null;
	}
}
