package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;
public class ReboardComment implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardComment";
		
		// 로그인 세션 체크
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/whistle/member/login.blp";
		}
		
		// 파라미터 받기
		String sno = req.getParameter("bno");
		int bno = Integer.parseInt(sno);
		String spage = req.getParameter("nowPage");
		
		// db 작업
		ReboardDao rDao = new ReboardDao();
		BoardVO bVO = rDao.getReboardInfo(bno, sid);
		
		// 데이터 채우기
		req.setAttribute("DATA", bVO);
		
		return view;
	}
}