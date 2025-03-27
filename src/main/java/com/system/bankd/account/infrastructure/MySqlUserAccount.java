package com.system.bankd.account.infrastructure;

import com.system.bankd.account.domain.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MySqlUserAccount extends CrudRepository<Account, Long> {
    @Query("select c from Account c where c.user.userId = ?1 and c.accountType = ?2")
    Account getAccountByTypeAndUserId(Long userId, String accountType);
}
