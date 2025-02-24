package com.system.bankd.infrastructure.database;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.models.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MySqlAccountUser extends CrudRepository<Account, Long> {
    @Query("select c from Account c where c.accountType = ?1 and c.user.userId = ?2")
    Account getAccountByTypeAndUserId(Long userId, AccountType accountType);
}
