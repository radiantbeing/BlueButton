package boardgamecafe;

import mgr.Manageable;

import java.util.Scanner;


public class Game implements Manageable {
	String code;
	String name;
	String location;
	String kinds;
	String difficulty;
	String condition;
	int quantity = 5;			// 일단 수량 정보는 입력된 데이터가 없어서 5로 통일합니다

	@Override
	public void read(Scanner scan) {
		code = scan.next();
		name = scan.next();
		location = scan.next();
		kinds = scan.next();
		difficulty = scan.next();
		condition = scan.next();
	}

	// 프린트 형식도 생각해놓은 형식이 없어서 일단 구현만 해놓았습니다.
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("%s %s 난이도 : %s 종류 : %s\n" + 
		"위치 : %s 대여가능수량 : %d\n", 
		code, name, difficulty, kinds, location,quantity);
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if (code.contains(kwd))
			return true;
		if (name.contains(kwd))
			return true;
		if (kinds.equals(kwd))
			return true;
		if (difficulty.equals(kwd))
			return true;
		if (condition.equals(kwd))
			return true;
		return false;
	}
	
	// 이 부분도 마찬가지로, 게임 추가 등의 기능에서 사용될 예정입니다.
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	
	public void setDifiiculty(String difficulty) {
		this.difficulty = difficulty;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
