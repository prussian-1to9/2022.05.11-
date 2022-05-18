package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;

public class DelInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		req.setAttribute("isRedirect", true);
		String view = "/whistle/";
		
		// 세션 체크
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid==null) {// 로그인 안한경우
			view ="/whistle/member/login.blp";
		}
		
		// 파라미터 꺼내기
		int mno = Integer.parseInt(req.getParameter("mno"));
		String id = req.getParameter("id");	// name 값으로 넘어옴.
		
		// 본인인지 체크
		if(!sid.equals(id)) {
			view = "/whistle/member/memberList.blp";
			return view;
		}
		
		// db 작업
		MemberDao mDao = new MemberDao();
		int cnt = mDao.delMember(mno);
		
		// 결과에 따른 처리
		if(cnt != 1) {// 실패시
			// 다시 정보 페이지로
			view = "/whistle/member/myInfo.blp";
			
		}else {// 성공시
			// 로그아웃 처리
			req.getSession().removeAttribute("SID");
		}
		
		return view;
	}

}
