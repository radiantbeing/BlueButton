package boardgamecafe;

import java.util.Scanner;

public class Member extends NonMember {
    // 이름 전화번호 비밀번호 포인트(결제 금액의 5%)
    // 서팔광 01017634965 3645 4154
    String password;
    int point;

    @Override
    public void read(Scanner scan) {
        super.read(scan);
        password = scan.next();
        point = scan.nextInt();
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("\t비밀번호:%s 포인트:%d\n", password, point);
    }

    @Override
    public boolean matches(String kwd) {
        if (super.matches((kwd))) {
            return true;
        }
        if (password.equals(kwd))
            return true;
        return false;
    }

    public void printUserType() {
        System.out.print("[회원] ");
    }
}
