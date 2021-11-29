package boardgamecafe;

import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

public class NonMember implements Manageable {
    // 김성철 01055789968
    String name;
    String phoneNumber;
    // 게임 방은 1, 2, 3, ..., 9, 10의 숫자로 구분
    int roomNumber;
    int remainingTime;
    Game playingGame;
    Timer timer;
    public ArrayList<Order> orderList = new ArrayList<>();

    @Override
    public void read(Scanner scan) {
        name = scan.next();
        phoneNumber = scan.next();
    }

    void addOrder(Order order){
        orderList.add(order);
    }

    // 파일을 추가하면서 수동으로 입력을 해야하는 경우가 생겨서 read를 오버로딩
    public void read(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void print() {
        printUserType();
        System.out.printf("%s |전화번호: %s\n", name, phoneNumber);
        printRemainingTime();
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

    void setRoomNumber(int num) {
        roomNumber = num;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    // 이용 시간을 초 단위로 추가합니다.
    // GUI의 요금제 선택 단계에서 사용하는 메소드
    public void addTime(int seconds) {
        remainingTime += seconds;
    }

    // 잔여 시간을 계산하기 위한 Timer를 시작합니다.
    // GUI의 결제 단계에서 사용하는 메소드
    public void startTimer() {
        timer = new Timer();
        timer.start();
    }

    public void printRemainingTime() {
        System.out.printf("\t잔여시간: %d\n", remainingTime);
    }

    //남은시간을 얻을때 쓰는 메소드
    public int getRemainingTime() {
        return remainingTime;
    }

    public void setPlayingGame(Game game) {
        playingGame = game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String num) {
        phoneNumber = num;
    }

    class Timer extends Thread {
        public void run() {
            while (true) {
                try {
                    sleep(1000);
                    remainingTime--;
                    if (remainingTime <= 0) {
                        remainingTime = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

