package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.EmoticonTO;
import com.entity.PageTO;

public class BoardSearchCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//String searchName=request.getParameter("searchName");
		String searchValue=request.getParameter("searchValue");
		int method = 1;
		int curPage = 1;
		if(request.getParameter("curPage") !=null) {
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		
		BoardDAO dao = new BoardDAO();
		PageTO list = dao.search("title",searchValue, curPage);
		ArrayList<EmoticonTO> ticon =dao.getEmoticon();
		//이모티콘 이미지 저장
		request.setAttribute("ticon", ticon);
		//listPage.jsp에서 목록 리스트 데이터 저장
		request.setAttribute("list", list.getBoardList());
		
		request.setAttribute("method", method);
		//page.jsp에서 페이징 처리 데이터 저장
		request.setAttribute("page", list);
		return "emo/listPage.jsp";
	}
}
