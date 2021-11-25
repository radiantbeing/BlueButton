package boardgamecafe;

import java.util.Scanner;

import mgr.Manageable;

public class Order implements Manageable{
	public String orderedMenu;
	public int orderedCount;
	public int totalPrice;
	
	public Order(String name, int count, int price) {
		this.orderedMenu = name;
		this.orderedCount = count;
		this.totalPrice = count * price;
	}

	@Override
	public void print() {
	}

	@Override
	public void read(Scanner scan) {
	}

	@Override
	public boolean matches(String wkd) {	
		return false;
	}
	
}
