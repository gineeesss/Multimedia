import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        int incremento = 0;
        do {
            switch (scr.nextLine().charAt(0)) {
                case '+':
                    System.out.println(++incremento);
                    break;
                case '-':
                    if (incremento - 1 > 0) {
                        System.out.println(--incremento);
                        break;
                    }
                    break;
            }
        } while (true);
    }
}