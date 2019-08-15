package com.example.springmvcshop.services.jpaservices;

import com.example.springmvcshop.config.JpaIntegrationConfig;
import com.example.springmvcshop.domain.Customer;
import com.example.springmvcshop.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"})
public class CustomerServiceJpaSaoImplTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void listAll() {
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assert customers.size() == 3;
    }

    @Test
    public void getById() {
        Integer id = 6;
        Customer customer = customerService.getById(id);

        assert customer.getFirstName() == "Micheal";
    }

    @Test
    public void saveOrUpdate() {
        String firstName = "First";
        String lastName = "Last";
        String addressLine1 = "address line 1";
        String addressLine2 = "address line 2";
        String city = "Big";
        String state = "Very big";
        String zipCode = "11101";
        String email = "test@test.com";
        String phoneNumber = "301.302.0101";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipCode(zipCode);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        Customer returnedCustomer = customerService.saveOrUpdate(customer);

        assert returnedCustomer.getFirstName() == firstName;
        assert returnedCustomer.getLastName() == lastName;
        assert returnedCustomer.getAddressLine1() == addressLine1;
        assert returnedCustomer.getAddressLine2() == addressLine2;
        assert returnedCustomer.getCity() == city;
        assert returnedCustomer.getState() == state;
        assert returnedCustomer.getZipCode() == zipCode;
        assert returnedCustomer.getEmail() == email;
        assert returnedCustomer.getPhoneNumber() == phoneNumber;
    }

    @Test
    public void delete() {
    }
}