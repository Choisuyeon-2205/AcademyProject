package model;

import java.sql.Date;

public class UsersVO {
	private String userid;
	private String upw;
	private String uname; 
	private String uphone;
	private String uemail;
	private Date ubirth;     
	private String uphoto;
	private int rseq;
	private Date rdate;

	public UsersVO() {
		super();
	}

	public UsersVO(String userid, String upw, String uname, String uphone) {
		super();
		this.userid = userid;
		this.upw = upw;
		this.uname = uname;
		this.uphone = uphone;
	}

	public UsersVO(String userid, String upw, String uname, String uphone, String uemail, Date ubirth, String uphoto) {
		super();
		this.userid = userid;
		this.upw = upw;
		this.uname = uname;
		this.uphone = uphone;
		this.uemail = uemail;
		this.ubirth = ubirth;
		this.uphoto = uphoto;
	}
	
	public int getRseq() {
		return rseq;
	}

	public void setRseq(int rseq) {
		this.rseq = rseq;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUpw() {
		return upw;
	}

	public void setUpw(String upw) {
		this.upw = upw;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public Date getUbirth() {
		return ubirth;
	}

	public void setUbirth(Date ubirth) {
		this.ubirth = ubirth;
	}

	public String getUphoto() {
		return uphoto;
	}

	public void setUphoto(String uphoto) {
		this.uphoto = uphoto;
	}

	@Override
	public String toString() {
		return "usersVO [userid=" + userid + ", upw=" + upw + ", uname=" + uname + ", uphone=" + uphone + ", uemail="
				+ uemail + ", ubirth=" + ubirth + ", uphoto=" + uphoto + "]";
	}
	
	
}
