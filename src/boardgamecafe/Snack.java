package boardgamecafe;

import mgr.Manageable;

import java.util.Scanner;


public class Snack implements Manageable {
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
