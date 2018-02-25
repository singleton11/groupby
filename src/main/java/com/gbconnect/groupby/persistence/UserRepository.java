package com.gbconnect.groupby.persistence;

import com.gbconnect.groupby.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
