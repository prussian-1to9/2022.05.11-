package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;

public class MyInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		String view = "/member/memberInfo";

		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		
		if (sid == null) {// 로그인 안되어있으면 로그인 페이지로
			req.setAttribute("isRedirect", true);
			view = "/whistle/member/login.blp";
			return view;
		}
		
		// 통과시 db 작업
		MemberDao mDao = new MemberDao();
		MemberVO mvo = mDao.getMemberInfo(sid);
		
		// 데이터 세팅
		req.setAttribute("DATA", mvo);
		
		return view;
	}

}
