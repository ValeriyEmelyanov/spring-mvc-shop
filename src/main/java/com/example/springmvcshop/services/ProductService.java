package com.example.springmvcshop.services;

import com.example.springmvcshop.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveOrUpdateProduct(Product product);
}
