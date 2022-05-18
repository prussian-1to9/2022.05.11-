package com.githrd.jennie.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;

public class IdCheck implements BlpInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*		비동기 통신의 경우 pw.print로 직접 문서를 작성하기 때문에
		Stringbuffer로 json 문서를 구성하는 문자열을 짜주도록 한다.	*/
		// 초기화
		StringBuffer buff = new StringBuffer();
		
		// 비동기통신 처리
		req.setAttribute("isRedirect", null);
		
		// 파라미터 꺼내기
		String id = req.getParameter("id");
		
		// db 작업
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getIdCount(id);
		
		// 결과에 따른 처리
		buff.append("{");
		buff.append("\"result\": \"");
		if(cnt==0) {// 사용 가능
			buff.append("OK");
		}else {// 사용 불가
			buff.append("NO");
		}
		buff.append("\"");
		buff.append("}");
		
		return buff.toString();
	}
}
