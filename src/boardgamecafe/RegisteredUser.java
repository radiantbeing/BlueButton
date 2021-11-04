package boardgamecafe;

import java.util.Scanner;

public class RegisteredUser extends User{
	String id;
	String pwd;
	int point;
	
	// super 클래스는 User입니다.
	@Override
	public void read(Scanner scan) {
		super.read(scan);
		pwd = scan.next();
		point = scan.nextInt();
	}
	
	@Override
	public void print() {
		printUserType();
		System.out.printf("이름 : %s 전화번호 : %s 포인트 : %d\n", name, phoneNumber, point);
		System.out.printf("\t%d번방 이용인원 : %d 예약시간 : %d 남은시간 : %d\n", 
							roomNum, reservedNum, reservationTime, remaintime);
	}
	
	public void printUserType() {
		// TODO Auto-generated method stub
		System.out.print("[회원] ");
	}
	
	@Override
	public boolean matches(String kwd) {
		if(super.matches(kwd))
			return true;
		if(id.equals(kwd))
			return true;
		if(pwd.equals(kwd))
			return true;
		return false;
	}
	
	// 추후 회원가입기능, 비밀번호 변경 등의 기능을 만들 때 사용할 부분
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
}
