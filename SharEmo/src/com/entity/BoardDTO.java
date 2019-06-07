package com.entity;

public class BoardDTO {
	private String userid;
	private int num;
	private String author;
	private String title;
	private String content;
	private int likes;
	private int readcnt;
	private String writeday;
	private int repRoot;
	private int repStep;
	private int repIndent;
	
	public BoardDTO() {	}
	public BoardDTO(String userid, int num,String author, String title, String content,int likes, int readcnt,
			String writeday, int repRoot, int repStep, int repIndent) {
		this.userid=userid;
		this.num=num;
		this.author=author;
		this.title=title;
		this.content=content;
		this.likes=likes;
		this.readcnt=readcnt;
		this.writeday=writeday;
		this.repRoot=repRoot;
		this.repStep=repStep;
		this.repIndent=repIndent;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num=num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt=readcnt;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday=writeday;
	}
	public int getRepRoot() {
		return repRoot;
	}
	public void setRepRoot(int repRoot) {
		this.repRoot=repRoot;
	}
	public int getRepStep() {
		return repStep;
	}
	public void setRepStep(int repStep) {
		this.repStep=repStep;	
	}
	public int getRepIndent() {
		return repIndent;
	}
	public void setRepIndent(int repIndent) {
		this.repIndent=repIndent;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
