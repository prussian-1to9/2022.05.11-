package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;

public class LoginProc implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/whistle/main.blp";
		
		// 이미 로그인 했다면
		if(req.getSession().getAttribute("SID")!=null) {
			return view;
		}
		
		// 로그인 시
		// 파라미터 받기
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		// db 작업, 결과받기
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getLogin(id, pw);
		
		// 결과에 따른 처리
		if(cnt==1) {// 성공
			// 키값 세팅
			req.getSession().setAttribute("SID", id);	
			
			// view 는 그대로 main을 향한다.
			
		}else {// 실패 : 없는 회원이거나 잘못 처리된 경우
			// 로그인 페이지로 보낸다.
			view = "/whistle/member/login.blp";
		}
		
		return view;
	}

}
