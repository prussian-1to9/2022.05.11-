package com.githrd.jennie.controller;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
public interface BlpInter {
	String exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
