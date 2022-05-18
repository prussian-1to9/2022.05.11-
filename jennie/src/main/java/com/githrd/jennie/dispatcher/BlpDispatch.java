package com.githrd.jennie.dispatcher;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import com.githrd.jennie.controller.*;

@WebServlet("*.blp")
public class BlpDispatch extends HttpServlet {
	private HashMap<String, BlpInter> map;	// 실제 요청 내용, 실행할 객체 묵음
	
	public void init(ServletConfig config) throws ServletException {
		Properties prop = new Properties();
		FileInputStream fin = null;
		
		try {
			// properties 파일-> Stream
			String path = this.getClass().getResource("").getPath();	// 현재 실행중인경로
			path = path + "../resources/blp.properties";
			fin = new FileInputStream(path);
			
			// properties에 채워주기
			prop.load(fin);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fin.close();
			}catch(Exception e) {}
		}
		
		// 파일에 기억된 경로로 Map 완성
		map = new HashMap<String, BlpInter>();
		Enumeration en = prop.keys();	// 키값 뽑아오기(.blp)
		
		// 키값에 해당하는 클래스 객체 생성, map에 입력
		while(en.hasMoreElements()) {
			String key = (String)en.nextElement();
			
			// 클래스 경로 꺼내기
			String classpath = prop.getProperty(key);
			
			// map 이용해 객체 만들기
			try {
				Class tmp = Class.forName(classpath);
				Object o = tmp.newInstance();	// 일단 써..
				map.put(key, (BlpInter)o);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Boolean bool = false;	// forward를 많이쓰므로 기본값 false
		req.setAttribute("isRedirect", bool);
		// Redirect false 면 forward, true면 redirect, null이면 ajax로 처리
		
		// 1. 전체 요청 알아내기
		String full = req.getRequestURI();
		
		// 2. 도메인 찾아내기
		String domain = req.getContextPath();
		
		// 3. 실제 요청 알아내기
		String real = full.substring(domain.length());
		
		// 4. 원하는 컨트롤러 선택, init()에서 map에 등록된 데이터를 이용해 꺼낸다.
		BlpInter blp = map.get(real);
		resp.setCharacterEncoding("UTF-8");		// 응답문서 인코딩
		
		// 5. 실행
		String view = blp.exec(req, resp);
		bool = (Boolean)req.getAttribute("isRedirect");	// isRedirect 값 꺼내오기
		
		if(bool==null) {// 비동기통신
			PrintWriter pw = resp.getWriter();
			pw.print(view);
			
		}else if(bool) {// true : redirect
			resp.sendRedirect(view);
			
		}else {// false : forward
			String prefix = "/WEB-INF/views";
			String surrifx = ".jsp";
			
			RequestDispatcher rd = req.getRequestDispatcher(prefix + view + surrifx);
			rd.forward(req, resp);
		}	
	}
}
