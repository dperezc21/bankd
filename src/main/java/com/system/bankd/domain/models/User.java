package com.system.bankd.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_phone", nullable = false)
    private String userPhone;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Account> accountList;

}
