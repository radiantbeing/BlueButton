package boardgamecafe;

import mgr.Manageable;

import java.util.Scanner;


public class Snack implements Manageable {
    // 에스프레소 커피 3800 20
    String name;
    String kind;
    int price;
    int quantity;

    @Override
    public void read(Scanner scan) {
        name = scan.next();
        kind = scan.next();
        price = scan.nextInt();
        quantity = scan.nextInt();
    }

    @Override
    public void print() {
        System.out.printf("이름: %s 종류: %s 가격: %d 수량: %d\n",
                name, kind, price, quantity);
    }

    @Override
    public boolean matches(String kwd) {
        if (name.contains(kwd))
            return true;
        if (kind.equals(kwd))
            return true;
        return false;
    }
}
