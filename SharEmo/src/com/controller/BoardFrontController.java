package com.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BoardCommand;

//해당 어노테이션은 "~.do"로 들어오는 모든 요청을 이 서블릿에서 처리하겠다는 의미입니다. (맵핑)
@SuppressWarnings("serial")
@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {

	private Map<String, Object> commandMap = new HashMap<String, Object>(); 
	 
    public void init(ServletConfig config) throws ServletException {
 
        // Common properties 
        loadProperties("com/properties/Command");
    }
 
    // properties 연결
    private void loadProperties(String path) {
 
        ResourceBundle rbHome = ResourceBundle.getBundle(path);
 
        Enumeration<String> actionEnumHome = rbHome.getKeys();
 
        while (actionEnumHome.hasMoreElements())
 
        {
 
            String command = actionEnumHome.nextElement();
            ;
 
            String className = rbHome.getString(command);
 
            try {
 
                Class<?> commandClass = Class.forName(className);
 
                Object commandInstance = commandClass.newInstance(); 
                
                commandMap.put(command, commandInstance); 
 
            } catch (ClassNotFoundException e) {
 
                continue; // error
 
                // throw new ServletException(e);
 
            } catch (InstantiationException e) {
 
                e.printStackTrace();
 
            } catch (IllegalAccessException e) {
 
                e.printStackTrace();
 
            }
 
        }
 
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doPost를 호출함으로써 get방식으로 온 요청도 post방식과 함께 처리합니다.
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String view = null;
		BoardCommand com=null;
        try {
        	// URL에서 스키마, 서버이름, 포트번호를 제외한 나머지 주소와 파라미터를 가져옵니다.
    		// (http://localhost:8080)/Sharjsp/jsp/list.do
    		String requestURL = request.getRequestURI();

    		// URL에서 컨텍스트 경로를 가져옵니다. (/Sharemo)
            if (requestURL.indexOf(request.getContextPath()) == 0) {
        		// 36줄에서 가져온 페이지 주소에서 컨텍스트 경로마저 제외시킵니다. (/emo/list.do)
            	requestURL = requestURL.substring(request.getContextPath().length());
            }
            com = (BoardCommand) commandMap.get(requestURL);
            if (com == null) {
                System.out.println("not found : " + requestURL);
                return;
            }
            view = com.execute(request, response);
    		// 이렇게 추출한 com 변수를 각각 적합한 처리 모델에 넘겨서 일을 처리하고
    		// 그다음에 수행할 요청 혹은 띄울 페이지를 설정해줍니다.
            if (view == null) {
                return;
            }

        } catch (Throwable e) {
            throw new ServletException(e);
        }

		// 지정한 경로로 제어를 이동(리다이렉트)시키기 위한 코드입니다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
		/*// 글쓰기 폼
		if (com.equals("/writeui.do")) {
			nextPage = "emo/write.jsp";
		}*/
	}

}
