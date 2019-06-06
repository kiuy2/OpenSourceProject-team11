package com.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.BoardDAO;
import com.entity.User;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//BoardWriteCommand는 글쓰기 화면에서 저장 버튼을 눌렀을때 실행되는 기능입니다.
public class BoardUpdateCommand implements BoardCommand {

	// Processing the file name
	public String newFileName(String oldFileName) {
		// Gets the last "." index; appear
		int index = oldFileName.lastIndexOf(".");
		// The interception of the last "." content before
		String frontStr = oldFileName.substring(0, index);
		// Gets the last "." after the content
		String behindStr = oldFileName.substring(index);
		// Reassemble the file name

		String newFileName = frontStr + System.currentTimeMillis() + "_" + behindStr;

		return newFileName;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 실행시 사용자가 입력한 정보를 받아와서 DAO를 통해 DB에 새로운 row를 삽입합니다.

		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String path = request.getRealPath("emosave");
		int maxFileSize = 1024 * 1024 * 10;
		String enc = "utf-8";
		String num="";
		String title="";
		String content=""; 
		BoardDAO dao = new BoardDAO();

		User user= (User) session.getAttribute("user");
		String author =user.nickname;
		
		// Create a disk file factory processing object
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// Create a server file upload handler object
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

		// Set the maximum file upload size
		servletFileUpload.setFileSizeMax(maxFileSize);// 10M
		
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		
		try {
			// Field and request resolution in the request request content
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);

			// Traversing the parsed list collection
			for (FileItem fileItem : fileItems) {

				if (fileItem.isFormField()) {
					if(fileItem.getFieldName().equals("num")) {
						num=fileItem.getString("UTF-8");
					}
					else if(fileItem.getFieldName().equals("title"))
						title =fileItem.getString("UTF-8");
					else if(fileItem.getFieldName().equals("description")) {
						content =fileItem.getString("UTF-8");
						dao.update(num, title, author, content);
					}
						
				} else {
					if (fileItem.getSize() <= maxFileSize) {
						// To obtain the field name
						String fileName = fileItem.getName();

						if (!fileName.equals("")) {
							// Gets the type of the upload file
							if (fileItem.getContentType().contains("image/")) {

								int index = fileName.lastIndexOf("\\");
								if (index != -1) {
									fileName = fileName.substring(index + 1);
								}

								File file = new File(path, newFileName(fileName));

								fileItem.write(file);
								dao.writeImage( file.getName(), fileName);
								
								fileItem.delete();
							} 
							else {
								dao.writeImage("default.jpg", "default.jpg");
							}
						} 
						else {
							dao.writeImage("default.jpg", "default.jpg");
						}
					} 
					else {
						dao.writeImage("default.jpg", "default.jpg");
					}
				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	

		return "listPage.do";
	}
}
