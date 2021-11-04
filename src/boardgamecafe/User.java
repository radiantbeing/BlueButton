package boardgamecafe;

import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

public class User implements Manageable {
	String name;					
	String phoneNumber;
	// 이 밑의 부분도 아직 정해진게 없으므로 일단 통일된 값으로 사용했습니다.
	int reservedNum = 2;				
	int reservationTime = 60;			
	int remaintime = 40;					
	int roomNum = 3;
	ArrayList<Game> playGameList = new ArrayList<>();
	ArrayList<Snack> orderedSnackList = new ArrayList<>();
	
	// 최대한 저희가 배운 부분과 유사하게 만들어보았습니다. 지금 만든 모든 객체들이 이와 유사할 것입니다.
	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phoneNumber = scan.next();
	}

	@Override
	public void print() {
		printUserType();
		System.out.printf("이름 : %s 전화번호 : %s\n", name, phoneNumber);
		System.out.printf("\t%d번방 이용인원 : %d 예약시간 : %d 남은시간 : %d\n", 
							roomNum, reservedNum, reservationTime, remaintime);
		// 유저가 이용한 게임리스트 출력 추가 예정
		// 아니면 이용한 게임만 프린트 하도록 만들도 됩니다, 똑같은 방법으로 간식 리스트도 출력 가능할거 같아요
	}
	
	public void printUserType() {
		// TODO Auto-generated method stub
		System.out.print("[비회원] ");
	}

	@Override
	public boolean matches(String kwd) {
		if(name.contains(kwd))
			return true;
		if(phoneNumber.contains(kwd))
			return true;
		if(kwd.equals("" + roomNum))
			return true;
		return false;
	}
	
	public boolean matches(String[] kwdArr) {
		for(String kwd : kwdArr) {
			if(!matches(kwd))
				return false;
		}
		
		return true;
	}
	
	// 추후 회원가입, 자리예약 등의 기능에 사용될 메소드들입니다.
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhonenumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setReservedNum(int reservedNum) {
		this.reservedNum = reservedNum;
	}
	
	public void setReservationTime(int reservedTime) {
		this.reservationTime = reservedTime;
	}
	
	public void serRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	public void addGame(Game game) {
		playGameList.add(game);
	}
	
	public void addSnack(Snack snack) {
		orderedSnackList.add(snack);
	}
	
}
