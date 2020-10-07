package chaplinskiy.crud.repository.impl.csv;

import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.model.Customer;
import chaplinskiy.crud.model.Specialty;
import chaplinskiy.crud.repository.AccountRepository;
import chaplinskiy.crud.repository.CustomerRepository;
import chaplinskiy.crud.repository.SpecialtyRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static chaplinskiy.crud.util.Constants.*;

public class CustomerRepositoryCsvIO implements CustomerRepository {
    private final SpecialtyRepository specialtyRepository;
    private final AccountRepository accountRepository;

    public CustomerRepositoryCsvIO() {
        specialtyRepository = new SpecialtyRepositoryCsvIO();
        accountRepository = new AccountRepositoryCsvIO();
    }

    @Override
    public Customer create(Customer customer) {

        List<Customer> allCustomer = getAll();

        if (null == allCustomer || allCustomer.isEmpty()){
            customer.setId(0L);
        } else {
            Customer customertWithMaxId =
                    allCustomer.stream().max(Comparator.comparing(Customer::getId)).get();
            customer.setId(customertWithMaxId.getId() + 1);
        }

        StringBuilder customerString = customerConvertToString(customer);

        try (FileWriter  writerCustomer = new FileWriter(customerRepositoryPathCsv, true)){
            writerCustomer.write(String.valueOf(customerString));
            writerCustomer.flush();
        }  catch (IOException e){
            System.out.println("Exception IO");
        }
        return customer;
    }

    private StringBuilder customerConvertToString(Customer customer) {
        StringBuilder stringCustomer = new StringBuilder();
        stringCustomer.append(customer.getId());
        stringCustomer.append(",");
        stringCustomer.append(customer.getName());
        stringCustomer.append(",");
        stringCustomer.append(customer.getSurname());
        stringCustomer.append(",");

        Set<Specialty> specialties = customer.getSpecialties();
        stringCustomer.append("specialties:[");
        for(Specialty s : specialties){
            stringCustomer.append(s.getId());
            stringCustomer.append(",");
        }
        stringCustomer.deleteCharAt(stringCustomer.length()-1);
        stringCustomer.append("],");
        stringCustomer.append("account:");
        stringCustomer.append(customer.getAccount().getId());
        stringCustomer.append("\n");
        return stringCustomer;
    }

    @Override
    public Customer update(Customer customer) {
        List<Customer> allCustomers = getAll();

        allCustomers = allCustomers.stream().filter(a -> {
            return !a.getId().equals(customer.getId());
        }).collect(Collectors.toList());

        allCustomers.add(customer);

        allCustomers = allCustomers.stream().sorted(Comparator.comparing(Customer::getId)).collect(Collectors.toList());

        StringBuilder customers = new StringBuilder();

        for(Customer c : allCustomers){
            customers.append(customerConvertToString(c));
        }

        try (FileWriter  writerCustomer = new FileWriter(customerRepositoryPathCsv, false)){
            writerCustomer.write(String.valueOf(customers));
            writerCustomer.flush();
        }  catch (IOException e){
            System.out.println("Exception IO");
        }


        return customer;
    }

    @Override
    public Customer getById(Long id) {
        List<Customer> allCustomers = getAll();
        allCustomers = allCustomers.stream().filter(a -> {
            return a.getId().equals(id);
        }).collect(Collectors.toList());

        return allCustomers.get(0);
    }

    @Override
    public List<Customer> getAll() {
        List<String> rawCustomers = new ArrayList<>();

        try {
            rawCustomers = Files.readAllLines(Paths.get(customerRepositoryPathCsv));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertCustmoersFromStringToJavaBean(rawCustomers);
    }

    private List<Customer> convertCustmoersFromStringToJavaBean(List<String> rawCustomers) {
        List<Customer> customers = new ArrayList<>();
        for(String c : rawCustomers){
            String[] split = c.split(",");
            Long id = Long.valueOf(split[0].trim());
            String name = split[1].trim();
            String surname = split[2].trim();

            String specialty = split[3];
            int start = specialty.indexOf("[") + 1;
            int end = specialty.indexOf("]");
            String[] specialties = specialty.substring(start, end).split(" ");

            Set <Specialty> customerSpecialties = new HashSet<>();
            for (int i = 0; i < specialties.length; i++) {
                customerSpecialties.add(specialtyRepository.getById(Long.valueOf(specialties[i].trim())));
            }

            Long idAccount = Long.valueOf(split[4].substring(split[4].indexOf(":")+1, split[4].length()).trim());
            Account byId = accountRepository.getById(idAccount);

            Customer customer = new Customer(id, name, surname, customerSpecialties, byId);

            customers.add(customer);
        }
        return customers;
    }

    @Override
    public void deleteById(Long id) {
        List<Customer> allCustomers = getAll();

        allCustomers = allCustomers.stream().filter(a -> {
            return !a.getId().equals(id);
        }).collect(Collectors.toList());

        allCustomers = allCustomers.stream().sorted(Comparator.comparing(Customer::getId)).collect(Collectors.toList());

        StringBuilder customers = new StringBuilder();

        for(Customer c : allCustomers){
            customers.append(customerConvertToString(c));
        }

        try (FileWriter  writerCustomer = new FileWriter(customerRepositoryPathCsv, false)){
            writerCustomer.write(String.valueOf(customers));
            writerCustomer.flush();
        }  catch (IOException e){
            System.out.println("Exception IO");
        }


    }
}
