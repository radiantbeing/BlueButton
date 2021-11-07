package boardgamecafe;

import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

public class NonMember implements Manageable {
    // 이름 전화번호
    // 김성철 01055789968
    String name;
    String phoneNumber;
    ArrayList<Snack> snacksList = new ArrayList<>();//임시 snackList

    @Override
    public void read(Scanner scan) {
        name = scan.next();
        phoneNumber = scan.next();
    }

    @Override
    public void print() {
        printUserType();
        System.out.printf("%s 전화번호: %s\n", name, phoneNumber);
    }

    public void printUserType() {
        System.out.print("[비회원] ");
    }

    @Override
    public boolean matches(String kwd) {
        if (name.contains(kwd))
            return true;
        if (("" + phoneNumber).contains(kwd))
            return true;
        return false;
    }

    public void addSnackList(Snack snack){
        snacksList.add(snack);
    }
}
