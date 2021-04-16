package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import util.DBUtil;
public class UsersDAO {
	
	//1. 회원가입
	public int userSignUp(UsersVO user) {
		int result=0;
		Connection conn= null;
		PreparedStatement st= null;
		
		String sql= "insert into users values(?,?,?,?,?,?,?)";
		
		conn= DBUtil.getConnection();
		
		try {	
			st= conn.prepareStatement(sql);
			st.setString(1,user.getUserid());
			st.setString(2, user.getUpw());
			st.setString(3, user.getUname());
			st.setString(4, user.getUphone());
			st.setString(5, user.getUemail());
			st.setDate(6, user.getUbirth());
			st.setString(7, user.getUphoto());
			
			result= st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	//중복 아이디 체크
	public int userCheckId(String check_id) {
		int result=0;
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from users where userid= ?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, check_id);
			rs= st.executeQuery();
			if(rs.next()) {
				System.out.println("중복된 아이디입니다.");
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}
	
	//중복 핸드폰 번호 체크
	public int userCheckPhone(String check_phone) {
		int result=0;
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from users where uphone= ?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, check_phone);
			rs= st.executeQuery();
			if(rs.next()) {
				System.out.println("중복된 핸드폰번호입니다.");
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}

	//0. 로그인
	public boolean userLogin(String c_user, String c_pass) {
		UsersVO user = new UsersVO();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select upw from users where userid= ?";
		boolean result = false;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, c_user);
			rs= st.executeQuery();
			if(!rs.next()) {
				System.out.println("존재하지 않는 id입니다.");
			}else {
				result= c_pass.equals(rs.getString("upw"));
				if(!result) System.out.println("비밀번호가 맞지 않습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return result;
	}

	//3.현재 모집중인 커리큘럼 조회
	public List<CurriculumVO> selectCurrentCurriculumList()  {
		List<CurriculumVO> curilist = new ArrayList<CurriculumVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from curriculum where cdate+1 > sysdate order by cid";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			rs= st.executeQuery();
			while(rs.next()) {
				CurriculumVO curriculum = makeCurriculum(rs);
				curilist.add(curriculum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return curilist;
	}

	private CurriculumVO makeCurriculum(ResultSet rs) throws SQLException {
		CurriculumVO curriculum= new CurriculumVO();
		curriculum.setCcontents(rs.getString("Ccontents"));
		curriculum.setCcurrent(rs.getInt("Ccurrent"));
		curriculum.setCdate(rs.getDate("Cdate"));
		curriculum.setCid(rs.getInt("Cid"));
		curriculum.setCname(rs.getString("Cname"));
		curriculum.setCprice(rs.getInt("Cprice"));
		curriculum.setCtotal(rs.getInt("Ctotal"));
		curriculum.setTid(rs.getInt("Tid"));
		
		return curriculum;
	}

	//4.커리큘럼 참여하기(등록)
	public int registerCurriculum(int cid, String c_user) {
		int result=0;
		ResultSet rs= null;
		Connection conn= null;
		PreparedStatement st= null;
		String sql= "select cdate+1 from curriculum where cid=? and ccurrent<ctotal";
		String sql2= "insert into registration values(REG_SEQ.nextval,?,?,sysdate)";
		String sql3= "update curriculum set ccurrent= ccurrent+1 where cid=?";
		Date now = new Date();
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, cid);
			rs= st.executeQuery();
			if(!rs.next()) {
				System.out.println("해당하는 id의 커리큘럼이 없거나, 마감되었습니다.");
				return 0;
			}
			Date date= rs.getDate("cdate+1");
			if(now.after(date)) {
				System.out.println("신청할 수 없는 커리큘럼입니다.");
				return 0;
			}
			st= conn.prepareStatement(sql2);
			st.setInt(1, cid);
			st.setString(2, c_user);
			result= st.executeUpdate();
			if(result<=0) {
				System.out.println("insert문 실패");
			}else {
			st= conn.prepareStatement(sql3);
			st.setInt(1, cid);
			result= st.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	//6.등록한 커리큘럼 취소
	public int cancelCurriculum(int cid, String c_user) {
		int result=0;
		ResultSet rs= null;
		Connection conn= null;
		PreparedStatement st= null;
		String sql= "delete from registration where cid=? and userid=?";
		String sql2= "update curriculum set ccurrent= ccurrent-1 where cid=?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, cid);
			st.setString(2, c_user);
			result= st.executeUpdate();
			if(result<=0) {
				System.out.println("해당하는 커리큘럼을 수강하고 있지 않습니다.");
			}else {
				st= conn.prepareStatement(sql2);
				st.setInt(1, cid);
				result= st.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	//7.내정보 조회
	public UsersVO selectMyInformation(String userid) {
		UsersVO user = new UsersVO();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from users where userid= ?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, userid);
			rs= st.executeQuery();
			while(rs.next()) {
				user = makeUser(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return user;
	}
	
	private UsersVO makeUser(ResultSet rs) throws SQLException {
		//resultset에서 읽어서 VO객체를 만든다.
		UsersVO user= new UsersVO();
		user.setUbirth(rs.getDate("Ubirth"));
		user.setUemail(rs.getString("Uemail"));
		user.setUname(rs.getString("Uname"));
		user.setUphone(rs.getString("Uphone"));
		user.setUphoto(rs.getString("Uphoto"));
		user.setUpw(rs.getString("Upw"));
		user.setUserid(rs.getString("Userid"));
		
		return user;
	}

	//8.내정보 변경
	public int updateMyInformation(UsersVO user, String userid, String upw) {
		String sql= "update users set upw=?, uname=?, uphone=?, uemail=?, uphoto=? where userid=? and upw=?";
		Connection conn;
		PreparedStatement st= null;
		int result=0;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, user.getUpw());
			st.setString(2, user.getUname());
			st.setString(3, user.getUphone());
			st.setString(4, user.getUemail());
			st.setString(5, user.getUphoto());
			st.setString(6, userid);
			st.setString(7, upw);
			
			result= st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		return result;
	}

	//9. 회원 탈퇴하기
	public int userDelete(String userid, String userpw) {
		int result= 0;
		String sql= "delete from users where userid=? and upw=?";
		Connection conn;
		PreparedStatement st= null;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, userid);
			st.setString(2, userpw);
			result= st.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		
		return result;
	}

	//2. 특정 강사의 커리큘럼 조회
	public List<CurriculumVO> selectByTeacher(String tname) {
		List<CurriculumVO> curilist = new ArrayList<CurriculumVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select cur.*, tname from curriculum cur, teacher tea where cur.tid=tea.tid and tname=?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, tname);
			rs= st.executeQuery();
			while(rs.next()) {
				CurriculumVO curriculum = makeCurriculum(rs);
				curriculum.setTname(rs.getString("tname"));
				curilist.add(curriculum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return curilist;
	}

	//5.등록한 커리큘럼 모두 조회
	public List<CurriculumVO> selectMyCurriculum(String userid) {
		List<CurriculumVO> curilist = new ArrayList<CurriculumVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select cur.*, rdate 등록일 from registration reg, curriculum cur where reg.cid=cur.cid and userid= ?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, userid);
			rs= st.executeQuery();
			while(rs.next()) {
				CurriculumVO curriculum = makeCurriculum(rs);
				curriculum.setRdate(rs.getDate("등록일"));
				curilist.add(curriculum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return curilist;
	}

	
	
}
