package boardgamecafe;

import java.util.Scanner;

public class Administrator{
	// 좌석 관리, 보드게임 관리(수량, 상태), 식음료 관리, 회원 관리(회원, 비회원, 포인트), 이용시간 조회
	Scanner scan = new Scanner(System.in);
	
	public void run() {
		adminMenu();
	}
	
	private void adminMenu() {
    	int num = 0;    	
		while(true) {
			// 좌석 관리, 보드게임 관리(수량, 상태), 식음료 관리, 회원 관리(회원, 비회원, 포인트), 이용시간 조회
			System.out.println("(1) 보드게임 관리 (2) 식음료 관리 (3) 회원 관리 " +
								"(4) 이용시간 조회 (5) 좌석 관리 기타: 관리자메뉴 종료");
			System.out.print(">> ");
			num = scan.nextInt();
			if(num < 1 || num > 6) break;
			switch(num) {
			case 1: registerGame(); break;
			case 2: snackRegister(); break;
			case 3: userRegister(); break;
			case 4: checkRemainTime(); break;
			case 5: roomRegister(); break;
			default: break;
			}
		}
	}
	
	// 일단 콘솔에다가 구현하는 거라 switch문이 많이 들어갔습니다.
	// 추후에 GUI개발시 JTable로 변경하고 지금의 기능들은 버튼 리스너로 들어갈거 같습니다.
	public void registerGame() {		// 보드게임 관리
		int num = 0;
		while(true) {
			System.out.println("(1) 게임 추가 (2) 게임 삭제 (3) 게임 관리 " +
								"그외. 종료");
			System.out.print(">> ");
			num = scan.nextInt();
			if(num < 1 || num > 4) return;
			switch(num) {
			case 1: addGame(); break;
			case 2: deleteGame(); break;
			case 3: gameRegisterMenu(); break;
			default: break;
			}
		}
	}
	
	private void gameRegisterMenu() {
		int code;
		String kwd;
		while(true) {
			// 콘솔의 한계상 관리번호를 이용하여 변경합니다
			System.out.printf("상태 변경 혹은 관리하고 싶은 게임의 관리번호를 쓰시오. (종료는 -1)\n>> ");
			code = scan.nextInt();
			if(code == -1)
				break;
			if(code > BoardGameCafe.gameMgr.getList().size()) {
				System.out.println("다시 입력해 주세요 -> 존재하지 않습니다.");
				continue;
			}
			
			Game g = (Game)BoardGameCafe.gameMgr.find("" + code);
			System.out.println("현재 선택된 게임 정보");
			g.print();
			System.out.printf("현재 선택한 게임의 정보를 바꾸실건가요(y/n)\n>> ");
			kwd = scan.next();
			changeGameInfo(kwd, g);
		}
	}
	
	private void deleteGame() {
		// TODO Auto-generated method stub
		int code;
		while(true) {
			System.out.printf("삭제하고 싶은 게임의 관리번호를 쓰시오. (종료는 -1)\n>> ");
			code = scan.nextInt();
			if(code == -1)
				break;
			if(code > BoardGameCafe.gameMgr.getList().size()) {
				System.out.println("다시 입력해 주세요 -> 존재하지 않습니다.");
				continue;
			}
			BoardGameCafe.gameMgr.getList().remove(code);
			for(int i = 0; i < BoardGameCafe.gameMgr.getList().size(); i++) {
				if(i >= code) {
					Game g = (Game)BoardGameCafe.gameMgr.getList().get(i);
					g.code--;
				}
			}
		}
	}
	
	private void addGame() {
		Game g = new Game();
	    
		int lastNum = BoardGameCafe.gameMgr.getList().size() - 1;
	    Game lastGame = (Game)BoardGameCafe.gameMgr.getList().get(lastNum);

		System.out.println("추가하고 싶으신 게임을 형식에 맞춰서 정확히 기입해 주세요");
		System.out.println("게임이름 장르 난이도 관리상태 (위치, 관리번호는 자동으로 입력됩니다)");
		
		g.code = lastGame.code + 1;
		g.name = scan.next();
		g.genre = scan.next();
		g.difficulty = scan.next();
		g.condition = scan.next();
		
		// example >> E24
		int locationRow = lastGame.location.substring(0).charAt(0);	// E
		int locationColumn = Integer.parseInt(lastGame.location.substring(1, 3));	// 24
		
		if(locationColumn == 30) {
			locationRow++;
			g.location = (char)locationRow + ("" + 0);		// 두개 합치기		
		}
		else{
			locationColumn += 1;
			g.location = (char)locationRow + ("" + locationColumn);
		}
		BoardGameCafe.gameMgr.getList().add(g);
	}
	
