package chaplinskiy.crud.view;

import chaplinskiy.crud.controllers.CustomerController;
import chaplinskiy.crud.controllers.SpecialtyController;
import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.model.AccountStatus;
import chaplinskiy.crud.model.Customer;
import chaplinskiy.crud.model.Specialty;
import chaplinskiy.crud.repository.AccountRepository;
import chaplinskiy.crud.repository.CustomerRepository;
import chaplinskiy.crud.repository.SpecialtyRepository;
import chaplinskiy.crud.repository.impl.csv.AccountRepositoryCsvIO;
import chaplinskiy.crud.repository.impl.csv.CustomerRepositoryCsvIO;
import chaplinskiy.crud.repository.impl.csv.SpecialtyRepositoryCsvIO;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static chaplinskiy.crud.util.Constants.*;
import static chaplinskiy.crud.util.PrintUtils.printMessage;
import static chaplinskiy.crud.util.ScannerSingle.getScanner;

public class CustomerView {
    private final Scanner scanner;
    private final CustomerController customerController;
    private final AccountRepository accountRepository;
    private final SpecialtyRepository specialtyRepository;

    public CustomerView() {
        scanner = getScanner();
        customerController = new CustomerController();
        accountRepository = new AccountRepositoryCsvIO();
        specialtyRepository = new SpecialtyRepositoryCsvIO();
    }

    public void run() {
        boolean start = true;
        while (start) {
            printMessage(customerViewMessage);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    printAllCustomers();
                    break;
                case 2:
                    createCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    getByIdCustomer();
                    break;
                case 6:
                    start = false;
                    break;
                default:
                    printMessage(wrongCustomerMessage);
            }
        }
    }

    private void getByIdCustomer() {
        printMessage(idCustomerMessage);
        List<Customer> allCustomers = customerController.getAllCustomers();
        printMessage(allCustomerMessage);

        allCustomers.stream().forEach(System.out::println);

        Long id = Long.valueOf(scanner.next());

        Customer customer = customerController.getCustomerById(id);

        printMessage(customer.toString());
    }

    private void updateCustomer() {
        printMessage(idCustomerMessage);

        List<Customer> allCustomers = customerController.getAllCustomers();
        printMessage(allCustomerMessage);

        allCustomers.stream().forEach(System.out::println);

        Long id = Long.valueOf(scanner.next());

        Customer customerById = customerController.getCustomerById(id);

        printMessage(enterCustomerNameMessage);
        String name = scanner.next();
        customerById.setName(name);
        printMessage(enterCustomerSurnameMessage);
        String surname = scanner.next();
        customerById.setSurname(surname);
        printMessage(enterCustomerAccountMessage);
        List<Account> allAccount = accountRepository.getAll();
        allAccount.stream().forEach(System.out::println);
        Long idAcc = Long.valueOf(scanner.next());
        Account account = accountRepository.getById(idAcc);
        customerById.setAccount(account);

        printMessage(enterCustomerSpecialtyMessage);
        List<Specialty> allSpecialties = specialtyRepository.getAll();
        allSpecialties.stream().forEach(System.out::println);
        String ids = scanner.next();

        String[] split = ids.split(",");

        Set<Specialty> specialtyCustomer = new HashSet<>();

        for (int i = 0; i < split.length; i++) {
            Specialty currentSpec = specialtyRepository.getById(Long.valueOf(split[i].trim()));
            specialtyCustomer.add(currentSpec);
        }

        customerById.setSpecialties(specialtyCustomer);

        Customer customerUpdate = customerController.updateCustomer(customerById);

        printMessage(customerUpdate.toString());
    }

    private void deleteCustomer() {
        printMessage(idCustomerMessage);

        List<Customer> allCustomers = customerController.getAllCustomers();
        printMessage(allCustomerMessage);

        allCustomers.stream().forEach(System.out::println);

        Long id = Long.valueOf(scanner.next());

        customerController.removeCounterById(id);

    }

    private void createCustomer() {
        printMessage(createNewCustomerMessage);
        Customer customer = new Customer();
        printMessage(enterCustomerNameMessage);
        String name = scanner.next();
        customer.setName(name);
        printMessage(enterCustomerSurnameMessage);
        String surname = scanner.next();
        customer.setSurname(surname);
        printMessage(enterCustomerAccountMessage);
        List<Account> allAccount = accountRepository.getAll();
        allAccount.stream().forEach(System.out::println);
        Long id = Long.valueOf(scanner.next());
        Account account = accountRepository.getById(id);
        customer.setAccount(account);

        printMessage(enterCustomerSpecialtyMessage);
        List<Specialty> allSpecialties = specialtyRepository.getAll();
        allSpecialties.stream().forEach(System.out::println);
        String ids = scanner.next();

        String[] split = ids.split(",");

        Set<Specialty> specialtyCustomer = new HashSet<>();

        for (int i = 0; i < split.length; i++) {
            Specialty currentSpec = specialtyRepository.getById(Long.valueOf(split[i].trim()));
            specialtyCustomer.add(currentSpec);
        }

        customer.setSpecialties(specialtyCustomer);

        Customer newCustomer = customerController.createCustomer(customer);
        printMessage(newCustomer.toString());

    }

    private void printAllCustomers() {
        List<Customer> allCustomers = customerController.getAllCustomers();
        printMessage(allCustomerMessage);

        allCustomers.stream().forEach(System.out::println);
    }
}