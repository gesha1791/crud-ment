package chaplinskiy.crud.view;

import java.util.Scanner;

import static chaplinskiy.crud.util.Constants.*;
import static chaplinskiy.crud.util.PrintUtils.printMessage;
import static chaplinskiy.crud.util.ScannerSingle.getScanner;

public class RunnerView {
    private static RunnerView view;

    private final AccountView accountView;
    private final SpecialtyView specialtyView;
    private final CustomerView customerView;
    private final Scanner scanner;


    private RunnerView() {
        scanner = getScanner();
        customerView = new CustomerView();
        accountView = new AccountView();
        specialtyView = new SpecialtyView();
    }

    public static RunnerView getInstance() {
        if (view == null) {
            view = new RunnerView();
        }
        return view;
    }

    public void run() {

        boolean active = true;
        while (active) {
            printMessage(runnerViewMessage);

            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    specialtyView.run();
                    break;
                case 2:
                    accountView.run();
                    break;
                case 3:
                    customerView.run();
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                default:
                    printMessage(wrongRunnerMessage);
            }
        }
    }
}
