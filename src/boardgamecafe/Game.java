package boardgamecafe;

import mgr.Manageable;

import java.util.Scanner;


public class Game implements Manageable {
    // 관리번호 게임이름 위치 장르 난이도 관리상태
    // 189 고스트헌터 G9 주사위협력 중 중
    int code;
    String name;
    String location;
    String genre;
    String difficulty;
    String condition;

    @Override
    public void read(Scanner scan) {
        code = scan.nextInt();
        name = scan.next();
        location = scan.next();
        genre = scan.next();
        difficulty = scan.next();
        condition = scan.next();
    }

    @Override
    public void print() {
        System.out.printf("[%2d] 이름: %s / 위치: %s / 종류: %s / 난이도: %s / " +
                "상태: %s\n", code, name, location, genre, difficulty, condition);
    }

    @Override
    public boolean matches(String kwd) {
        if (code == Integer.parseInt(kwd))
            return true;
        if (name.contains(kwd))
            return true;
        if (location.equals(kwd))
            return true;
        if (genre.contains(kwd))
            return true;
        if (condition.equals(kwd))
            return true;
        return false;
    }
}
