package com.githrd.jennie.vo;

import java.util.ArrayList;
import java.sql.*;
import java.text.*;
public class BoardVO {
	private int mno, bno, upno, rno, ano, click, cnt, step;
	private String id, title, body, sdate, stime, avatar;
	private Date wdate;
	private Time wtime;
	ArrayList<FileVO> list;
	
	// Getter Setter
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd");
		sdate = form.format(wdate);
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
	}
	public Time getWtime() {
		return wtime;
	}
	public void setStime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		stime = form.format(wtime);
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setStime();
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public ArrayList<FileVO> getList() {
		return list;
	}
	public void setList(ArrayList<FileVO> list) {
		this.list = list;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}