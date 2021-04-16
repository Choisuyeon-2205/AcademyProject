package view;

import java.util.List;

import model.RegistrationVO;

public class RegistrationView {
	public static void registrationPrint(String message) {
		System.out.println("***** 알림 *****");
		System.out.println("메시지:"+ message);
	}
	
	public static void registrationPrint(List<RegistrationVO> reglist) {
		System.out.println("--------------------전체 등록건 조회-------------------");
		for(RegistrationVO registration: reglist) {
			registrationPrint(registration);
		}
		System.out.println("--------------------------------------------------");
	}
	
	public static void registrationPrint(RegistrationVO registration) {
		System.out.println(registration);
	}
}