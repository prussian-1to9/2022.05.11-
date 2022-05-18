package com.githrd.jennie.db;
import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.*;
/**
 * 	Connection Pool에 있는 Connection을 이용하여,
 * 	db 작업에 필요한 자원을 만드는 Utility한 클래스
 * 
 * 	Connection PoolにあるConnectionを利用し、
 * 	db作用に必要な資源を作る utility的なClass.
 * 	
 * 	@author 최이지
 * 	@since	2022.05.12
 * 	@version v.1.0
 */
public class BlpDBCP {
	// 이 Class가 new될 때、 context.xml 의 자원을 갖고 와야 한다.
	// 이 기법을 JNDI(Java Naming and Directory Interface) 기법이라고 한다.
	private DataSource ds;
	
	public BlpDBCP() {
		try {
			// 1. context.xml에 등록한 자원을 알아낸다.
			InitialContext context = new InitialContext();
			
			// 2. 그 중 팔요한 자원을 얻는다.
			ds = (DataSource)context.lookup("java:/comp/env/jdbc/TestDB");
			/*
				이름찾기 규칙
				名前探しの規則
				
					java:/comp/env/jdbc/찾는이름探す名前
					
				We use...
					java:/comp/env/jdbc/TestDB
					
				==> 서버의 환경 중, jdbc/TestDB라는 이름의 자원을 찾는 명령
					이 작업이 완료되면 Connection Pool을 찾아 무사히 기억한 것.
			 */
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
/*
		접속이 필요한 경우, 직접 하는것이 아니라
		Connection Pool의 Connection 을 하나 꺼내오는것이 효율적이다.
 */
	public Connection getCon() {
		// 반환값 변수
		Connection con = null;
		
		try {
			// ConnectionPool을 관리하는 DataSource에서 하나 꺼내오기
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	// Statemnet 반환 메소드
	public Statement getStmt(Connection con) {
		// 반환값 변수
		Statement stmt = null;
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return stmt;
	}
	
	// Prepared Statement 반환 메소드
	public PreparedStatement getPstmt(Connection con, String sql) {
		// 반환값 변수
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pstmt;
	}
	
	// 자원 반환 메소드
	public void close(Object o) {
		try {
			if(o instanceof ResultSet) ((ResultSet)o).close();
			else if(o instanceof PreparedStatement) ((PreparedStatement)o).close();
			else if(o instanceof Statement) ((Statement)o).close();
			else if(o instanceof Connection) ((Connection)o).close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
