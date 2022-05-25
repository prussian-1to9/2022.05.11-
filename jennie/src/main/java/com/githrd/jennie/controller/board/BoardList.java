package com.githrd.jennie.controller.board;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.util.*;
import com.githrd.jennie.vo.*;
public class BoardList implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 변수들 초기화
		String view = "/board/boardList";
		int nowPage = 1;
		
		// 파라미터 있을경우
		String spage = req.getParameter("nowPage");
		if (spage != null) nowPage = Integer.parseInt(spage);
		
		// db 작업
		BoardDao bDao = new BoardDao();
		int total = bDao.getTotalCount();
		
		// 페이지 처리, 리스트 설정
		PageUtil page = new PageUtil(nowPage, total);
		ArrayList<BoardVO> list = bDao.getBoardList(page);
		
		// 데이터 입력
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		
		return view;
	}
}
