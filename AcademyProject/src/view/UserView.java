package view;

import java.util.List;
import model.UsersVO;

public class UserView {
	public static void userPrint(String message) {
		System.out.println("***** 알림 *****");
		System.out.println("메시지:"+ message);
	}
	
	public static void userPrint(List<UsersVO> userlist) {
		for(UsersVO user: userlist) {
			userPrint(user);
		}
	}
	
	public static void userPrint(UsersVO user) {
		System.out.println("********"+user.getUname()+"님의 정보*******");
		System.out.println("id: "+user.getUserid());
		System.out.println("password: "+user.getUpw());
		System.out.println("name "+user.getUname());
		System.out.println("phone number: "+user.getUphone());
		System.out.println("email: "+user.getUemail());
		System.out.println("birthday: "+user.getUbirth());
		System.out.println("photo: "+user.getUphoto());
	}
	
	public static void userRegPrint(UsersVO user) {
		System.out.println("********"+user.getUname()+"님의 등록 정보*******");
		System.out.println("등록번호: "+user.getRseq());
		System.out.println("등록일: "+user.getRdate());
		System.out.println("id: "+user.getUserid());
		System.out.println("password: "+user.getUpw());
		System.out.println("name "+user.getUname());
		System.out.println("phone number: "+user.getUphone());
		System.out.println("email: "+user.getUemail());
		System.out.println("birthday: "+user.getUbirth());
		System.out.println("photo: "+user.getUphoto());
	}
	public static void userRegPrint(List<UsersVO> userlist) {
		for(UsersVO user: userlist) {
			userRegPrint(user);
		}
	}
}
