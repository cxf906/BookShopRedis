package com.icss.bk.dto;

public class BookComment {
	private long aid;        //序号，用当前时间的毫秒数自动生成
	private String uname;    //评论人
	private String info;     //评论信息
	private String pdate;    //评论时间
	private int zanNum;      //点赞次数	
	
	
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public int getZanNum() {
		return zanNum;
	}
	public void setZanNum(int zanNum) {
		this.zanNum = zanNum;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

}
