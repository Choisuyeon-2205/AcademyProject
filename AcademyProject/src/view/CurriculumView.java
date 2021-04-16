package view;

import java.util.List;
import model.CurriculumVO;

public class CurriculumView {
	public static void curriculumPrint(String message) {
		System.out.println("***** 알림 *****");
		System.out.println("메시지:"+ message);
	}
	
	public static void curriculumPrint(List<CurriculumVO> curilist) {
		for(CurriculumVO curriculum: curilist) {
			curriculumPrint(curriculum);
		}
	}
	
	public static void curriculumPrint(CurriculumVO curriculum) {
		System.out.println("********"+curriculum.getCname()+"과정*******");
		System.out.println("no: "+curriculum.getCid());
		System.out.println("name: "+curriculum.getCname());
		System.out.println("contents: "+curriculum.getCcontents());
		System.out.println("tuition fee: "+curriculum.getCprice());
		System.out.println("last date: "+curriculum.getCdate());
		System.out.println("total number: "+curriculum.getCtotal());
		System.out.println("current number: "+curriculum.getCcurrent());
		System.out.println("teacher id: "+curriculum.getTid());
	}
	
	public static void curriculumTnamePrint(List<CurriculumVO> curilist) {
		for(CurriculumVO curriculum: curilist) {
			curriculumTnamePrint(curriculum);
		}
	}
	
	public static void curriculumTnamePrint(CurriculumVO curriculum) {
		System.out.println("********"+curriculum.getCname()+"과정*******");
		System.out.println("no: "+curriculum.getCid());
		System.out.println("name: "+curriculum.getCname());
		System.out.println("contents: "+curriculum.getCcontents());
		System.out.println("tuition fee: "+curriculum.getCprice());
		System.out.println("last date: "+curriculum.getCdate());
		System.out.println("total number: "+curriculum.getCtotal());
		System.out.println("current number: "+curriculum.getCcurrent());
		System.out.println("teacher id: "+curriculum.getTid());
		System.out.println("teacher name: "+curriculum.getTname());
	}

	public static void curriculumRdatePrint(List<CurriculumVO> curilist) {
		for(CurriculumVO curriculum: curilist) {
			curriculumRdatePrint(curriculum);
		}	
	}
	public static void curriculumRdatePrint(CurriculumVO curriculum) {
		System.out.println("********"+curriculum.getCname()+"과정*******");
		System.out.println("no: "+curriculum.getCid());
		System.out.println("name: "+curriculum.getCname());
		System.out.println("contents: "+curriculum.getCcontents());
		System.out.println("tuition fee: "+curriculum.getCprice());
		System.out.println("last date: "+curriculum.getCdate());
		System.out.println("total number: "+curriculum.getCtotal());
		System.out.println("current number: "+curriculum.getCcurrent());
		System.out.println("teacher id: "+curriculum.getTid());
		System.out.println("register date: "+curriculum.getRdate());
	}
}