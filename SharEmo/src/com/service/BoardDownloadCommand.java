package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;

public class BoardDownloadCommand implements BoardCommand {

	public static final int MAX_SIZE = 1024;

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String num = request.getParameter("num");
		String savePath = request.getRealPath("emosave/" + num);
		String files[] = new File(savePath).list();
		byte[] buf = new byte[MAX_SIZE];
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.retrieve(num);
		String title = dto.getTitle();
		String zipName = title + ".zip";

		ZipOutputStream zipout = null;
		FileInputStream fileinput = null;
		try {
			zipout = new ZipOutputStream(new FileOutputStream(savePath + "/" + zipName));

			for (String file : files) {
				fileinput = new FileInputStream(savePath + "/" + file);
				zipout.putNextEntry(new ZipEntry(file));

				int length = 0;
				while ((length = fileinput.read(buf)) > 0) {
					zipout.write(buf, 0, length);
				}
			}
		} catch (IOException e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		} finally {
			try {
				zipout.closeEntry();
				zipout.close();
				fileinput.close();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}

		File zipFile = new File(savePath, zipName);
		response.setContentType("application/zip;charset=utf-8;");
		ServletOutputStream os = null;
		FileInputStream fin = null;
		String orgfilename=zipFile.getName();
		String client = request.getHeader("User-Agent");

		try {
			if (client.indexOf("MSIE") > -1 || client.indexOf("Trident") > -1 ) {
				orgfilename= URLEncoder.encode(orgfilename, "utf-8");
			} else {
				// 한글 파일명 처리
				orgfilename = new String(orgfilename.getBytes("utf-8"), "8859_1");
				response.setHeader("Content-Type", "application/zip; charset=utf-8");
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\";");
			fin = new FileInputStream(savePath + "\\" + zipName);
			os = response.getOutputStream();

			int length = 0;
			while ((length = fin.read(buf)) > 0) {
				os.write(buf, 0, length);
			}

			if (os != null)
				os.close();
			if (fin != null)
				fin.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			zipFile.delete();
		}


		return null;
	}
}
