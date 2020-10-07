package chaplinskiy.crud.repository.impl.csv;

import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.model.AccountStatus;
import chaplinskiy.crud.repository.AccountRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static chaplinskiy.crud.util.Constants.accountRepositoryPathCsv;

public class AccountRepositoryCsvIO implements AccountRepository {

    public Account create(Account account) {
        List<Account> allAccount = getAll();

        if (allAccount.isEmpty()){
            account.setId(0L);
        } else {
            Account accountWithMaxId =
                    allAccount.stream().max(Comparator.comparing(Account::getId)).get();
            account.setId(accountWithMaxId.getId() + 1);
        }

        String acc = account.getId() + "," + account.getStatus() +"\n";

        try (Writer writerAccount = new FileWriter(accountRepositoryPathCsv, true)){
            writerAccount.write(acc);
        }  catch (IOException e){
            System.out.println("Exception IO");
        }

        return account;
    }

    public Account update(Account account) {
        List<Account> all = getAll();

        for (int i = 0; i < all.size(); i++) {
            Account currentAccount = all.get(i);
            if(currentAccount.getId().equals(account.getId())){
                currentAccount.setStatus(account.getStatus());
            }
        }
        StringBuilder accounts = new StringBuilder();

        Iterator<Account> iterator = all.iterator();

        while (iterator.hasNext()){
            Account nextAccount = iterator.next();
            accounts.append(nextAccount.getId() + "," + nextAccount.getStatus() + "\n");
        }

        String trim = accounts.toString();


        try (Writer writerAccount = new FileWriter(accountRepositoryPathCsv)){
            writerAccount.write(String.valueOf(trim));
        }  catch (IOException e){
            System.out.println("Exception IO");
        }

        return account;
    }

    public Account getById(Long id) {
        List<Account> all = getAll();

        all = all.stream().filter(a->{
            return a.getId().equals(id);
        }).collect(Collectors.toList());

        return all.get(0);
    }

    public List<Account> getAll() {
        List<String> rawAccounts = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        try {
            rawAccounts = Files.readAllLines(Paths.get(accountRepositoryPathCsv));
        } catch (IOException e) {
            e.printStackTrace();
        }

        rawAccounts.stream().forEach(a -> {
            String[] attributes = a.split(String.valueOf(','));
            accounts.add(makeAccount(attributes));
        });

        return accounts;
    }

    public void deleteById(Long id) {
        List<Account> all = getAll();

        for (int i = 0; i < all.size(); i++) {
            Account currentAccount = all.get(i);
            if(currentAccount.getId().equals(id)){
                all.remove(currentAccount);
            }
        }
        StringBuilder accounts = new StringBuilder();

        Iterator<Account> iterator = all.iterator();

        while (iterator.hasNext()){
            Account nextAccount = iterator.next();
            accounts.append(nextAccount.getId() + "," + nextAccount.getStatus() + "\n");
        }

        String trim = accounts.toString();


        try (Writer writerAccount = new FileWriter(accountRepositoryPathCsv)){
            writerAccount.write(String.valueOf(trim));
        }  catch (IOException e){
            System.out.println("Exception IO");
        }
    }


    private Account makeAccount(String[] attributes) {
       return new Account(Long.valueOf(attributes[0]), AccountStatus.valueOf(attributes[1]));
    }

}
