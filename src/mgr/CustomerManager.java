package mgr;

import boardgamecafe.Game;
import boardgamecafe.Snack;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager implements Manageable{
    String name;
    String phoneNumber;

    @Override
    public void read(Scanner scan) {
        name = scan.next();
        phoneNumber = scan.next();
    }

    @Override
    public void print() {
        System.out.printf("이름 : %s 전화번호 : %s\n", name, phoneNumber);
    }

    @Override
    public boolean matches(String kwd) {
        if(name.contains(kwd))
            return true;
        if(phoneNumber.contains(kwd))
            return true;
        return false;
    }
}
