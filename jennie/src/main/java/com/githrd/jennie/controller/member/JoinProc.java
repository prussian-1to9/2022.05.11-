package com.githrd.jennie.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;
public class JoinProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화, redirect 설정해주기
		req.setAttribute("isRedirect", true);
		String view = "/whistle/main.blp";
		
		// 세션 검사
		if(req.getSession().getAttribute("SID")!=null) {
			return view;
		}
		
		// 파라미터 받기
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String gen = req.getParameter("gen");
		int ano = Integer.parseInt(req.getParameter("ano"));
		
		// VO에 담기
		MemberVO mvo = new MemberVO();
		mvo.setAno(ano);
		mvo.setAvt(ano);
		mvo.setGen(gen);
		mvo.setId(id);
		mvo.setMail(mail);
		mvo.setName(name);
		mvo.setPw(pw);
		mvo.setTel(tel);
		
		// db 작업
		MemberDao mDao = new MemberDao();
		int cnt = mDao.addMember(mvo);
		
		if (cnt==1) {// 성공시
			// 로그인
			req.getSession().setAttribute("SID", id);
			
		}else {// 실패시 : 회원가입 화면으로 안내
			view = "/whistle/member/join.blp";
		}
		
		return view;
	}
}
