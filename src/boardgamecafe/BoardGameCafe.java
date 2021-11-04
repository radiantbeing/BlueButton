package boardgamecafe;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class BoardGameCafe extends Manager {
	Manager gameMgr = new Manager();
	Manager snackMgr = new Manager();
	Manager userMgr = new Manager();
	Manager rgUserMgr = new Manager();
	Scanner scan = new Scanner(System.in);
	
	public void run() {
		gameMgr.readAll("boardgame.txt", new Factory() {
			public Manageable create() {
				return new Game();
			}
		});
		
		snackMgr.readAll("snack.txt", new Factory() {
			public Manageable create() {
				return new Snack();
			}
		});
		
		userMgr.readAll("customer.txt", new Factory() {
			public Manageable create() {
				return new User();
			}
		});
		
		// 회원가입한 유저와 일반유저 객체를 User하나로 통합하고 싶은데 아시는분 있으면 수정 부탁드립니다 ㅠㅠ
		rgUserMgr.readAll("registeredCustomer.txt", new Factory() {
			public Manageable create() {
				return new RegisteredUser();
			}
		});
		
		gameMgr.printAll();
		snackMgr.printAll();
		userMgr.printAll();
		rgUserMgr.printAll();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardGameCafe bgc = new BoardGameCafe();
		bgc.run();
	}
}

// 일단 기능을 추가하기 전에 가장 기본이 될 것 같은 Manager클래스 이용해서 만들어본 코드입니다
// 더 추가하고 싶으신거 있으시면 자유롭게 추가하시면 될거 같아요!!
