package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.MemberDao;

public class EditProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 변수 초기화
		req.setAttribute("isRedirect", true);
		String view = "/whistle/member/myInfo.blp";
		
		// 로그인 체크
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid==null) {
			// 로그인 안했으면 로그인 페이지로
			view = "/whistle/member/login.blp";
			return view;
		}
		
		// 로그인시 파라미터 가져오기
		int mno = Integer.parseInt(req.getParameter("mno"));
		String pw =req.getParameter("pw");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String sno = req.getParameter("ano");
		
		StringBuffer buff = new StringBuffer();
		
		if(pw != null) {
			buff.append(" , pw = '" + pw +"' ");
		}
		if(mail != null) {
			buff.append(" , mail = '" + mail +"' ");
		}
		if(tel != null) {
			buff.append(" , tel = '" + tel +"' ");
		}
		if(sno != null) {
			buff.append(" , avt = '" + sno +"' ");
		}
		
		// 첫 콤마 지워주기
		String psql = buff.toString().substring(3);
		
		// db 작업
		MemberDao mDao = new MemberDao();
		int cnt = mDao.editMyInfo(mno, psql);
		
		// 결과에 따른 처리
		if(cnt != 1) {
			// 실패시
			view = "/whistle/member/editInfo.blp";
		}
		
		return view;
		
	}
}
