package com.ssafy.pjt.board.dto;

public class BoardDto {
	private int bno; //
	private String writer; // id;
	private String title; // title;
	private String content; // content;
	private String registerTime; // tegister_time;

	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int bno, String writer, String title, String content, String registerTime) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.registerTime = registerTime;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "BoardDto [articleNo=" + bno + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", registerTime=" + registerTime + "]";
	}

}
