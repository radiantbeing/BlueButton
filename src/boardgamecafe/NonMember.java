package boardgamecafe;

import mgr.Manageable;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class NonMember implements Manageable {
    // 김성철 01055789968
    String name;
    String phoneNumber;
    // 게임 방은 1, 2, 3, ..., 9, 10의 숫자로 구분
    int roomNumber;
    int remainingTime;
    Timer timer;
    @Override
    public void read(Scanner scan) {
        name = scan.next();
        phoneNumber = scan.next();
        remainingTime = (int)(Math.random()*255);//remain정해짐 일단 랜덤으로
        timer = new Timer(remainingTime);
        timer.start();
    }

    @Override
    public void print() {
        printUserType();
        System.out.printf("%s 전화번호: %s\n", name, phoneNumber);
        timer.printRemainingTime();
    }

    public void printUserType() {
        System.out.print("[비회원] ");
    }

    int getRemainingTime(){
       return remainingTime;
    }

    @Override
    public boolean matches(String kwd) {
        if (name.contains(kwd))
            return true;
        if (("" + phoneNumber).contains(kwd))
            return true;
        return false;
    }

    void setRemainingTime(int remainingTime){
        this.remainingTime = remainingTime;
    }

    void setRoomNumber(int num) {
        roomNumber = num;
    }
}

class Timer extends Thread{
    int remainingTime;
    public Timer(int remainingTime){
        this.remainingTime = remainingTime;
    }
    public void printRemainingTime(){
        System.out.println("잔여시간:"+remainingTime);
    }
    public void run() {
        while(true) {
            try {
                sleep(1000);
                remainingTime--;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}