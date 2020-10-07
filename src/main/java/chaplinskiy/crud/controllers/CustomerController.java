package chaplinskiy.crud.controllers;

import chaplinskiy.crud.model.Customer;
import chaplinskiy.crud.repository.AccountRepository;
import chaplinskiy.crud.repository.CustomerRepository;
import chaplinskiy.crud.repository.impl.csv.CustomerRepositoryCsvIO;

import java.util.List;

public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController() {
        customerRepository = new CustomerRepositoryCsvIO();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.create(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getById(id);
    }

    public Customer updateCustomer(Customer customerById) {
        return customerRepository.update(customerById);
    }

    public void removeCounterById(Long id) {
        customerRepository.deleteById(id);
    }
}
