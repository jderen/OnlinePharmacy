package com.app.pharmacy.model.dto;

import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.User;
import com.app.pharmacy.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private Long number;
    private BigDecimal cost;
    private String name;
    private String description;
    private Boolean prescriptionRequired;
    private String image;

    public static Product getProductByProductDto(ProductDto productDto){
        return Product.builder()
                .id(productDto.id)
                .number(productDto.number)
                .cost(productDto.cost)
                .name(productDto.name)
                .description(productDto.description)
                .prescriptionRequired(productDto.prescriptionRequired)
                .image(productDto.image)
                .transactions(new ArrayList<>())
                .build();
    }

    public static ProductDto getProductDtoByProduct(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .number(product.getNumber())
                .cost(product.getCost())
                .name(product.getName())
                .description(product.getDescription())
                .prescriptionRequired(product.isPrescriptionRequired())
                .image(product.getImage())
                .build();
    }
}