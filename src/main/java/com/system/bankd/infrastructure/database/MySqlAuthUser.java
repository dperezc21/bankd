package com.system.bankd.infrastructure.database;

import com.system.bankd.domain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MySqlAuthUser extends CrudRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    User getUser(String email);
}
