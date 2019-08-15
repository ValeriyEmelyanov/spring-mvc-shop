package com.example.springmvcshop.services;

import com.example.springmvcshop.config.JpaIntegrationConfig;
import com.example.springmvcshop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"})
public class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListAll() {
        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 5;
    }

    @Test
    public void testGetById() {
        Integer id = 1;
        String description = "Product 1";

        Product product = productService.getById(id);

        assert product.getDescription() == description;
    }

    @Test
    public void testSaveOrUpdate() {
        String description = "Product 6";
        BigDecimal price = new BigDecimal("10.99");
        String imageUrl = "http://example.com/product6";

        Product product = new Product();
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        Product returndProduct = productService.saveOrUpdate(product);

        assert returndProduct.getDescription() == description;
        assert returndProduct.getPrice() == price;
        assert returndProduct.getImageUrl() == imageUrl;
    }

    @Test
    public void testDelete() {
        productService.delete(1);

        assert productService.listAll().size() == 4;
    }
}