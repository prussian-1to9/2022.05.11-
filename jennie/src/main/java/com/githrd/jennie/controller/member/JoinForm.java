package com.githrd.jennie.controller.member;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;
public class JoinForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/join";
		
		// 세션 검사
		if(req.getSession().getAttribute("SID")!=null) {//로그인 상태일 시
			// redirect 세팅
			req.setAttribute("isRedirect", true);
			return "/whistle/main.blp";
		}
		
		// 로그인 한게 아니라면
		MemberDao mDao = new MemberDao();
		ArrayList<MemberVO> list = mDao.getAvtList();
		
		// 데이터 심기
		req.setAttribute("LIST", list);
		
		return view;
	}
}
