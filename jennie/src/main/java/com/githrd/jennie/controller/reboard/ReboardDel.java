package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
public class ReboardDel implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/redirect";
		
		// 파라미터 가져오기
		String spage = req.getParameter("nowPage");
		String sno = req.getParameter("bno");
		int rbno = Integer.parseInt(sno);
		
		// db 작업
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.delReboard(rbno);
		
		// 결과에 따른 처리
		if(cnt == 0) {// 실패
			req.setAttribute("MSG", sno + "번 글 삭제 작업 실패");
		} else {// 성공
			req.setAttribute("MSG", sno + "번 글 삭제 작업 성공");
		}
		
		// 성공시 : 데이터 넣어주기
		req.setAttribute("nowPage", spage);
		req.setAttribute("VIEW", "/whistle/reboard/reboardList.blp");
		
		return view;
	}
}
