package com.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.entity.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//BoardWriteCommand는 글쓰기 화면에서 저장 버튼을 눌렀을때 실행되는 기능입니다.
public class BoardWriteCommand implements BoardCommand {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//실행시 사용자가 입력한 정보를 받아와서 DAO를 통해 DB에 새로운 row를 삽입합니다.

		HttpSession session=request.getSession();

		String path = request.getRealPath("emo");
		int max = 1024 * 1024 * 10;
		String enc = "utf-8";
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest multi;
		multi = new MultipartRequest(request, path, max, enc, dp);
		String sysName = multi.getFilesystemName("upload");
		String orgName = multi.getOriginalFileName("upload");
		String type = multi.getContentType("upload");
		type = type.split("/")[0];
		if (!type.equals("image")) {
			File f = multi.getFile("upload");
			f.delete();
			System.out.println("파일 확장자 타입이 맞지 않습니다.");
		}
		
		User user= (User) session.getAttribute("user");
		String title =multi.getParameter("title");
		String author =user.nickname;
		String content =multi.getParameter("description");
		BoardDAO dao = new BoardDAO();
		dao.write(title, author, content);
		return "listPage.do";
	}
}
