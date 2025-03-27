package com.system.bankd.user.infrastructure;

import com.system.bankd.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MySqlAuthUser extends CrudRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    User getUser(String email);
}