	private void changeGameInfo(String kwd, Game g) {
		if(kwd.equals("n"))
			return;
		while(true) {
			System.out.printf("변경하고 싶은 유형 입력 (이름, 종류, 난이도, 상태)\n"
								+ "변경하고 싶지 않을시 end입력\n>> ");
			kwd = scan.next();
			if(kwd.equals("end"))
				break;
			switch(kwd) {
			case "이름":{
				System.out.print(">> ");
				g.name = scan.next();
				System.out.println("변경 완료!");
				break;
			}
			case "종류":{
				System.out.print(">> ");
				g.genre = scan.next();
				System.out.println("변경 완료!");
				break;
			}
			case "난이도":{
				System.out.print(">> ");
				g.difficulty = scan.next();
				System.out.println("변경 완료!");
				break;
			}
			case "상태":{
				System.out.print(">> ");
				g.condition = scan.next();
				System.out.println("변경 완료!");
				break;
			}
			default:{
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요");
				break;
			}
			}
		}
	}
	// 회원 관리 기능
	public void userRegister() {
		int num = 0;
		while(true) {
			System.out.println("(1) 비밀번호 변경 (2) 회원 탈퇴 " +
								"그외. 종료");
			System.out.print(">> ");
			num = scan.nextInt();
			if(num < 1 || num > 2) return;
			switch(num) {
			case 1: changePwd(); break;
			case 2: withdrawMem(); break;
			default: break;
			}
		}
	}

	private void changePwd() {
		System.out.printf("변경을 원하는 회원의 아이디 입력\n>> ");				// 아이디는 전화번호입니다
		String id = scan.next();									
		Member m = (Member)BoardGameCafe.memberMgr.find(id);					
		if(m == null) {
			System.out.println("회원이 존재하지 않습니다");
			return;
		}
		System.out.println("변경하려는 회원이 맞습니까?");
		m.print();
		System.out.printf("y/n 입력\n>> ");
		String temp = scan.next();
		if(temp.equals("n"))
			return;
		System.out.printf("변경할 비밀번호 입력\n>> ");
		m.password = scan.next();
		System.out.println("변경 완료!");
	}
	
	private void withdrawMem() {
		System.out.printf("변경을 원하는 회원의 아이디 입력\n>> ");				// 아이디는 전화번호입니다
		String id = scan.next();									
		Member m = (Member)BoardGameCafe.memberMgr.find(id);					
		if(m == null) {
			System.out.println("회원이 존재하지 않습니다");
			return;
		}
		m.print();
		System.out.printf("탈퇴하시겠습니까? (y/n)\n>> ");
		String temp = scan.next();
		if(temp.equals("n")) {
			System.out.println("탈퇴 취소! 처음 화면으로 돌아갑니다");
			return;
		}
		BoardGameCafe.memberMgr.getList().remove(m);
		System.out.println("탈퇴 완료!");
	}
	
	private void checkRemainTime() {
		System.out.printf("이름 입력\n>> ");
		String tmp = scan.next();
		
		Member m = (Member)BoardGameCafe.memberMgr.find(tmp);
		NonMember n = 
				(NonMember)BoardGameCafe.nonMemberMgr.find(tmp);
		if(m == null && n == null) {
			System.out.println("존재하지 않는 회원입니다.");
			return;
		}
		if(m == null) {
			n.print();
			System.out.printf("이용시간을 변경하시겠습니까?\n>> ");
			tmp = scan.next();
			if(tmp.equals("y")) {
				System.out.printf("이용시간 입력\n>> ");
				int second = scan.nextInt();
				n.addTime(second);
				System.out.print("변경완료 : " 
							+ n.remainingTime + "분");
			}
		}
		if(n == null) {
			m.print();
			System.out.printf("이용시간을 변경하시겠습니까?\n>> ");
			tmp = scan.next();
			if(tmp.equals("y")) {
				System.out.printf("이용시간 입력\n>> ");
				int second = scan.nextInt();
				m.addTime(second);
				System.out.println("변경완료 : " 
							+ m.remainingTime + "분");
			}
		}
	}
	
	// 삭음료 관리 기능
	public void snackRegister() {
		int num = 0;
		while (true) {
			System.out.println("(1) 식음료메뉴 추가 (2) 식음료메뉴 삭제 (3) 식음료 메뉴 관리(수량,가격조정) " + "그외. 종료");
			System.out.print(">> ");
			num = scan.nextInt();
			if (num < 1 || num > 4)
				return;
			switch (num) {
			case 1:addSnack();break;
			case 2:deleteSnack();break;
			case 3:snackRegisterMenu();break;
			default:break;
			}
		}
	}

