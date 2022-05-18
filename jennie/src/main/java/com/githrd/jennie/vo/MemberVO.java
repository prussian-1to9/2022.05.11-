package com.githrd.jennie.vo;

import java.sql.*;
import java.text.SimpleDateFormat;
public class MemberVO {
	private int mno, avt, ano;
	private String name, id, pw, mail, tel, sdate, gen, savename;
	private Date jdate;
	private Time jtime;
	
	public MemberVO() {}

	
	// Getter Setter
	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		this.sdate = form1.format(jdate);
	}

	public Date getJdate() {
		return jdate;
	}

	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}

	public Time getJtime() {
		return jtime;
	}

	public void setJtime(Time jtime) {
		this.jtime = jtime;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public int getAvt() {
		return avt;
	}


	public void setAvt(int avt) {
		this.avt = avt;
	}

	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public String getSavename() {
		return savename;
	}


	public void setSavename(String savename) {
		this.savename = savename;
	}
}
