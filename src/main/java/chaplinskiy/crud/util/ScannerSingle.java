package chaplinskiy.crud.util;


import java.util.Scanner;

public class ScannerSingle {
    private static Scanner scanner;

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
