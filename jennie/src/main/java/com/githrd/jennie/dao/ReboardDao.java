package com.githrd.jennie.dao;

import java.sql.*;
import java.util.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.db.*;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.util.PageUtil;
public class ReboardDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private BlpDBCP db;
	private ReboardSQL rSQL;
	
	public ReboardDao() {
		rSQL = new ReboardSQL();
		db = new BlpDBCP();
	}

	// 게시글 리스트 조회 함수
	public ArrayList<BoardVO> getList(PageUtil page){
		// 반환값 변수 초기화
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_ALL_LIST);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			
			// 명령 실행
			rs = pstmt.executeQuery();
			
			// 데이터 넣기
			while(rs.next()) {
				BoardVO bVO = new BoardVO();
				
				bVO.setStep(rs.getInt("step"));
				bVO.setWdate(rs.getDate("wdate"));
				bVO.setWtime(rs.getTime("wdate"));
				bVO.setSdate();
				bVO.setAvatar(rs.getString("savename"));
				bVO.setBody(rs.getString("body"));
				bVO.setId(rs.getString("id"));
				bVO.setMno(rs.getInt("mno"));
				bVO.setUpno(rs.getInt("upno"));
				bVO.setBno(rs.getInt("rbno"));
				bVO.setRno(rs.getInt("rno"));
				
				list.add(bVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}

	// 총 게시글 수 조회 함수
	public int getTotalCount() {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_TOTAL_CNT);
		stmt = db.getStmt(con);
		
		// 명령 실행
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			
			// 데이터 채우기
			cnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원반환
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return cnt;
	}
}