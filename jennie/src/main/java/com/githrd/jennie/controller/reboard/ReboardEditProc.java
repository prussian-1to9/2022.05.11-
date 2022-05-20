package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
public class ReboardEditProc implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		String view = "/reboard/redirect";
		
		// 로그인 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/whistle/member/login.blp";
		}
		
		// 파라미터 가져오기
		String body = req.getParameter("body");
		String spage = req.getParameter("nowPage");
		int rbno = Integer.parseInt(req.getParameter("bno"));
		
		// db 작업
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.updateReboard(rbno, body);
		
		// 결과에 따른 처리
		if(cnt == 0) {// 실패시
			req.setAttribute("VIEW", "/whistle/reboard/reboardEdit.blp");
			
		}else {// 성공시
			req.setAttribute("VIEW", "/whistle/reboard/reboardList.blp");
			req.setAttribute("MSG", rbno + "번 글 수정 성공");
		}
		req.setAttribute("NOWPAGE", spage);
		
		return view;
	}

}
