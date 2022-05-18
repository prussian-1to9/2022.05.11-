package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.*;
public class LoginForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String view = "/member/login";
		// 이미 로그인 한 경우 redirect 해줘야 하니까
		if(req.getSession().getAttribute("SID") != null) {
			req.setAttribute("isRedirect", true);
			
			// redirect의 경우 view에 적힌 경로 그대로 실행
			view = "/whistle/main.blp";
		}
		
		return view;
	}
}
