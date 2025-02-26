package com.system.bankd.domain.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MovementResponse {
    private Long id;
    private String name;
    private String description;
    private Date createdDate;
    private Double amount;
}
