package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.EmoticonDAO;
import com.entity.EmoticonTO;
import com.entity.PageTO;

public class BoardMainCommand implements BoardCommand{
	static boolean start=false;
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int curPage =1;
		if(request.getParameter("curPage") !=null) {
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		
		BoardDAO dao = new BoardDAO();
		PageTO listNew = dao.page(curPage, null, true);
		PageTO listPop = dao.page(curPage, "likes", false);
		PageTO listHot = dao.page(curPage, "readCnt", false);

		EmoticonDAO emodao = new EmoticonDAO();
		ArrayList<EmoticonTO> ticon =emodao.getEmoticon();
		//listPage.jsp에서 목록 리스트 데이터 저장
		request.setAttribute("listNew", listNew.getBoardList());
		request.setAttribute("listPop", listPop.getBoardList());
		request.setAttribute("listHot", listHot.getBoardList());
		//이모티콘 이미지 저장
		request.setAttribute("ticon", ticon);
		return "emo/main.jsp";
	}
}
