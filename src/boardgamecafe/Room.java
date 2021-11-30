package boardgamecafe;

import java.util.Scanner;

import mgr.Manageable;

public class Room implements Manageable{
	//인원 최대 4명, 총 방 10개
	boolean reserve = false;			// false = 현재 예약 가능 상태, true = 현재 예약완료
	boolean use = false;				// false = 현재 사용중이 아님(예약 가능), true = 현재 사용중(예약 불가)
	int person = 0;
	int roomNumber = 0;

	public void setRoomNumber(int roomNumber){
		this.roomNumber = roomNumber;
	}

	public void setReserve() {reserve = !reserve;}
	public void setUse(){
		use = !use;
	}
	public void getUse() { return use; }
	@Override
	public void print() {
		System.out.printf("[%d번방] ", roomNumber++);
		if(this.reserve == false && this.use == false)
			System.out.printf("이용 가능 인원 : %d\t예약가능(미사용중)\n", person);
		else if(this.reserve == true && this.use == false)
			System.out.printf("현재 예약 인원 : %d\t예약중\n", person);
		else
			System.out.printf("현재 이용 인원 : %d\t현재 사용중\n", person);
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean matches(String wkd) {
		// TODO Auto-generated method stub
		return false;
	}
}
