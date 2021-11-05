package mgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// 코드는 최대한 교재 6장의 Manager클래스 만들기와 유사하게 만들었습니다.
public class Manager {
    ArrayList<Manageable> mList = new ArrayList<>();

    public void readAll(String filename, Factory fac) {
        Scanner filein = openfile(filename);
        Manageable m = null;

        while (filein.hasNext()) {
            m = fac.create();
            m.read(filein);
            mList.add(m);
        }
    }

    public void printAll() {
        for (Manageable m: mList) {
            m.print();
        }
    }

    public void search(Scanner kwdScanner) {
        String kwd = null;
        while (true) {
            System.out.print(">> ");
            kwd = kwdScanner.next();
            if (kwd.equals("end"))
                break;
            for (Manageable m: mList) {
                if (m.matches(kwd))
                    m.print();
            }
        }
    }

    public Manageable find(String kwd) {
        for (Manageable m: mList) {
            if (m.matches(kwd))
                return m;
        }
        return null;
    }

    // 조건에 맞는 객체를 찾아서 리스트를 만든후 리스트를 반환하는 함수입니다.
    public ArrayList<Manageable> findAll(String kwd) {
        ArrayList<Manageable> result = new ArrayList<>();
        for (Manageable m: mList) {
            if (m.matches(kwd)) {
                result.add(m);
            }
        }
        return result;
    }

    Scanner openfile(String filename) {
        Scanner filein = null;
        try {
            filein = new Scanner(new File(filename));
        } catch (IOException e) {
            System.out.print(filename + "가 존재하지 않습니다.");
            System.exit(0);
        }
        return filein;
    }


}
