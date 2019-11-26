package com.app.pharmacy.model;

import com.app.pharmacy.model.enums.Status;
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
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    private Long number;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "transaction")
    private List<Prescription> prescriptions = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "transaction_product",
    joinColumns = @JoinColumn(name = "transaction_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
}
