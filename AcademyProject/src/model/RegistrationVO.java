package model;

import java.sql.Date;

public class RegistrationVO {
	private int rseq;  
	private int cid;
	private String userid;
	private Date rdate;
	
	public RegistrationVO() {
		super();
	}

	public RegistrationVO(int rseq, int cid, String userid, Date rdate) {
		super();
		this.rseq = rseq;
		this.cid = cid;
		this.userid = userid;
		this.rdate = rdate;
	}

	public int getRseq() {
		return rseq;
	}

	public void setRseq(int rseq) {
		this.rseq = rseq;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	@Override
	public String toString() {
		return "[rseq=" + rseq + ", cid=" + cid + ", userid=" + userid + ", rdate=" + rdate + "]";
	}
	
	
}
