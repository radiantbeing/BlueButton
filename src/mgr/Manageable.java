package mgr;

import java.util.Scanner;

public interface Manageable {
    public void print();
    public void read(Scanner scan);
    public boolean matches(String wkd);
}
