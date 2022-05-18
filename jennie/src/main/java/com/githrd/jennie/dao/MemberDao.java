package com.githrd.jennie.dao;

import java.sql.*;
import java.util.*;
import com.githrd.jennie.db.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.vo.*;
/**
 * 	회원 관련 db 작업을 전담 처리하는 Class
 *	@author 최이지
 *	@since	2022.05.12
 *	@version v.1.0
 *		
 *		작업이력 :
 *			2022.05.12	-	Class 제작
 *							담당자 : 최이지
 */
public class MemberDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private BlpDBCP db;
	private MemberSQL mSQL;
	
	public MemberDao() {
		db = new BlpDBCP();
		mSQL = new MemberSQL();
	}
	
	// 로그인 유무 처리 함수
	public int getLogin(String id, String pw) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN_CNT);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			// 명령 실행, 데이터 채우기
			rs = pstmt.executeQuery();
			rs.next();
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

	// 회원가입 함수
	public int addMember(MemberVO mVO) {
		// 반환값 변수
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.ADD_MEMBER);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성 (순서 : name, id, pw, mail, tel, avt, gen)
		try {
			pstmt.setString(1, mVO.getName());
			pstmt.setString(2, mVO.getId());
			pstmt.setString(3, mVO.getPw());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getTel());
			pstmt.setInt(6, mVO.getAvt());
			pstmt.setString(7, mVO.getGen());
			
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
	
	// 아바타 리스트 보여주는 함수
	public ArrayList<MemberVO> getAvtList(){
		// 반환값 변수
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		// db 접근
		con = db.getCon();
		stmt = db.getStmt(con);
		
		// 명령 완성
		String sql = mSQL.getSQL(mSQL.SEL_ALL_AVT);
		
		// 명령 실행
		try {
			rs = stmt.executeQuery(sql);
			
			// 데이터 넣어주기
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				
				mVO.setAno(rs.getInt("ano"));
				mVO.setSavename(rs.getString("savename"));
				mVO.setGen(rs.getString("gen"));
				
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return list;
	}

	// 아이디 카운트 조회 함수
	public int getIdCount(String id) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 입력
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			// 자원 반환
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 회원목록 조회 함수
	public ArrayList<MemberVO> getMemberList(){
		// 반환값 변수 초기화
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		// db 접근
		con = db.getCon();
		stmt = db.getStmt(con);
		
		// 명령 완성
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_LIST);
		
		// 명령 실행
		try {
			rs = stmt.executeQuery(sql);
			
			// 데이터 뽑아오기
			while(rs.next()) {
				// 입력
				MemberVO mVO = new MemberVO();
				
				mVO.setMno(rs.getInt("mno"));
				mVO.setName(rs.getString("name"));
				
				// 리스트에 넣기
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 자원 반환
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return list;
	}

	// 회원번호로 정보 조회 함수
	public MemberVO getMnoInfo(int mno) {
		// 반환값 변수
		MemberVO mVO = new MemberVO();
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MNO_INFO);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setInt(1, mno);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 채우기
			mVO.setMno(rs.getInt("mno"));
			mVO.setName(rs.getString("name"));
			mVO.setId(rs.getString("id"));
			mVO.setMail(rs.getString("mail"));
			mVO.setJdate(rs.getDate("joindate"));
			mVO.setJtime(rs.getTime("joindate"));
			mVO.setSdate();
			mVO.setTel(rs.getString("tel"));
			mVO.setGen(rs.getString("gen"));
			mVO.setAno(rs.getInt("avt"));
			
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
	
	// id로 정보조회 함수
	public MemberVO getMemberInfo(String id) {

		// 반환값 변수
		MemberVO mVO = new MemberVO();
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MEMB_INFO);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setString(1, id);
			
			// 명령 실행
			rs = pstmt.executeQuery();
			rs.next();
			
			// 데이터 채우기
			mVO.setMno(rs.getInt("mno"));
			mVO.setName(rs.getString("name"));
			mVO.setId(rs.getString("id"));
			mVO.setMail(rs.getString("mail"));
			mVO.setJdate(rs.getDate("joindate"));
			mVO.setJtime(rs.getTime("joindate"));
			mVO.setSdate();
			mVO.setTel(rs.getString("tel"));
			mVO.setGen(rs.getString("gen"));
			mVO.setAno(rs.getInt("avt"));
			
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

	// 회원 탈퇴 함수
	public int delMember(int mno) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.DEL_MEMBER);
		pstmt = db.getPstmt(con, sql);
		
		// 명령 완성
		try {
			pstmt.setInt(1, mno);
			
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

	// 회원정보 수정 함수
	public int editMyInfo(int mno, String psql) {
		// 반환값 변수 초기화
		int cnt = 0;
		
		// db 접근
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.EDIT_MEMBER);
		
		// 질의명령 완성
		sql = sql.replace("###", psql);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
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
}
