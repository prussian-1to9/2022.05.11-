package com.githrd.jennie.dao;
/**
 *	방명록 관련 db 작업 전담 처리 클래스
 *	@author 최이지
 *	@since 2022/05/17
 *	@version v.1.0
 *		
 *		작업이력
 *			2022/05/17	-	클래스 제작
 *							리스트 가져오는 함수 제작
 *								담당자 : 최이지
 */
import java.util.*;
import java.sql.*;
import com.githrd.jennie.db.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.util.*;
public class GBoardDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private BlpDBCP db;
	private GBoardSQL gSQL;
	
	public GBoardDao() {
		db = new BlpDBCP();
		gSQL = new GBoardSQL();
	}
	
	// 게시글 리스트 가져오는 함수
	public ArrayList<BoardVO> getBoardList(PageUtil page){
		// 반환값 변수 초기화
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		// db 접근
		con = db.getCon();
		String sql = gSQL.getSQL(gSQL.SEL_GBRD_LIST);
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
				
				bVO.setRno(rs.getInt("rno"));
				bVO.setBno(rs.getInt("gno"));
				bVO.setId(rs.getString("id"));
				bVO.setBody(rs.getString("body").replaceAll("\r\n", "<br>"));
				bVO.setAvatar(rs.getString("savename"));
				bVO.setWdate(rs.getDate("wdate"));
				bVO.setWtime(rs.getTime("wdate"));
				bVO.setSdate();
				
				list.add(bVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}

	// 작성 여부 조회 함수
	public int getWriterCount(String id) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = gSQL.getSQL(gSQL.SEL_WRITE_CNT);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 담기
			cnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		
		return cnt;
	}

	// 총 게시글 수 조회 함수
	public int getTotal() {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		stmt = db.getStmt(con);
		
		// 명령 실행
		String sql = gSQL.getSQL(gSQL.SEL_TOTAL_CNT);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			
			// 데이터 담기
			cnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 게시글 등록 함수
	public int addGBoard(String id, String body) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = gSQL.getSQL(gSQL.INSERT_GBOARD);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, body);
			
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

	// 작성자 아바타 조회 함수
	public MemberVO getWriterInfo(String id) {
		// 반환값 변수 초기화
		MemberVO mVO = new MemberVO();
		
		// db 전달
		con = db.getCon();
		String sql = gSQL.getSQL(gSQL.SEL_WRITER_INFO);
		pstmt = db.getPstmt(con, sql);
		
		// 질의명령 완성
		try {
			pstmt.setString(1, id);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 입력
			mVO.setMno(rs.getInt("mno"));
			mVO.setSavename(rs.getString("savename"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return mVO;
	}
}