package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;
public class ReboardWriteProc implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		String view = "/whistle/reboard/reboardList.blp";
		req.setAttribute("isRedirect", true);
		
		// 로그인 체크
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			view = "/whistle/member/login.blp";
			return view;
		}
		
		// 파라미터 꺼내기
		String sno = req.getParameter("mno");
		String spage = req.getParameter("nowPage");
		String body = req.getParameter("body");
		String supno = req.getParameter("upno");
		
		// db 작업
		BoardVO bVO =new BoardVO();
		bVO.setMno(Integer.parseInt(sno));
		bVO.setBody(body);
		if(supno != null) {// 오류나면 안되니까
			bVO.setUpno(Integer.parseInt(supno));
		}
		
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.addReboard(bVO);
		
		// 결과에 따른 처리
		if(cnt == 0 && supno == null){// 원글 등록 실패시
			
			// forward : post 방식으로 보내기
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/reboard/reboardWrite.blp");
			req.setAttribute("NOWPAGE", spage);
			view = "/reboard/redirect";
			
		} else if(cnt == 0 && supno != null) {// 댓글 등록 실패시
			
			// forward : post 방식으로 보내기, 경로는 reboardComment
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/reboard/reboardComment.blp");
			req.setAttribute("NOWPAGE", spage);
			view = "/reboard/redirect";
			
		}else if(cnt == 1 && supno != null){// 댓글 등록 성공시
			
			// nowPage 넘겨줘야 하니까 forwarding : post 방식으로
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/reboard/reboardList.blp");
			req.setAttribute("NOWPAGE", spage);
			view = "/reboard/redirect";
		}
		
		return view;
	}
}
