package com.system.bankd.domain.models;

import com.system.bankd.domain.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "amount")
    private Double accountAmount;

    @Column(name = "account_type")
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
