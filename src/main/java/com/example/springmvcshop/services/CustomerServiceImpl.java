package com.example.springmvcshop.services;

import com.example.springmvcshop.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        loadCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextKey());
            }
            customers.put(customer.getId(), customer);

            return customer;
        } else {
            throw new RuntimeException("Customer can`t be null!");
        }
    }

    private Integer getNextKey() {
        if (customers.isEmpty()) {
            return 1;
        }
        return Collections.max(customers.keySet()) + 1;
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    private void loadCustomers() {
        customers = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Ivan");
        customer1.setLastName("Petrov");
        customer1.setEmail("IvanPetrov@test.com");
        customer1.setPhoneNumber("123-123");
        customer1.setAddressLine1("...");
        customer1.setAddressLine2("...");
        customer1.setCity("Newyork");
        customer1.setState("??");
        customer1.setZipCode("00010001");

        customers.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Vlad");
        customer2.setLastName("Kukushkin");
        customer2.setEmail("VladKukushkin@test.com");
        customer2.setPhoneNumber("611-777");
        customer2.setAddressLine1("...");
        customer2.setAddressLine2("...");
        customer2.setCity("Newyork");
        customer2.setState("??");
        customer2.setZipCode("00010001");

        customers.put(2, customer2);
    }
}
