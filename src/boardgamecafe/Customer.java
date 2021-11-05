package boardgamecafe;

import mgr.CustomerManager;
import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends CustomerManager {
	//서팔광 01017634965 3645 4154
	int passWord;
	int point;
	
	// 최대한 저희가 배운 부분과 유사하게 만들어보았습니다. 지금 만든 모든 객체들이 이와 유사할 것입니다.
	@Override
	public void read(Scanner scan) {
		super.read(scan);
		passWord = scan.nextInt();
		point = scan.nextInt();
	}

	@Override
	public void print() {
		printUserType();
		super.print();
	}
	
	@Override
	public boolean matches(String kwd) {
		if(super.matches((kwd))){
			return true;
		}
		return false;
	}

	public void printUserType() {
		System.out.print("[회원] ");
	}
}
