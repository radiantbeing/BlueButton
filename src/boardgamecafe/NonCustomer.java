package boardgamecafe;

import java.util.Scanner;

public class NonCustomer extends Customer {

	@Override
	public void read(Scanner scan) {
		super.read(scan);
	}
	
	@Override
	public void print() {
		printUserType();
		super.print();
	}
	
	public void printUserType() {
=		System.out.print("[비회원] ");
	}
	
	@Override
	public boolean matches(String kwd) {
		if(super.matches((kwd))){
			return true;
		}
		return false;
	}
}
