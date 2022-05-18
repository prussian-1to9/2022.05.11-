package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;

public class MemberInfo implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수
		String view = "/member/memberInfo";
		
		// 파라미터 따오기
		int mno = Integer.parseInt(req.getParameter("mno"));
		
		// db 작업
		MemberDao mDao = new MemberDao();
		MemberVO mvo = mDao.getMnoInfo(mno);
		
		// 데이터 세팅
		req.setAttribute("DATA", mvo);
		
		return view;
	}

}
