package com.gbconnect.groupby.repositories;

import com.gbconnect.groupby.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByAuthority(String authority);
}
