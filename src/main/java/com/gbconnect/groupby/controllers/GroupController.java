package com.gbconnect.groupby.controllers;

import com.gbconnect.groupby.domain.Group;
import com.gbconnect.groupby.services.GroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/groups")
    public Group createGroup(@Valid @RequestBody Group group) {
        return groupService.createGroup(group);
    }
}
