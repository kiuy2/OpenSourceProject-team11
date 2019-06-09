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

public class BoardFollowCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		String follow = request.getParameter("follow");
		String follower = request.getParameter("follower");
		BoardDAO dao = new BoardDAO();
		int followernum=dao.setFollow(follow,follower);

		PrintWriter out = response.getWriter();
		out.println(followernum);
		if(user!=null) {
			boolean isFollow=dao.isFollow(follow,user.getId());
			out.println(isFollow);
		}
		out.close();
		
		return null;
	}
}
