package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class AcademyDAO {
	int count=0;
	
	//1. 전체 user조회
	public List<UsersVO> selectUserList() {
		List<UsersVO> userlist = new ArrayList<UsersVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from users";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			rs= st.executeQuery();
			while(rs.next()) {
				UsersVO user = makeUser(rs);
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return userlist;
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

	//2.  id로 사용자조회
	public UsersVO selectById(String userId) {
		UsersVO user = new UsersVO();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from users where userid= ?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, userId);
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

	//3. 이름으로 사용자 조회
	public List<UsersVO> selectByName(String userName) {
		List<UsersVO> userlist = new ArrayList<UsersVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from users where uname= ?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, userName);
			rs= st.executeQuery();
			while(rs.next()) {
				UsersVO user = makeUser(rs);
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return userlist;
	}

	//6. 커리큘럼 등록하기
	public int insertCurriculum(CurriculumVO curriculum) {
		int result=0;
		Connection conn= null;
		PreparedStatement st= null;
		
		String sql= "insert into curriculum values(CURRICULUM_SEQ.nextval,?,?,?,?,?,0,?)";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, curriculum.getCname());
			st.setString(2, curriculum.getCcontents());
			st.setInt(3, curriculum.getCprice());
			st.setDate(4, curriculum.getCdate());
			st.setInt(5, curriculum.getCtotal());
			st.setInt(6, curriculum.getTid());
			
			result= st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	//9. 새로운 강사 등록하기
	public int insertTeacher(TeacherVO teacher) {
		int result=0;
		Connection conn= null;
		PreparedStatement st= null;
		
		String sql= "insert into teacher values(?,?,?,?,?,?,?)";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, teacher.getTid());
			st.setString(2, teacher.getTname());
			st.setString(3, teacher.getTphone());
			st.setString(4, teacher.getTemail());
			st.setDate(5, teacher.getTbirth());
			st.setString(6, teacher.getTphoto());
			st.setString(7, teacher.getTself());
			
			result= st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	//5. 전체 커리큘럼 조회
	public List<CurriculumVO> selectCurriculumList() {
		List<CurriculumVO> curilist = new ArrayList<CurriculumVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from curriculum order by cid";
		
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

	//8. 전체 강사 조회
	public List<TeacherVO> selectTeacherList() {
		List<TeacherVO> teaclist = new ArrayList<TeacherVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from teacher order by tid";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			rs= st.executeQuery();
			while(rs.next()) {
				TeacherVO teacher = makeTeacher(rs);
				teaclist.add(teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return teaclist;
	}

	private TeacherVO makeTeacher(ResultSet rs) throws SQLException {
		TeacherVO teacher = new TeacherVO();
		teacher.setTbirth(rs.getDate("Tbirth"));
		teacher.setTemail(rs.getString("Temail"));
		teacher.setTid(rs.getInt("Tid"));
		teacher.setTname(rs.getString("Tname"));
		teacher.setTphone(rs.getString("Tphone"));
		teacher.setTphoto(rs.getString("Tphoto"));
		teacher.setTself(rs.getString("Tself"));
		
		return teacher;
	}

	//7. 기존 커리큘럼 삭제
	public int deleteCurriculum(int cid) {
		int result= 0;
		String sql= "delete from curriculum where cid=?";
		Connection conn;
		PreparedStatement st= null;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, cid);
			result= st.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		
		return result;
	}

	//10. 기존 강사 삭제
	public int deleteTeacher(int tid) {
		int result= 0;
		String sql= "delete from teacher where tid=?";
		Connection conn;
		PreparedStatement st= null;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, tid);
			result= st.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		
		return result;
	}

	//4. 사용자 정보 삭제
	public int deleteUser(String userid) {
		int result= 0;
		String sql= "delete from users where userid=?";
		Connection conn;
		PreparedStatement st= null;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, userid);
			result= st.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		
		return result;
	}

	//13. 커리큘럼 정보 변경
	public int updateCurriculum(CurriculumVO curriculum) {
		String sql= "update curriculum set cname=?, ccontents=?, cprice=?, cdate=?, ctotal=?, ccurrent=?, tid=? where cid=?";
		Connection conn;
		PreparedStatement st= null;
		int result=0;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, curriculum.getCname());
			st.setString(2, curriculum.getCcontents());
			st.setInt(3, curriculum.getCprice());
			st.setDate(4, curriculum.getCdate());
			st.setInt(5, curriculum.getCtotal());
			st.setInt(6, curriculum.getCcurrent());
			st.setInt(7, curriculum.getTid());
			st.setInt(8, curriculum.getCid());
	
			result= st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		return result;
	}

	//14. 강사 정보 변경
	public int updateTeacher(TeacherVO teacher) {
		String sql= "update teacher set tname=?, tphone=?, temail=?, tbirth=?, tphoto=?, tself=? where tid=?";
		Connection conn;
		PreparedStatement st= null;
		int result=0;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, teacher.getTname());
			st.setString(2, teacher.getTphone());
			st.setString(3, teacher.getTemail());
			st.setDate(4, teacher.getTbirth());
			st.setString(5, teacher.getTphoto());
			st.setString(6, teacher.getTself());
			st.setInt(7, teacher.getTid());
	
			result= st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}	
		return result;
	}

	//12. 커리큘럼 id별 등록건 조회
	public List<UsersVO> selectRegistrationListByCid(int cid) {
		List<UsersVO> userlist = new ArrayList<UsersVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		count=0;
		String sql= "select rseq, rdate, users.* from registration reg, users where users.userid=reg.userid and cid=?";
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, cid);
			rs= st.executeQuery();
			while(rs.next()) {
				UsersVO user = makeUser(rs);
				user.setRseq(rs.getInt("rseq"));
				user.setRdate(rs.getDate("rdate"));
				userlist.add(user);
				count++;
			}
			System.out.println(cid+"번 커리큘럼에 참여하고 있는 인원 수는 현재 "+count+"명 입니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return userlist;
	}

	//11. 등록건 모두 조회
	public List<RegistrationVO> selectRegistrationList() {
		List<RegistrationVO> reglist = new ArrayList<RegistrationVO>();
		Connection conn= null;
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql= "select * from registration";
		count= 0;
		
		conn= DBUtil.getConnection();
		
		try {
			st= conn.prepareStatement(sql);
			rs= st.executeQuery();
			while(rs.next()) {
				RegistrationVO registration = makeRegistration(rs);
				reglist.add(registration);
				count++;
			}
			System.out.println("전체 등록 건 수는 "+count+" 건 입니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return reglist;
		
	}

	private RegistrationVO makeRegistration(ResultSet rs) throws SQLException {
		RegistrationVO registration= new RegistrationVO();
		registration.setCid(rs.getInt("Cid"));
		registration.setRdate(rs.getDate("Rdate"));
		registration.setRseq(rs.getInt("Rseq"));
		registration.setUserid(rs.getString("Userid"));
		
		return registration;
	}
	
}
