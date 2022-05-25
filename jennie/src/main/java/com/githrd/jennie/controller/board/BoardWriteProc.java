package com.githrd.jennie.controller.board;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.util.*;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;
import com.oreilly.servlet.MultipartRequest;
public class BoardWriteProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값 초기화
		String view = "/whistle/board/boardList.blp";
		req.setAttribute("isRedirect", true);
		
		// 로그인 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/whistle/member/login.blp";
		}
		
	/*
		form 태그가 Stream 방식으로 전송되는 경우,
		전송되는 부가정보(파라미터, 파일)을 HttpServletRequest 객체에서 꺼내는게 아닌
		
		MultipartRequest 객체에서 꺼내 사용해야 한다.
		==> mvn repository에서 cos-05Nov2002.jar 다운, 라이브러리에 추가
		
		우리는 별도의 클래스를 작성, db 작업에 필요한 데이터를 다운받아 봅시다.
		(FileUtil.java)
	 */
		FileUtil futil = new FileUtil(req, "/resources/upload/");
		MultipartRequest multi = futil.getMulti();
	/*
		이 클래스에서는 다른 컨트롤러와 달리, Stream 방식으로 전달된 데이터를 처리한다.
		일반 컨트롤러에서 파라미터를 꺼낼 때 request 객체에서 꺼냈지만,
		
		이 클래스처럼 MultipartRequest 방식으로 전달되는 데이터는
		HttpServletRequest 객체에서 파라미터를 꺼낼 수 없다.
		(MultipartRequest 객체에서 뽑아 사용!)
	 */
		String title = multi.getParameter("title");
		String body = multi.getParameter("body");
		ArrayList<FileVO> list = futil.getList();
				
		// 데이터 일괄 세팅
		BoardVO bVO = new BoardVO();
		bVO.setId(sid);
		bVO.setTitle(title);
		bVO.setBody(body);
		bVO.setList(list);
		
		// db 일괄작업
		BoardDao bDao = new BoardDao();
		int cnt = bDao.insertBoardData(bVO);
		
		// 실패시
		if(cnt == -1 || cnt!=bVO.getList().size()) {
			view = "/whistle/board/boardWrite.blp?nowPage=" + multi.getParameter("nowPage");
		}else {// 성공시
			view = "/whistle/board/boardList.blp";
		}
		
		return view;
	}
}
