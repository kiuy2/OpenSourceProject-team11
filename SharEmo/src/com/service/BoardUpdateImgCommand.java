package com.service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmoticonDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//BoardWriteCommand는 글쓰기 화면에서 저장 버튼을 눌렀을때 실행되는 기능입니다.
public class BoardUpdateImgCommand implements BoardCommand {
	public String moveFile(String folderName, String fileName, String beforeFilePath, String afterFilePath) {
		String path = afterFilePath + "\\" + folderName;
		String filePath = path + "\\" + fileName;
		File dir = new File(path);
		if (!dir.exists()) { // 폴더 없으면 폴더 생성
			dir.mkdirs();
		}
		try {
			File file = new File(beforeFilePath + "\\" + fileName);

			if (file.renameTo(new File(filePath))) { // 파일 이동
				return filePath; // 성공시 성공 파일 경로 return
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 실행시 사용자가 입력한 정보를 받아와서 DAO를 통해 DB에 새로운 row를 삽입합니다.

		EmoticonDAO emodao = new EmoticonDAO();
		String path = request.getRealPath("emosave");
		int maxFileSize = 1024 * 1024 * 10;
		String enc = "utf-8";
		MultipartRequest multi = new MultipartRequest(request, path, maxFileSize, enc, new DefaultFileRenamePolicy());
		String num = multi.getParameter("num");
		String dirpath = path + "\\" + num;
		Enumeration files = multi.getFileNames();
		File dir = new File(dirpath);
		File[] fileList = dir.listFiles();
		boolean check = false;
		if (fileList != null)
			for (int j = 0; j < fileList.length; j++) {
				check = false;
				String emo = fileList[j].getName();
				for (int i = 0; i < fileList.length; i++) {
					emo = (String) multi.getParameter("image_" + i);
					if (emo != null && fileList[j].getName().indexOf(emo) >= 0) {
						check = true;
						break;
					}
				}
				if (!check) {
					emodao.deleteEmoticon(num, fileList[j].getName());
					fileList[j].delete();
				}
			}
		while (files.hasMoreElements()) {
			String uploadFile = (String) files.nextElement();
			String orgName = multi.getOriginalFileName(uploadFile);
			String sysName = multi.getFilesystemName(uploadFile);
			moveFile(num, sysName, path, path);
			emodao.writeEmoticon(sysName, orgName, num);
		}
		return null;
	}
}
