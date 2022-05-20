package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;
public class ReboardEdit implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardEdit";
		
		// 로그인 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/whistle/member/login.blp";
		}
		
		// 통과시 : 파라미터 가져오기
		String spage = req.getParameter("nowPage");
		int rbno = Integer.parseInt(req.getParameter("bno"));
		
		// db 작업
		ReboardDao rDao = new ReboardDao();
		BoardVO bVO = rDao.setEditData(rbno, sid);
		
		// 데이터 심기
		req.setAttribute("DATA", bVO);
		
		return view;
	}
}
