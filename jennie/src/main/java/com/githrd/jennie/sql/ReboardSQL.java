package com.githrd.jennie.sql;

public class ReboardSQL {
	public final int SEL_ALL_LIST = 1001;
	public final int SEL_TOTAL_CNT = 1002;
	
	public ReboardSQL() {}

	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code){
			case SEL_ALL_LIST:
				buff.append("SELECT ");
				buff.append("	rno, rbno, upno, mno, id, body, savename, wdate, step ");
				buff.append("FROM ");
				buff.append("	( ");
				buff.append("		SELECT ");
				buff.append("			ROWNUM rno, rbno, upno, mno, id, body, savename, wdate, step ");
				buff.append("		FROM ");
				buff.append("			( ");
				buff.append("				SELECT ");
				buff.append("					rbno, NVL(upno, 0) upno, mno, id, body, savename, wdate, (level-1) step ");
				buff.append("				FROM ");
				buff.append("					reboard r, member m, avatar a ");
				buff.append("				WHERE ");
				buff.append("					r.isshow = 'Y' ");
				buff.append("					AND rbmno = mno ");
				buff.append("					AND avt = ano ");
				buff.append("				START WITH ");
				buff.append("					upno IS NULL ");
				buff.append("				CONNECT BY ");
				buff.append("					upno = PRIOR rbno ");
				buff.append("				ORDER SIBLINGS BY ");
				buff.append("					wdate DESC ");
				buff.append("			) ");
				buff.append("	) ");
				buff.append("WHERE ");
				buff.append("	rno BETWEEN ? AND ? ");
				break;
			case SEL_TOTAL_CNT:
				buff.append("SELECT ");
				buff.append("	COUNT(*) cnt ");
				buff.append("FROM ");
				buff.append("	reboard ");
				buff.append("WHERE ");
				buff.append("	isshow = 'Y' ");
				break;
		}
		return buff.toString();
	}
}
