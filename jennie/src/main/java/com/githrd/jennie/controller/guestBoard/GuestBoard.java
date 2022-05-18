package com.githrd.jennie.controller.guestBoard;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.util.*;

public class GuestBoard implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 변수들 초기화
		String view = "/guestBoard/gBoardList";
		int cnt = 0;
		GBoardDao gDao = new GBoardDao();

		// 현재 페이지 체크
		String spage =req.getParameter("nowPage");
		int nowPage = 1;
		
		if(spage != null) {// 값이 있으면 넣어주기
			nowPage = Integer.parseInt(spage);
		}
		
		// 총 게시글 수 조회
		int total = gDao.getTotal();
		
		// 페이지 객체 만들기
		PageUtil page = new PageUtil(nowPage, total);
		
		// db 작업, 보여줄 게시글 수 꺼내주기
		ArrayList<BoardVO> list = gDao.getBoardList(page);

		// 로그인 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid != null) {
			cnt = gDao.getWriterCount(sid);
		}
		
		// 뷰에 데이터 심기
		req.setAttribute("LIST", list);
		req.setAttribute("CNT", cnt);
		req.setAttribute("PAGE", page);
		
		return view;
	}
}
