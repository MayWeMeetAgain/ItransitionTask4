package com.annieryannel.userssecure.utils.actions.actionsImpl;

import com.annieryannel.userssecure.service.UserService;
import com.annieryannel.userssecure.utils.Roles;
import com.annieryannel.userssecure.utils.actions.Action;

import java.util.List;

public class BlockAction implements Action {
    @Override
    public void execute(UserService userService, List<String> userIds) {
        for (String userId : userIds) {
            userService.removeRoleFromUser(Long.parseLong(userId), Roles.ROLE_ACTIVE);
        }
    }
}
