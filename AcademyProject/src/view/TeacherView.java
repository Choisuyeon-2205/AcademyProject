package view;

import java.util.List;
import model.TeacherVO;

public class TeacherView {
	public static void teacherPrint(String message) {
		System.out.println("***** 알림 *****");
		System.out.println("메시지:"+ message);
	}
	
	public static void teacherPrint(List<TeacherVO> teaclist) {
		for(TeacherVO teacher: teaclist) {
			teacherPrint(teacher);
		}
	}
	
	public static void teacherPrint(TeacherVO teacher) {
		System.out.println("********"+teacher.getTname()+"강사 정보*******");
		System.out.println("ID: "+teacher.getTid());
		System.out.println("name: "+teacher.getTname());
		System.out.println("phone number: "+teacher.getTphone());
		System.out.println("email: "+teacher.getTemail());
		System.out.println("birthday: "+teacher.getTbirth());
		System.out.println("self produce: "+teacher.getTself());
		System.out.println("photo url: "+teacher.getTphoto());
	}
}
