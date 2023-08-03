package com.teamproject.teamproject.member;

public class Member {

	private String tp_m_id;
	private String tp_m_pw;
	private String tp_m_nick;
	private String tp_m_name;
	private String tp_m_addr;
	private String tp_m_photo;
	private int tp_m_role;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String tp_m_id, String tp_m_pw, String tp_m_nick, String tp_m_name, String tp_m_addr,
			String tp_m_photo, int tp_m_role) {
		super();
		this.tp_m_id = tp_m_id;
		this.tp_m_pw = tp_m_pw;
		this.tp_m_nick = tp_m_nick;
		this.tp_m_name = tp_m_name;
		this.tp_m_addr = tp_m_addr;
		this.tp_m_photo = tp_m_photo;
		this.tp_m_role = tp_m_role;
	}

	public String getTp_m_id() {
		return tp_m_id;
	}

	public void setTp_m_id(String tp_m_id) {
		this.tp_m_id = tp_m_id;
	}

	public String getTp_m_pw() {
		return tp_m_pw;
	}

	public void setTp_m_pw(String tp_m_pw) {
		this.tp_m_pw = tp_m_pw;
	}

	public String getTp_m_nick() {
		return tp_m_nick;
	}

	public void setTp_m_nick(String tp_m_nick) {
		this.tp_m_nick = tp_m_nick;
	}

	public String getTp_m_name() {
		return tp_m_name;
	}

	public void setTp_m_name(String tp_m_name) {
		this.tp_m_name = tp_m_name;
	}

	public String getTp_m_addr() {
		return tp_m_addr;
	}

	public void setTp_m_addr(String tp_m_addr) {
		this.tp_m_addr = tp_m_addr;
	}

	public String getTp_m_photo() {
		return tp_m_photo;
	}

	public void setTp_m_photo(String tp_m_photo) {
		this.tp_m_photo = tp_m_photo;
	}

	public int getTp_m_role() {
		return tp_m_role;
	}

	public void setTp_m_role(int tp_m_role) {
		this.tp_m_role = tp_m_role;
	}
	
	
	
}
