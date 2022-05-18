package com.githrd.jennie.controller.guestBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
public class GBoardWrite implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		String view = "/guestBoard/gBoardWrite";
		
		// 세션검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null){
			req.setAttribute("isRedirect", true);
			view = "/whistle/member/login.blp";
			return view;
		}
		
		// 페이지 파라미터 꺼내기
		String spage = req.getParameter("nowPage");
		
		// 작성 게시글 검사
		GBoardDao gDao = new GBoardDao();	// cnt 꺼내오는 용도
		int cnt = gDao.getWriterCount(sid);
		
		if(cnt == 1) {// 이미 글을 등록한 경우
			req.setAttribute("isRedirect", true);
			view = "/whistle/guestBoard/gBoardList.blp?nowPage=" + spage;
			return view;	// get 방식
		}
		
		return view;
	}
}
