package com.app.pharmacy.controller;

import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.User;
import com.app.pharmacy.model.dao.ProductDao;
import com.app.pharmacy.model.dao.TransactionDao;
import com.app.pharmacy.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/all")
    public List<ProductDto> findAll() {
        List<Product> products = productDao.findAll();
        return products
                .stream()
                .map(ProductDto::getProductDtoByProduct)
                .collect(Collectors.toList());
    }


}
