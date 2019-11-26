package com.app.pharmacy.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private Long number;
    private Long cost;
    private String name;
    private String description;
    private boolean prescriptionRequired;


   @ManyToMany(mappedBy = "products")
    private List<Transaction> transactions = new ArrayList<>();


}
