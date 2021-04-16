package controller;

import java.sql.Date;
import java.util.Scanner;
import model.AcademyDAO;
import model.CurriculumVO;
import model.RegistrationVO;
import model.TeacherVO;
import model.UsersDAO;
import model.UsersVO;
import view.CurriculumView;
import view.RegistrationView;
import view.TeacherView;
import view.UserView;



public class AcademyController {

	public static void main(String[] args) {
		int control= 0;

		while(true) {
			Scanner sc= new Scanner(System.in);
			System.out.println("===========================");
			System.out.println("          안녕하세요.         ");
			System.out.println("     kosta academy입니다.    ");
			System.out.println("===========================");
			System.out.println("0. 관리자 로그인");
			System.out.println("1. 회원 사용자 로그인");
			System.out.println("2. 프로그램 종료");
			System.out.println("===========================");
			control = sc.nextInt();
			if(control==0) {
				System.out.println("관리자 프로그램 접속합니다.---");
				academyController();
				System.out.println("관리자 프로그램 종료합니다.---");
			}else if(control==1) {
				System.out.println("사용자 프로그램 접속합니다.---");
				userController();
				System.out.println("사용자 프로그램 종료합니다.---");
			}else {
				System.out.println("프로그램 종료");
				System.exit(0);
			}
		}
	}

