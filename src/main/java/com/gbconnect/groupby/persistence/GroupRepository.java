package com.gbconnect.groupby.persistence;

import com.gbconnect.groupby.domain.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
