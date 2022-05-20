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

	// 회원 번호, 사용 아바타 조회 함수
	public BoardVO getInfo(String id) {
		// 반환값 변수
		BoardVO bVO = new BoardVO();
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_WRITER_INFO);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 넣기
			bVO.setAvatar(rs.getString("savename"));
			bVO.setMno(rs.getInt("mno"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return bVO;
	}

	// 게시글, 댓글 통합 등록 함수
	public int addReboard(BoardVO bVO) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.INSERT_REBOARD);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			if(bVO.getUpno() == 0) {
				// 상위 글번호가 없으면 null 로 넣어주기
				pstmt.setNull(1,java.sql.Types.NULL);
			} else {
				pstmt.setInt(1, bVO.getUpno());
			}
			
			pstmt.setInt(2, bVO.getMno());
			pstmt.setString(3, bVO.getBody());
			
			// 명령 실행
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 댓글 등록 시 필요 정보 불러오는 함수
	public BoardVO getReboardInfo(int bno, String id) {
		// 반환값 변수 초기화
		BoardVO bVO = new BoardVO();
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_REBOARD_INFO);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			pstmt.setInt(2, bno);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 입력
			String body = rs.getString("body");
			body = (body.length() >= 10) ? (body.substring(0, 10) + "...") : body;
			bVO.setMno(rs.getInt("mno"));
			bVO.setUpno(rs.getInt("rbno"));
			bVO.setBody(body);
			bVO.setId(rs.getString("id"));
			bVO.setAvatar(rs.getString("savename"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return bVO;
	}

	// 글 삭제 (숨김) 함수
	public int delReboard(int rbno) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.DEL_REBOARD);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setInt(1, rbno);
			
			// 명령 실행
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원반환
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 글 수정 함수
	public int updateReboard(int rbno, String body) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.UPDATE_REBOARD);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, body);
			pstmt.setInt(2, rbno);
			
			// 실행
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 글 수정 시 필요 정보 불러오는 함수
	public BoardVO setEditData(int bno, String id) {
		// 반환값 변수 초기화
		BoardVO bVO = new BoardVO();
		
		// db 접근
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_REBOARD_INFO);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			pstmt.setInt(2, bno);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 입력
			String body = rs.getString("body").replaceAll("\r\n", "<br>");
			bVO.setMno(rs.getInt("mno"));
			bVO.setBno(rs.getInt("rbno"));
			bVO.setBody(body);
			bVO.setId(rs.getString("id"));
			bVO.setAvatar(rs.getString("savename"));
			bVO.setWdate(rs.getDate("wdate"));
			bVO.setWtime(rs.getTime("wdate"));
			bVO.setSdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return bVO;
	}
}