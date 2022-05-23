package com.githrd.jennie.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.util.*;
public class BoardWriteProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 초기화
		String view = "/whistle/board/boardList.blp";
		req.setAttribute("isRedirect", true);
		
	/*
		form 태그가 Stream 방식으로 전송되는 경우,
		전송되는 부가정보(파라미터, 파일)을 HttpServletRequest 객체에서 꺼내는게 아닌
		
		MultipartRequest 객체에서 꺼내 사용해야 한다.
		==> mvn repository에서 cos-05Nov2002.jar 다운, 라이브러리에 추가
		
		우리는 별도의 클래스를 작성, db 작업에 필요한 데이터를 다운받아 봅시다.
		(FileUtil.java)
	 */
		FileUtil futil = new FileUtil(req, "/resources/upload/");
		
		// 실패시 forward : redirect.jsp
		
		
		return view;
	}

}
