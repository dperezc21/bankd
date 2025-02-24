package com.system.bankd.infrastructure.database;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.models.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MySqlUserAccount extends CrudRepository<Account, Long> {
    @Query("select c from Account c where c.user.userId = ?1 and c.accountType = ?2")
    Account getAccountByTypeAndUserId(Long userId, AccountType accountType);
}
