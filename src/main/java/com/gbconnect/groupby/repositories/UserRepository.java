package com.gbconnect.groupby.repositories;

import com.gbconnect.groupby.domain.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    User findByUsername(String username);
}
