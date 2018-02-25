package com.gbconnect.groupby.services;

import com.gbconnect.groupby.domain.Group;
import com.gbconnect.groupby.persistence.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }
}
