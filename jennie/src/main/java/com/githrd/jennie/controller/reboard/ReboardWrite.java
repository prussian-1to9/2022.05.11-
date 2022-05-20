package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;
public class ReboardWrite implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardWrite";
		String page = req.getParameter("nowPage");
		
		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {// 로그인 안했으면
			req.setAttribute("isRedirect", true);
			view = "/whistle/member/login.blp";
			return view;
		}
		
		// 통과시 db 작업
		ReboardDao rDao = new ReboardDao();
		BoardVO bVO = rDao.getInfo(sid);
		
		// 데이터 심기
		req.setAttribute("DATA", bVO);
		
		return view;
	}
}
