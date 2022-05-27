package com.githrd.jennie.dao;

import java.sql.*;
import java.util.*;

import com.githrd.jennie.db.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.util.*;
public class BoardDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BlpDBCP db;
	private BoardSQL bSQL;
	
	public BoardDao() {
		db = new BlpDBCP();
		bSQL = new BoardSQL();
	}
	
	// 단일파일 등록 함수
	public int addFileInfo(FileVO fVO) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.INSERT_FILEINFO);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, fVO.getId());
			pstmt.setString(2, fVO.getOriname());
			pstmt.setString(3, fVO.getSavename());
			pstmt.setString(4, fVO.getDir());
			pstmt.setLong(5, fVO.getLen());
			
			// 명령 실행
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 단일 게시글 등록 함수
	public int addBoard(BoardVO bVO) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.INSERT_BOARD);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, bVO.getId());
			pstmt.setString(2, bVO.getTitle());
			pstmt.setString(3, bVO.getBody());
			
			// 명령 실행
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 파일 일괄 등록
	public int addFileList(ArrayList<FileVO> list) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		for(FileVO f : list) {
			cnt += addFileInfo(f);
		}
		
		return cnt;
	}
	// 일괄 등록
	public int insertBoardData(BoardVO bVO) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// 게시글 등록
		cnt = addBoard(bVO);
		if(cnt != 1) {
			return cnt;
		}
		
		// 파일 일괄 등록
		cnt = addFileList(bVO.getList());
		if(cnt != bVO.getList().size()) {
			return -1;
		}
		return cnt;
	}

	// 게시글 리스트 불러오기
	public ArrayList<BoardVO> getBoardList(PageUtil page) {
		// 반환값 변수 초기화
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		// db 접근
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_BOARD_LIST);
		pstmt = db.getPstmt(con, sql);

		// 명령 완성
		try {
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			
			// 명령 실행
			rs = pstmt.executeQuery();
			
			// 데이터 입력
			while(rs.next()) {
				BoardVO bVO = new BoardVO();
				bVO.setBno(rs.getInt("bno"));
				bVO.setId(rs.getString("id"));
				bVO.setTitle(rs.getString("title"));
				bVO.setWdate(rs.getDate("wdate"));
				bVO.setClick(rs.getInt("click"));
				bVO.setCnt(rs.getInt("cnt"));
				
				list.add(bVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}

	// 게시글 수 불러오기
	public int getTotalCount() {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		stmt = db.getStmt(con);
		
		// 명령 입력, 실행
		String sql = bSQL.getSQL(bSQL.SEL_TOTAL_COUNT);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			
			// 데이터 담기
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	
	// 게시글 상세보기 함수
	public BoardVO getBoardDetail(int bno) {
		// 반환값 변수 초기화
		BoardVO bVO = new BoardVO();
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		
		bVO.setList(list);
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_BOARD_DETAIL);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.isFirst()) {
					bVO.setBno(rs.getInt("bno"));
					bVO.setId(rs.getString("id"));
					bVO.setTitle(rs.getString("title"));
					bVO.setBody(rs.getString("body").replaceAll("\r\n", "<br>"));
					bVO.setWdate(rs.getDate("wdate"));
					bVO.setWtime(rs.getTime("wdate"));
					bVO.setClick(rs.getInt("click"));
				}
				
				FileVO fVO = new FileVO();
				fVO.setFno(rs.getInt("fno"));
				fVO.setOriname(rs.getString("oriname"));
				fVO.setSavename(rs.getString("savename"));
				fVO.setDir(rs.getString("dir"));
				list.add(fVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return bVO;
	}
}