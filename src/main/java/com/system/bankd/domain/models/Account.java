package com.system.bankd.domain.models;

import com.system.bankd.domain.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "account_user")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
