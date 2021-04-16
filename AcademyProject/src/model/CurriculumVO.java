package model;

import java.sql.Date;

public class CurriculumVO {
	private int cid;
	private String cname;
	private String ccontents;
	private int cprice;       
	private Date cdate;                
	private int ctotal;        
	private int ccurrent; 
	private int tid;
	private String tname;
	private Date rdate;
	
	public CurriculumVO() {
		super();
	}

	public CurriculumVO(int cid, String cname, int cprice, int tid) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cprice = cprice;
		this.tid = tid;
	}

	public CurriculumVO(int cid, String cname, String ccontents, int cprice, Date cdate, int ctotal, int ccurrent,
			int tid) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.ccontents = ccontents;
		this.cprice = cprice;
		this.cdate = cdate;
		this.ctotal = ctotal;
		this.ccurrent = ccurrent;
		this.tid = tid;
	}

	
	
	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcontents() {
		return ccontents;
	}

	public void setCcontents(String ccontents) {
		this.ccontents = ccontents;
	}

	public int getCprice() {
		return cprice;
	}

	public void setCprice(int cprice) {
		this.cprice = cprice;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getCtotal() {
		return ctotal;
	}

	public void setCtotal(int ctotal) {
		this.ctotal = ctotal;
	}

	public int getCcurrent() {
		return ccurrent;
	}

	public void setCcurrent(int ccurrent) {
		this.ccurrent = ccurrent;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "CurriculumVO [cid=" + cid + ", cname=" + cname + ", ccontents=" + ccontents + ", cprice=" + cprice
				+ ", cdate=" + cdate + ", ctotal=" + ctotal + ", ccurrent=" + ccurrent + ", tid=" + tid + "]";
	}
	
}
