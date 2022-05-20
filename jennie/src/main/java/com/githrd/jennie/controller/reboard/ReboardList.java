package com.githrd.jennie.controller.reboard;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.util.*;
public class ReboardList implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardList";
		int nowPage = 1;
		
		// 세션검사 생략
		
		// 파라미터 꺼내오기
		if(req.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
		}
		String msg = req.getParameter("msg");
		
		// 페이지 객체 만들기
		ReboardDao rDao = new ReboardDao();
		int total = rDao.getTotalCount();
		PageUtil page = new PageUtil(nowPage, total);
		
		// db 작업, 데이터 넣어주기
		ArrayList<BoardVO> list = rDao.getList(page);
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		if(msg != null) {
			req.setAttribute("MSG", msg);
		}
		
		return view;
	}
}