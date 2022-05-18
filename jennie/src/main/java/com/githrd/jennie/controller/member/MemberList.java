package com.githrd.jennie.controller.member;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;

public class MemberList implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		String view = "/member/memberList";
		
		// db 작업
		MemberDao mDao = new MemberDao();
		ArrayList<MemberVO> list = mDao.getMemberList();
		
		// 리스트에 심기
		req.getSession().setAttribute("LIST", list);
		
		return view;
	}
}
