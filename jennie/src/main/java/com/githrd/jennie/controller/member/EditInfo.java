package com.githrd.jennie.controller.member;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class EditInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		String view = "/member/editInfo";
		
		// 로그인 체크
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {// 로그인 안되어있으면
			req.setAttribute("isRedirect", true);
			view = "/whistle/member/login.blp";
			return view;
		}
		
		// db 작업
		MemberDao mDao = new MemberDao();
		MemberVO mvo = mDao.getMemberInfo(sid);
		ArrayList<MemberVO> list = mDao.getAvtList();
		
		// 데이터 심기
		req.setAttribute("LIST", list);
		req.setAttribute("DATA", mvo);
		
		return view;
	}

}
