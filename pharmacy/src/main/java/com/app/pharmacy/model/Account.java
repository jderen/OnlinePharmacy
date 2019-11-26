package com.app.pharmacy.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private User user;

    public Account(Long id) {this.id = id;}
}
