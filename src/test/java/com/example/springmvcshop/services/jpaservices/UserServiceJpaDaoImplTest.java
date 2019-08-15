package com.example.springmvcshop.services.jpaservices;

import com.example.springmvcshop.config.JpaIntegrationConfig;
import com.example.springmvcshop.domain.*;
import com.example.springmvcshop.services.ProductService;
import com.example.springmvcshop.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserService userService;

    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
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

    @Test
    public void testAddCartToUser() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("userPassword");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
    }

    @Test
    public void testAddCartWithCartDetails() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("userPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartDetail1 = new CartDetail();
        cartDetail1.setProduct(storedProducts.get(0));
        user.getCart().addCartDetails(cartDetail1);

        CartDetail cartDetail2 = new CartDetail();
        cartDetail2.setProduct(storedProducts.get(1));
        user.getCart().addCartDetails(cartDetail2);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
        assert savedUser.getCart().getCartDetails().size() == 2;
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("userPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartDetail1 = new CartDetail();
        cartDetail1.setProduct(storedProducts.get(0));
        user.getCart().addCartDetails(cartDetail1);

        CartDetail cartDetail2 = new CartDetail();
        cartDetail2.setProduct(storedProducts.get(1));
        user.getCart().addCartDetails(cartDetail2);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getCart().getCartDetails().size() == 2;

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

        userService.saveOrUpdate(savedUser);

        assert savedUser.getCart().getCartDetails().size() == 1;
    }
}