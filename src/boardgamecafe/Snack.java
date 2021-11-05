package boardgamecafe;

import mgr.Manageable;

import java.util.Scanner;


public class Snack implements Manageable {
	//에스프레소 커피 3800 20
	String name;
	String kinds;
	int price;
	int quantity;

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		kinds = scan.next();
		price = scan.nextInt();
		quantity = scan.nextInt();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("%s %s %d 수량: %d\n",
				name, kinds, price,quantity);
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if (name.contains(kwd))
			return true;
		if (kinds.equals(kwd))
			return true;
		if (kwd.equals(""+quantity))
			return true;
		return false;
	}
}
