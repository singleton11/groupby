package com.gbconnect.groupby.repositories;

import com.gbconnect.groupby.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByAuthority(String authority);
}