	private void addSnack() {
		Snack s = new Snack();
		System.out.println("추가할 메뉴 이름과 종류 가격 수량을 기입해주세요");
		s.name = scan.next();
		s.kind = scan.next();
		s.price = scan.nextInt();
		s.quantity = scan.nextInt();
		BoardGameCafe.snackMgr.getList().add(s);
	}

	private void deleteSnack() {
		String name;
		Snack s;
		while (true) {
			System.out.printf("삭제하고 싶은 식음료의 이름을 기입해주세요.(종료는 -1)\n>> ");
			name = scan.next();
			if (name.equals("-1"))
				break;
			if ((s = (Snack)BoardGameCafe.snackMgr.find(name))==null) {
				System.out.println("다시 입력해 주세요 -> 존재하지 않습니다.");
				continue;
			}
			BoardGameCafe.snackMgr.getList().remove(s);
		}
	}

	private void snackRegisterMenu() {
		String name;
		String kwd;
		Snack s;
		while (true) {
			System.out.printf("가격, 수량 변경 혹은 관리하고 싶은 식음료의 이름를 쓰시오. (종료는 -1)\n>> ");
			name = scan.next();
			if (name.equals("-1"))
				break;
			if ((s = (Snack)BoardGameCafe.snackMgr.find(name))==null) {
				System.out.println("다시 입력해 주세요 -> 존재하지 않습니다.");
				continue;
			}
			System.out.println("현재 선택된 식음료 정보");
			s.print();
			System.out.printf("현재 선택한 식음료 정보를 바꾸실건가요(y/n)\n>> ");
			kwd = scan.next();
			changeSnackInfo(kwd, s);
		}
	}	
	private void changeSnackInfo(String kwd, Snack s) {
		if (kwd.equals("n"))
			return;
		while (true) {
			System.out.printf("변경하고 싶은 유형 입력 (이름, 종류, 가격, 수량)\n" + "변경하고 싶지 않을시 end입력\n>> ");
			kwd = scan.next();
			if (kwd.equals("end"))
				break;
			switch (kwd) {
			case "이름": {
				System.out.print(">> ");
				s.name = scan.next();
				System.out.println("변경 완료!");
				break;
			}
			case "종류": {
				System.out.print(">> ");
				s.kind = scan.next();
				System.out.println("변경 완료!");
				break;
			}
			case "가격": {
				System.out.print(">> ");
				s.price = scan.nextInt();
				System.out.println("변경 완료!");
				break;
			}
			case "수량": {
				System.out.print(">> ");
				s.quantity = scan.nextInt();
				System.out.println("변경 완료!");
				break;
			}
			default: {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요");
				break;
			}
			}
		}
	}
	
	// 방(좌석) 관리 메뉴입니다
	public void roomRegister() {
		int num = 0;
		while (true) {
			System.out.println("(1) 예약 (2) 예약취소 " + "그외. 종료");
			System.out.print(">> ");
			num = scan.nextInt();
			if (num < 1 || num > 2)
				return;
			switch (num) {
			case 1:
				reserveRoom(scan);break;
			case 2:
				cancleRoom();break;
			default:break;
			}
		}
	}
	// 좌석 예약 기능
	private void reserveRoom(Scanner scan) {
		int roomNumber = 0; 
			while(true) {
				System.out.print("어느방을 예약하실 건가요?\n>> ");
				roomNumber = scan.nextInt();
				if(roomNumber==-1)
					return;
				if (roomNumber > BoardGameCafe.MAX_ROOM_NUMBER) {
					System.out.println("다시 입력해주세요");
					break;
				}
				Room r = (Room)BoardGameCafe.roomMgr.getList().get(roomNumber - 1);
				if(r.reserve == false && r.use == false) {
					r.reserve = true;
					System.out.println("선택 완료");
				}
				else if(r.use == true) {
					System.out.println("현재 사용중 -> 예약 불가능합니다");
				}
				else System.out.println("선택 불가");
			}
		}
	// 예약 취소 기능
	private void cancleRoom() {
		int roomNumber = 0;
		while(true) {
			System.out.print("예약을 취소할 방번호를 입력하세요\n>> ");
			roomNumber = scan.nextInt();
			if(roomNumber==-1)
				return;
			if (roomNumber > BoardGameCafe.MAX_ROOM_NUMBER) {
				System.out.println("다시 입력해주세요");
				break;
			}
			Room r = (Room)BoardGameCafe.roomMgr.getList().get(roomNumber - 1);
			if(r.reserve == true && r.use == false) {
				r.reserve = false;
				System.out.println("취소 완료");
			}
			else if(r.reserve == false) {
				System.out.println("현재 예약되어있지 않습니다.");
			}
			else {
				System.out.println("이미 사용중인 방입니다.");
			}
		}
	}
}