	private static void userController() {
		UsersDAO usersDAO = new UsersDAO();
		UsersVO user = null;
		int result= 0, cid= 0;
		String c_user=null, c_pass= null, re_pass= null;
		String check_id=null, check_phone= null;
		String tname= null;
		
		while(true) {
			System.out.println("===========작업선택===========");
			System.out.println("0.로그인");
			System.out.println("1.회원가입");
			System.out.println("2.특정 강사의 커리큘럼 조회(by 강사이름)");
			System.out.println("3.현재 모집중인 커리큘럼 조회");
			System.out.println("4.커리큘럼 참여하기(등록)");
			System.out.println("5.참여중인 커리큘럼 모두 조회");
			System.out.println("6.등록한 커리큘럼 취소");
			System.out.println("7.내정보 조회");
			System.out.println("8.내정보 변경");
			System.out.println("9.회원 탈퇴");
			System.out.println("10.exit");
			System.out.println("===========================");
			Scanner sc= new Scanner(System.in);
			int work= sc.nextInt();
			switch(work) {
			case 0:
				System.out.println("아이디 비밀번호>>");
				c_user= sc.next();
				c_pass= sc.next();
				boolean result2 = usersDAO.userLogin(c_user,c_pass);
				UserView.userPrint(result2?"로그인 성공":"로그인 실패");
				break;
			case 1:
				user= new UsersVO();
				while(true) {
				System.out.print("id>>");
				check_id= sc.next();
				result = usersDAO.userCheckId(check_id);
				if(result==1) {
					user.setUserid(check_id);
					break;
					}
				}
				System.out.print("password>>");
				user.setUpw(sc.next());
				System.out.print("name>>");
				user.setUname(sc.next());
				while(true) {
					System.out.print("phone number(ex.010-8447-7807)>>");
					check_phone= sc.next();
					result = usersDAO.userCheckPhone(check_phone);
					if(result==1) {
						user.setUphone(check_phone);
						break;
						}
					}
				System.out.print("email>>");
				user.setUemail(sc.next());
				System.out.print("birthday(ex.1998-01-20)>>");
				user.setUbirth(Date.valueOf(sc.next()));
				System.out.print("images>>");
				user.setUphoto(sc.next());
				
				result = usersDAO.userSignUp(user);
				UserView.userPrint(result>0?"회원가입 성공":"회원가입 실패");
				break;
			case 2:
				if(c_user== null || c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("강사 이름을 입력하세요.>>");
				tname= sc.next();
				CurriculumView.curriculumTnamePrint(usersDAO.selectByTeacher(tname));
				break;
			case 3:
				if(c_user== null || c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("현재 모집중인 커리큘럼 조회");
				CurriculumView.curriculumPrint(usersDAO.selectCurrentCurriculumList());
				break;
			case 4:
				if(c_user==null|| c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("참여하고 싶은 커리큘럼의 id(cid)를 입력하세요.>>");
				cid= sc.nextInt();
				
				result = usersDAO.registerCurriculum(cid, c_user);
				UserView.userPrint(result>0?"커리큘럼 등록 성공":"커리큘럼 등록 실패");
				break;
			case 5:
				if(c_user==null|| c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("내가 등록한 커리큘럼 목록 조회");
				CurriculumView.curriculumRdatePrint(usersDAO.selectMyCurriculum(c_user));
				break;
			case 6:
				if(c_user==null|| c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("취소하고 싶은 커리큘럼의 id(cid)를 입력하세요.>>");
				cid= sc.nextInt();
				
				result = usersDAO.cancelCurriculum(cid, c_user);
				UserView.userPrint(result>0?"커리큘럼 취소 성공":"커리큘럼 취소 실패");
				break;
			case 7:
				if(c_user==null|| c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("내정보 조회");
				UserView.userPrint(usersDAO.selectMyInformation(c_user));
				break;
			case 8:
				if(c_user==null|| c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println(c_user+"님의 비밀번호를 다시 입력하세요>>");
				re_pass= sc.next();
				user= new UsersVO();
				System.out.print("수정할 password>>");
				user.setUpw(sc.next());
				System.out.print("수정할 name>>");
				user.setUname(sc.next());
				System.out.print("수정할 phone>");
				user.setUphone(sc.next());
				System.out.print("수정할 email>>");
				user.setUemail(sc.next());
				System.out.print("수정할 photo url>");
				user.setUphoto(sc.next());
				
				result = usersDAO.updateMyInformation(user, c_user, re_pass);
				UserView.userPrint(result>0?"Update성공":"Update실패");
				break;
			case 9:
				if(c_user==null|| c_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("탈퇴하시겠습니까?(Y/N):");
				if((sc.next()).equals("Y")) {
					System.out.println(c_user+"님의 비밀번호를 다시 입력하세요>>");
					re_pass= sc.next();
					result= usersDAO.userDelete(c_user, re_pass);
					if(result>0) {
						System.out.println(c_user+"님 탈퇴 완료되었습니다.");
						System.out.println("자동으로 로그아웃합니다.");
						return;
					}else {
						System.out.println("비밀번호가 틀리거나, 수강중인 강좌가 있을 경우 탈퇴할 수 없습니다.");
						}
				}else {
					System.out.println("탈퇴 취소되었습니다.");
				}
				break;
			case 10:
				if(c_pass!=null)
					System.out.println("로그아웃 합니다.");
				return;
			default:
				break;
			}
		}
	}

	private static void academyController() {
		String a_pass= null;
		AcademyDAO academyDAO = new AcademyDAO();
		CurriculumVO curriculum = null;
		TeacherVO teacher = null;
		int result = 0;
		
		while(true) {
			System.out.println("===========작업선택===========");
			System.out.println("0.로그인");
			System.out.println("1. 전체 사용자 조회");
			System.out.println("2. 사용자 ID로 조회");
			System.out.println("3. 사용자 이름로 조회");
			System.out.println("4. 사용자 정보 삭제");
			System.out.println("5. 전체 커리큘럼 조회");
			System.out.println("6. 새로운 커리큘럼 등록");
			System.out.println("7. 기존 커리큘럼 삭제");
			System.out.println("8. 전체 강사 조회");
			System.out.println("9. 새로운 강사 등록");
			System.out.println("10. 기존 강사 삭제");
			System.out.println("11. 등록건 전체 조회");
			System.out.println("12. 커리큘럼 별 등록건 조회");
			System.out.println("13. 커리큘럼 정보 변경");
			System.out.println("14. 강사 정보 변경");
			System.out.println("15. exit");
			System.out.println("===========================");
			
			Scanner sc= new Scanner(System.in);
			int work= sc.nextInt();
			
			switch(work) {
			case 0:
				System.out.println("비밀번호를 입력하세요.>>");
				a_pass= sc.next();
				if(!a_pass.equals("1234")) {
					System.out.println("틀린 비밀번호입니다.");
					return;
					}
				break;
			case 1:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("전체 사용자 조회");
				UserView.userPrint(academyDAO.selectUserList());
				break;
			case 2:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("조회할 사용자의 id를 입력하세요>>");
				String userId= sc.next();
				UserView.userPrint(academyDAO.selectById(userId));
				break;
			case 3:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("조회할 사용자 이름을 입력하세요>>");
				String userName= sc.next();
				System.out.println(userName+"로 검색한 결과입니다.");
				UserView.userPrint(academyDAO.selectByName(userName));
				break;
			case 4:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("삭제할 사용자의 id(userid)를 입력하세요>>");
				UserView.userPrint(academyDAO.deleteUser(sc.next())+"건 삭제");
				break;
			case 5:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("전체 커리큘럼 조회");
				CurriculumView.curriculumPrint(academyDAO.selectCurriculumList());
				break;
			case 6:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				curriculum= new CurriculumVO();
				System.out.println("커리큘럼 정보 입력");
				sc.nextLine();
				System.out.print("name>>");
				curriculum.setCname(sc.nextLine());
				System.out.print("contents>>");
				curriculum.setCcontents(sc.nextLine());
				System.out.print("tuition fee>>");
				curriculum.setCprice(sc.nextInt());
				System.out.print("end date>>");
				curriculum.setCdate(Date.valueOf(sc.next()));
				System.out.print("total number>>");
				curriculum.setCtotal(sc.nextInt());
				System.out.print("teacher ID>>");
				curriculum.setTid(sc.nextInt());
				
				result = academyDAO.insertCurriculum(curriculum);
				CurriculumView.curriculumPrint(result>0?"커리큘럼 등록 성공":"커리큘럼 등록 실패");
				break;
			case 7:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("삭제할 커리큘럼의 id(cid)를 입력하세요>>");
				CurriculumView.curriculumPrint(academyDAO.deleteCurriculum(sc.nextInt())+"건 삭제");
				break;
			case 8:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("강사 전체 조회");
				TeacherView.teacherPrint(academyDAO.selectTeacherList());
				break;
			case 9:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				teacher= new TeacherVO();
				System.out.println("강사 정보 입력");
				System.out.print("id(num)>>");
				teacher.setTid(sc.nextInt());
				System.out.print("name>>");
				teacher.setTname(sc.next());
				System.out.print("phone number>>");
				teacher.setTphone(sc.next());
				System.out.print("email>>");
				teacher.setTemail(sc.next());
				System.out.print("birthday>>");
				teacher.setTbirth(Date.valueOf(sc.next()));
				System.out.print("image>>");
				teacher.setTphoto(sc.next());
				sc.nextLine(); 
				System.out.print("self produce>>");
				teacher.setTself(sc.nextLine());
				
				result = academyDAO.insertTeacher(teacher);
				TeacherView.teacherPrint(result>0?"강사 등록 성공":"강사 등록 실패");
				break;
			case 10:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.print("삭제할 강사의 id(tid)를 입력하세요>>");
				TeacherView.teacherPrint(academyDAO.deleteTeacher(sc.nextInt())+"건 삭제");
				break;
			case 11:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				RegistrationView.registrationPrint(academyDAO.selectRegistrationList());
				break;
			case 12:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				System.out.println("알고싶은 커리큘럼의 id를 입력하세요>>");
				int cid= sc.nextInt();
				System.out.println("하나의 커리큘럼 등록건 조회");
				UserView.userRegPrint(academyDAO.selectRegistrationListByCid(cid));
				break;
			case 13:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				curriculum= new CurriculumVO();
				System.out.print("수정할 커리큘럼 번호(cid)를 입력하세요>>");
				curriculum.setCid(sc.nextInt());
				System.out.print("수정할 커리큘럼명>>");
				sc.nextLine();
				curriculum.setCname(sc.nextLine());;
				System.out.print("수정할 contents(커리큘럼 설명)>>");
				curriculum.setCcontents(sc.nextLine());
				System.out.print("수정할 tuition fee>>");
				curriculum.setCprice(sc.nextInt());
				System.out.print("end date>>");
				curriculum.setCdate(Date.valueOf(sc.next()));
				System.out.print("총 인원수>");
				curriculum.setCtotal(sc.nextInt());
				System.out.print("현재 인원수>>");
				curriculum.setCcurrent(sc.nextInt());
				System.out.print("강사 id>>");
				curriculum.setTid(sc.nextInt());
				result = academyDAO.updateCurriculum(curriculum);
				CurriculumView.curriculumPrint(result>0?"Update성공":"Update실패");
				break;
			case 14:
				if(a_pass==null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				teacher= new TeacherVO();
				System.out.print("수정할 강사 번호(tid)를 입력하세요>>");
				teacher.setTid(sc.nextInt());
				System.out.print("수정할 강사 이름>>");
				teacher.setTname(sc.next());;
				System.out.print("수정할 phone number>>");
				teacher.setTphone(sc.next());
				System.out.print("수정할 email>");
				teacher.setTemail(sc.next());
				System.out.print("birthday>>");
				teacher.setTbirth(Date.valueOf(sc.next()));
				System.out.print("photo url>");
				teacher.setTphoto(sc.next());
				System.out.print("self produce>>");
				sc.nextLine();
				teacher.setTself(sc.nextLine());
				result = academyDAO.updateTeacher(teacher);
				CurriculumView.curriculumPrint(result>0?"Update성공":"Update실패");
				break;
			case 15:
				if(a_pass!=null)
					System.out.println("로그아웃 합니다.");
				return;
			default:
				break;
			}
		}
		
		
	}

}
