package boardgamecafe;

import mgr.Manageable;

import java.util.Scanner;


public class Game implements Manageable {
    // 관리번호 게임이름 위치 장르 난이도 관리상태
    // 189 고스트헌터 G9 주사위협력 중 중

    /*
    관리 번호의 경우 10*30 사이즈의 보관함을 상상했습니다.
    10개의 행은 알파벳으로, 30개의 열은 0 이상의 자연수로 표현합니다.
    현재 상태는 아래와 같습니다.
    (A0, A1, ..., A28, A29)
    (B0, B1, ..., B28, B29)
    ...
    (I0, I1, ..., I28, I29)
    (J0, J1, ..., J28, J29)
    따라서 게임 1개가 추가된다면 K0부터 보관되도록 구현하면 됩니다.
    K행에 30개의 게임이 보관되면, 그 다음에 추가되는 게임은 L0 부터 보관되는 식입니다.
     */

    public int code;
    public String name;
    public String location;
    public String genre;
    public String difficulty;
    public String condition;

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
        if (name.contains(kwd))
            return true;
        if (kwd.length() < 4 && code == Integer.parseInt(kwd))
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
