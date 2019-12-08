package com.app.pharmacy.controller;

import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.User;
import com.app.pharmacy.model.dao.ProductDao;
import com.app.pharmacy.model.dao.TransactionDao;
import com.app.pharmacy.model.dto.ProductDto;
import com.app.pharmacy.model.enums.Role;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/addSamples")
    public Map<String, String> addSampleProducts() {
        List<Product> products = createSampleProducts();
        if (!products.isEmpty()) {
            products.forEach(productDao::insert);
            return Collections.singletonMap("message", "samples added");
        } else {
            return Collections.singletonMap("message", "samples not added, Product entity in DB is empty");
        }

    }

    private List<Product> createSampleProducts() {

        Product p1 = new Product(null, 1L, new BigDecimal(10.99, MathContext.DECIMAL64), "Ibuprom", "lelum polelum", false, "image/kon.jpg",null);
        Product p2 = new Product(null, 2L, new BigDecimal(12.99, MathContext.DECIMAL64), "Apap", "lelum polelum", false, "image/kon.jpg",null);
        Product p3 = new Product(null, 3L, new BigDecimal(8.99, MathContext.DECIMAL64), "Groprinosin", "lelum polelum", false, "image/kon.jpg",null);
        Product p4 = new Product(null, 4L, new BigDecimal(21.99, MathContext.DECIMAL64), "Cerutin", "lelum polelum", false, "image/kon.jpg",null);
        Product p5 = new Product(null, 5L, new BigDecimal(20.99, MathContext.DECIMAL64), "Gripex", "lelum polelum", false, "image/kon.jpg",null);
        Product p6 = new Product(null, 6L, new BigDecimal(13.99, MathContext.DECIMAL64), "Clatra", "lelum polelum", true, "image/kon.jpg",null);

        return Arrays.asList(p1,p2,p3,p4,p5,p6);
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {

        var imgFile = new ClassPathResource(productDao.getImagePathByProductId(id));
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
