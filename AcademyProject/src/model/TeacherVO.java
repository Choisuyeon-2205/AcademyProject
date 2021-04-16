package model;

import java.sql.Date;

public class TeacherVO {
	private int tid;   
	private String tname; 
	private String tphone;  
	private String temail;
	private Date tbirth;            
	private String tphoto;       
	private String tself;

	public TeacherVO() {
		super();
	}
	
	public TeacherVO(int tid, String tname, String tphone, String tself) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tphone = tphone;
		this.tself = tself;
	}
	
	public TeacherVO(int tid, String tname, String tphone, String temail, Date tbirth, String tphoto, String tself) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tphone = tphone;
		this.temail = temail;
		this.tbirth = tbirth;
		this.tphoto = tphoto;
		this.tself = tself;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTphone() {
		return tphone;
	}

	public void setTphone(String tphone) {
		this.tphone = tphone;
	}

	public String getTemail() {
		return temail;
	}

	public void setTemail(String temail) {
		this.temail = temail;
	}

	public Date getTbirth() {
		return tbirth;
	}

	public void setTbirth(Date tbirth) {
		this.tbirth = tbirth;
	}

	public String getTphoto() {
		return tphoto;
	}

	public void setTphoto(String tphoto) {
		this.tphoto = tphoto;
	}

	public String getTself() {
		return tself;
	}

	public void setTself(String tself) {
		this.tself = tself;
	}

	@Override
	public String toString() {
		return "TeacherVO [tid=" + tid + ", tname=" + tname + ", tphone=" + tphone + ", temail=" + temail + ", tbirth="
				+ tbirth + ", tphoto=" + tphoto + ", tself=" + tself + "]";
	}

	
}

