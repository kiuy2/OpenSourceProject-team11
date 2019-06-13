package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.entity.UserTO;

public class BoardFollowCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		UserTO user=(UserTO)session.getAttribute("user");
		
		String follow = request.getParameter("follow");
		String follower = request.getParameter("follower");
		UserDAO userdao= new UserDAO();
		int followernum=userdao.setFollow(follow,follower);

		PrintWriter out = response.getWriter();
		out.println(followernum);
		if(user!=null) {
			boolean isFollow=userdao.isFollow(follow,user.getId());
			out.println(isFollow);
		}
		out.close();
		
		return null;
	}
}
