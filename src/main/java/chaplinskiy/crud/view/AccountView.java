package chaplinskiy.crud.view;

import chaplinskiy.crud.controllers.AccountController;
import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.model.AccountStatus;

import java.util.List;
import java.util.Scanner;

import static chaplinskiy.crud.util.Constants.*;
import static chaplinskiy.crud.util.PrintUtils.printMessage;
import static chaplinskiy.crud.util.ScannerSingle.getScanner;

public class AccountView {
    private final Scanner scanner;
    private final AccountController accountController;


    public AccountView() {
        scanner = getScanner();
        accountController = new AccountController();
    }

    public void run() {
        boolean start = true;
        while (start) {
            printMessage(accountViewMessage);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    printAllAccounts();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    updateAccount();
                    break;
                case 5:
                    getByIdAccount();
                    break;
                case 6:
                    start = false;
                    break;
                default:
                    printMessage(wrongAccountMessage);
            }
    }
}

    private void getByIdAccount() {
        printMessage(idAccountMessage);
        Long id = Long.valueOf(scanner.next());
        Account accountById = accountController.getAccountById(id);
        printMessage(accountById.toString());
    }

    private void deleteAccount() {
        printMessage(idAccountMessage);
        Long id = Long.valueOf(scanner.next());
        accountController.deleteAccountById(id);
    }

    private void updateAccount() {
        printMessage(idAccountMessage);
        Long id = Long.valueOf(scanner.next());

        printMessage(updateAccountMessage);
        printMessage(accountStatusEnumValue);
        AccountStatus status = AccountStatus.valueOf(scanner.next());
        Account account = new Account(id, status);
        accountController.updateAccountById(account);
    }

    private void createAccount() {
        printMessage(createNewAccountMessage);
        Account account = new Account(AccountStatus.ACTIVE);
        Account newAccount = accountController.createAccount(account);
        printMessage(newAccount.toString());
    }


    private void printAllAccounts() {
        List<Account> allAccounts = accountController.getAllAccounts();
        printMessage(allAccountsMessage);

        allAccounts.stream().forEach(System.out::println);
    }

}
