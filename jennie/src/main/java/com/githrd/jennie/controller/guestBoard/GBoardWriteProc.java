package com.githrd.jennie.controller.guestBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
public class GBoardWriteProc implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		req.setAttribute("isRedirect", true);
		String view = "/whistle/guestBoard/gBoardList.blp";
		
		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {// 로그인 안 했으면
			return "/whistle/member/login.blp";
		}
		
		// 파라미터 꺼내, db 작업
		int sno = Integer.parseInt(req.getParameter("mno"));
		String nowPage = req.getParameter("nowPage");
		String body = req.getParameter("body");
		req.setAttribute("NOWPAGE", nowPage);	// POST 방식
		
		// 글 등록 여부 검사
		GBoardDao gDao = new GBoardDao();
		int cnt = gDao.getWriterCount(sid);
		if(cnt==1) {
// GET 방식	view = view + "?nowPage=" + nowPage;
//			return view;
			
			// POST 방식
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", view);
			
			return "/guestBoard/redirect";
		}
		
		// 등록 작업
		int result = gDao.addGBoard(sid, body);
		if(result == 0) {// 실패시
// GET 방식	view = view + "?nowPage=" + nowPage;
//			return view;
			
			// POST 방식
			req.setAttribute("isRedirect", false);
			view = "/whistle/guestBoard/gBoardWrite.blp";
			req.setAttribute("VIEW", view);
			return "/guestBoard/redirect";
		}else {// 성공시
			return view;
		}
	}
}