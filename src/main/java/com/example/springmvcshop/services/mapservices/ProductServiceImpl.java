package com.example.springmvcshop.services.mapservices;

import com.example.springmvcshop.domain.DomainObject;
import com.example.springmvcshop.domain.Product;
import com.example.springmvcshop.services.ProductService;
import com.example.springmvcshop.services.mapservices.AbstractMapService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    public ProductServiceImpl() {
        super();
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return (Product) super.saveOrUpdate(product);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
