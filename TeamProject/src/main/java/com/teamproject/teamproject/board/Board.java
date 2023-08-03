package com.teamproject.teamproject.board;

import java.util.Date;

public class Board {
	private int tp_b_no;
	private String tp_b_writer;
	private String tp_b_title;
	private String tp_b_txt;
	private String tp_b_photo;
	private Date tp_b_when;
	private String tp_b_notice;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int tp_b_no, String tp_b_writer, String tp_b_title, String tp_b_txt, String tp_b_photo, Date tp_b_when,
			String tp_b_notice) {
		super();
		this.tp_b_no = tp_b_no;
		this.tp_b_writer = tp_b_writer;
		this.tp_b_title = tp_b_title;
		this.tp_b_txt = tp_b_txt;
		this.tp_b_photo = tp_b_photo;
		this.tp_b_when = tp_b_when;
		this.tp_b_notice = tp_b_notice;
	}

	public int getTp_b_no() {
		return tp_b_no;
	}

	public void setTp_b_no(int tp_b_no) {
		this.tp_b_no = tp_b_no;
	}

	public String getTp_b_writer() {
		return tp_b_writer;
	}

	public void setTp_b_writer(String tp_b_writer) {
		this.tp_b_writer = tp_b_writer;
	}

	public String getTp_b_title() {
		return tp_b_title;
	}

	public void setTp_b_title(String tp_b_title) {
		this.tp_b_title = tp_b_title;
	}

	public String getTp_b_txt() {
		return tp_b_txt;
	}

	public void setTp_b_txt(String tp_b_txt) {
		this.tp_b_txt = tp_b_txt;
	}

	public String getTp_b_photo() {
		return tp_b_photo;
	}

	public void setTp_b_photo(String tp_b_photo) {
		this.tp_b_photo = tp_b_photo;
	}

	public Date getTp_b_when() {
		return tp_b_when;
	}

	public void setTp_b_when(Date tp_b_when) {
		this.tp_b_when = tp_b_when;
	}

	public String getTp_b_notice() {
		return tp_b_notice;
	}

	public void setTp_b_notice(String tp_b_notice) {
		this.tp_b_notice = tp_b_notice;
	}

	
	
}
