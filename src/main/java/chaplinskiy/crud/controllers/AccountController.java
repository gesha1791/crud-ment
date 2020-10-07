package chaplinskiy.crud.controllers;

import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.repository.AccountRepository;
import chaplinskiy.crud.repository.impl.csv.AccountRepositoryCsvIO;

import java.util.List;

public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController() {
        this.accountRepository = new AccountRepositoryCsvIO();;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    public Account createAccount(Account account) {
        return accountRepository.create(account);
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public void updateAccountById(Account account) {
        accountRepository.update(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.getById(id);
    }
}
