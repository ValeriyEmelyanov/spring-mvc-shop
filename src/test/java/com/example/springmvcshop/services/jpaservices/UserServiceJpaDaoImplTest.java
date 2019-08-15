package com.example.springmvcshop.services.jpaservices;

import com.example.springmvcshop.config.JpaIntegrationConfig;
import com.example.springmvcshop.domain.Customer;
import com.example.springmvcshop.domain.User;
import com.example.springmvcshop.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void saveOfUser() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("userPassword");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }

    @Test
    public void saveOfUserWithCustomer() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("userPassword");

        Customer customer = new Customer();
        customer.setFirstName("First");
        customer.setLastName("Last");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;

    }
}