package com.system.bankd.movement.domain;

import com.system.bankd.account.domain.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Movement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementId;

    @Column(name = "movement_name", nullable = false)
    private String movementName;

    @Column(name = "movement_description", nullable = false)
    private String description;

    @Column(name = "movement_amount")
    private Double amount;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Movement() { this.createdAt = new Date(); }
}
