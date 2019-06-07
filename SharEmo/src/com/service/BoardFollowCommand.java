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

public class BoardFollowCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String follow = request.getParameter("follow");
		String follower = request.getParameter("follower");
		BoardDAO dao = new BoardDAO();
		int followernum=dao.setFollow(follow,follower);
		
		PrintWriter out = response.getWriter();
		out.println(followernum);
		out.close();
		
		return null;
	}
}
