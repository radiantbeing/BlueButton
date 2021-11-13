package boardgamecafe;

import java.util.Scanner;

import mgr.Manageable;

public class Admin implements Manageable{
	String id;
	String pwd;
	@Override
	public void print() {
		System.out.printf("ID: %s/nPassword: %s",id,pwd);
	}
	@Override
	public void read(Scanner scan) {
		id = scan.next();
		pwd = scan.next();
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(pwd.equals(kwd))
			return true;	
		return false;
	}
}
