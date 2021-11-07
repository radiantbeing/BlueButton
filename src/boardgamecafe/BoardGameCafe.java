package boardgamecafe;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class BoardGameCafe extends Manager {
    Scanner scan = new Scanner(System.in);
    Manager gameMgr = new Manager();
    Manager snackMgr = new Manager();
    Manager nonMemberMgr = new Manager();
    Manager memberMgr = new Manager();

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

        nonMemberMgr.readAll("non_member.txt", new Factory() {
            public Manageable create() {
                return new NonMember();
            }
        });

        memberMgr.readAll("member.txt", new Factory() {
            public Manageable create() {
                return new Member();
            }
        });

        openMenu();
    }

    void openMenu() {
        int menuNum = 0;
        Loop1:while (true) {
            System.out.println("(1) 게임 출력 (2) 회원 출력 (3) 비회원 출력 " +
                    "(4) 간식 출력 (기타) 종료");
            System.out.print(">> ");
            menuNum = scan.nextInt();
            switch (menuNum) {
                case 1: gameMgr.printAll(); break;
                case 2: memberMgr.printAll(); break;
                case 3: nonMemberMgr.printAll(); break;
                case 4: snackMgr.printAll(); break;
                default: break Loop1;
            }
        }
    }

    public static void main(String[] args) {
        BoardGameCafe bgc = new BoardGameCafe();
        bgc.run();
    }
}