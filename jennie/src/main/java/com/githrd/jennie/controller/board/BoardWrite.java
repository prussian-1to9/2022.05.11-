package com.githrd.jennie.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.jennie.controller.BlpInter;

public class BoardWrite implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/board/boardWrite";
		return view;
	}
}
