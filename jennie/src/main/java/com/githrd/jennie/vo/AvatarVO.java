package com.githrd.jennie.vo;

public class AvatarVO {
	private int ano;
	private long len;
	private String savename, dir, gen;
	
	public AvatarVO() {}

	// Getter Setter
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public long getLen() {
		return len;
	}

	public void setLen(long len) {
		this.len = len;
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}
}
