package com.app.pharmacy.model;

import com.app.pharmacy.model.enums.Role;
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
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountId", unique = true)
    private Account account;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Prescription> prescriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<>();

}
